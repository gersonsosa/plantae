package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.taxonomia.Taxon;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.TaxonDao;

import java.util.List;

/**
 * Created by hghar on 6/25/14.
 */
public class TaxonInformationFragment extends Fragment {

    private ViewHolder viewHolder;
    private DaoSession daoSession;
    private TaxonFragmentLoadTask taxonFragmentLoadTask;
    private OnTaxonInformationUpdated onTaxonInformationUpdated;

    public interface OnTaxonInformationUpdated {
        public void onFamilyUpdated(String family);
        public void onGenusUpdated(String genus);
        public void onSpeciesUpdated(String species);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onTaxonInformationUpdated = (OnTaxonInformationUpdated) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement TaxonInformationFragment.onTaxonInformationUpdated");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHolder = (ViewHolder) container.getTag(R.layout.fragment_taxon_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_taxon_information, container, false);
            retrieveViews();
            container.setTag(R.layout.fragment_taxon_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }

        daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();

        taxonFragmentLoadTask = new TaxonFragmentLoadTask();
        taxonFragmentLoadTask.execute();

        viewHolder.family.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onTaxonInformationUpdated.onFamilyUpdated(((AutoCompleteTextView) v).getText().toString());
                }
            }
        });
        viewHolder.genus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onTaxonInformationUpdated.onGenusUpdated(((AutoCompleteTextView) v).getText().toString());
                }
            }
        });
        viewHolder.species.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onTaxonInformationUpdated.onSpeciesUpdated(((AutoCompleteTextView) v).getText().toString());
                }
            }
        });
        return viewHolder.fragmentView;
    }

    private void retrieveViews() {
        viewHolder.family = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.family);
        viewHolder.genus = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.genus);
        viewHolder.species = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.species);
    }

    static class ViewHolder{
        View fragmentView;
        AutoCompleteTextView family;
        AutoCompleteTextView genus;
        AutoCompleteTextView species;
    }

    public void updateTaxon(String family, String genus, boolean clearSpecies) {
        if (clearSpecies) {
            viewHolder.species.setText("");
        }
        taxonFragmentLoadTask = new TaxonFragmentLoadTask();
        taxonFragmentLoadTask.execute(family, genus);
    }

    public class TaxonFragmentLoadTask extends AsyncTask<String, Void, Boolean> {

        private ArrayAdapter<Taxon> especiesArrayAdapter;
        private ArrayAdapter<Taxon> generosArrayAdapter;
        private ArrayAdapter<Taxon> familiasArrayAdapter;
        private String family;
        private String genus;

        @Override
        protected Boolean doInBackground(String...params) {
            if (params != null && params.length > 0) {
                family = params[0];
                genus = params[1];
            }
            if (family == null && genus == null) {
                List<Taxon> especies = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Rango.eq("epitetoespecifico")).list();
                especiesArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,especies);
                List<Taxon> generos = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Rango.eq("genero")).list();
                generosArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,generos);
                List<Taxon> familias = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Rango.eq("familia")).list();
                familiasArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,familias);
            }else if (family != null && genus == null) {
                List<Taxon> generos = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(family)).where(TaxonDao.Properties.Rango.eq("genero")).list();
                generosArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,generos);
            }else {
                List<Taxon> especies = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Genero.eq(genus)).where(TaxonDao.Properties.Rango.eq("genero")).list();
                especiesArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,especies);
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (familiasArrayAdapter != null) {
                viewHolder.family.setAdapter(familiasArrayAdapter);
            }
            if (generosArrayAdapter != null) {
                viewHolder.genus.setAdapter(generosArrayAdapter);
            }
            if (especiesArrayAdapter != null) {
                viewHolder.species.setAdapter(especiesArrayAdapter);
            }
            if (!viewHolder.genus.getText().toString().equals(genus)) {
                viewHolder.genus.setText(genus);
            }
            if (!viewHolder.family.getText().toString().equals(family)) {
                viewHolder.family.setText(family);
            }
        }

        @Override
        protected void onCancelled() {
            taxonFragmentLoadTask = null;
        }
    }
}