package edu.udistrital.plantae.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.taxonomia.Taxon;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.TaxonDTO;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.TaxonDao;

import java.util.List;

/**
 * Created by hghar on 6/25/14.
 */
public class TaxonInformationFragment extends Fragment {

    private EspecimenDTO especimenDTO;
    private ViewHolder viewHolder;
    private DaoSession daoSession;
    private TaxonFragmentLoadTask taxonFragmentLoadTask;

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
        especimenDTO = getArguments().getParcelable("especimen");
        daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();

        taxonFragmentLoadTask = new TaxonFragmentLoadTask();
        taxonFragmentLoadTask.execute();

        viewHolder.family.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    TaxonDTO taxonDTO;
                    String value = ((AutoCompleteTextView) v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        taxonDTO = especimenDTO.getTaxon() != null ? especimenDTO.getTaxon() : new TaxonDTO();
                        Taxon familia = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(value)).where(TaxonDao.Properties.Rango.eq("familia")).unique();
                        if (familia != null) {
                            taxonDTO.setId(familia.getId());
                        }
                        taxonDTO.setFamilia(value);
                        List<Taxon> generos = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(value)).where(TaxonDao.Properties.Rango.eq("genero")).list();
                        if (generos != null && !generos.isEmpty()) {
                            viewHolder.genus.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, generos));
                        }
                        especimenDTO.setTaxon(taxonDTO);
                    }
                }
            }
        });
        viewHolder.genus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    TaxonDTO taxonDTO = null;
                    String value = ((AutoCompleteTextView)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        taxonDTO = especimenDTO.getTaxon() != null ? especimenDTO.getTaxon() : new TaxonDTO();
                        Taxon genero = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Genero.eq(value)).where(TaxonDao.Properties.Rango.eq("genero")).unique();
                        if (genero != null){
                            List<Taxon> especies = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Genero.eq(value)).where(TaxonDao.Properties.Rango.eq("genero")).list();
                            viewHolder.species.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, especies));
                            viewHolder.family.setText(genero.getTaxonPadre().getNombre());
                            taxonDTO.setFamilia(genero.getTaxonPadre().getNombre());
                            taxonDTO.setId(genero.getId());
                        }
                        taxonDTO.setGenero(value);
                        especimenDTO.setTaxon(taxonDTO);
                    }
                }
            }
        });
        viewHolder.species.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    TaxonDTO taxonDTO;
                    String value = ((AutoCompleteTextView) v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        taxonDTO = especimenDTO.getTaxon() != null ? especimenDTO.getTaxon() : new TaxonDTO();
                        Taxon especie = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Especie.eq(value)).where(TaxonDao.Properties.Rango.eq("epitetoespecifico")).unique();
                        if (especie != null) {
                            viewHolder.family.setText(especie.getTaxonPadre().getTaxonPadre().getNombre());
                            viewHolder.genus.setText(especie.getTaxonPadre().getNombre());
                            taxonDTO.setId(especie.getId());
                            taxonDTO.setGenero(especie.getTaxonPadre().getNombre());
                            taxonDTO.setFamilia(especie.getTaxonPadre().getTaxonPadre().getNombre());
                        }
                        taxonDTO.setEspecie(value);
                        especimenDTO.setTaxon(taxonDTO);
                    }
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

    public class TaxonFragmentLoadTask extends AsyncTask<Void, Void, Boolean> {

        private ArrayAdapter<Taxon> especiesArrayAdapter;
        private ArrayAdapter<Taxon> generosArrayAdapter;
        private ArrayAdapter<Taxon> familiasArrayAdapter;

        @Override
        protected Boolean doInBackground(Void...params) {
            especimenDTO = getArguments().getParcelable("especimen");
            List<Taxon> especies = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Rango.eq("epitetoespecifico")).list();
            especiesArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,especies);
            List<Taxon> generos = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Rango.eq("genero")).list();
            generosArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,generos);
            List<Taxon> familias = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Rango.eq("familia")).list();
            familiasArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,familias);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            setAdapters(especiesArrayAdapter, generosArrayAdapter, familiasArrayAdapter);
        }

        @Override
        protected void onCancelled() {
            taxonFragmentLoadTask = null;
        }
    }

    private void setAdapters(ArrayAdapter<Taxon> especiesArrayAdapter, ArrayAdapter<Taxon> generosArrayAdapter, ArrayAdapter<Taxon> familiasArrayAdapter) {
        viewHolder.family.setAdapter(familiasArrayAdapter);
        viewHolder.genus.setAdapter(generosArrayAdapter);
        viewHolder.species.setAdapter(especiesArrayAdapter);
    }
}