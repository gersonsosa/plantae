package edu.udistrital.plantae;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewHolder viewHolder = (ViewHolder) container.getTag(R.layout.fragment_taxon_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_taxon_information, container, false);
            container.setTag(R.layout.fragment_taxon_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        especimenDTO = getArguments().getParcelable("especimen");
        final DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();
        final AutoCompleteTextView family = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.family);
        final AutoCompleteTextView genus = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.genus);
        final AutoCompleteTextView species = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.species);
        family.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    TaxonDTO taxonDTO = null;
                    String value = ((AutoCompleteTextView)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        taxonDTO = especimenDTO.getTaxon() != null ? especimenDTO.getTaxon() : new TaxonDTO();
                        Taxon familia = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Genero.eq(value)).where(TaxonDao.Properties.Rango.eq("familia")).unique();
                        if (familia != null) {
                            taxonDTO.setId(familia.getId());
                        }
                        taxonDTO.setFamilia(value);
                        List<Taxon> generos = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(value)).where(TaxonDao.Properties.Rango.eq("genero")).list();
                        if (generos != null && !generos.isEmpty()) {
                            genus.setAdapter(new ArrayAdapter<Taxon>(getActivity().getApplicationContext(),R.id.genus,generos));
                        }
                        especimenDTO.setTaxon(taxonDTO);
                    }
                }
            }
        });
        genus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    TaxonDTO taxonDTO = null;
                    String value = ((AutoCompleteTextView)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        taxonDTO = especimenDTO.getTaxon() != null ? especimenDTO.getTaxon() : new TaxonDTO();
                        Taxon genero = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Genero.eq(value)).where(TaxonDao.Properties.Rango.eq("genero")).unique();
                        if (genero != null){
                            List<Taxon> especies = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(value)).where(TaxonDao.Properties.Rango.eq("genero")).list();
                            species.setAdapter(new ArrayAdapter<Taxon>(getActivity().getApplicationContext(), R.id.genus, especies));
                            family.setText(genero.getTaxonPadre().getNombre());
                            taxonDTO.setFamilia(genero.getTaxonPadre().getNombre());
                            taxonDTO.setId(genero.getId());
                        }
                        taxonDTO.setGenero(value);
                        especimenDTO.setTaxon(taxonDTO);
                    }
                }
            }
        });
        species.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    TaxonDTO taxonDTO = null;
                    String value = ((AutoCompleteTextView)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        taxonDTO = especimenDTO.getTaxon() != null ? especimenDTO.getTaxon() : new TaxonDTO();
                        Taxon especie = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Especie.eq(value)).where(TaxonDao.Properties.Rango.eq("epitetoespecifico")).unique();
                        if (especie != null) {
                            family.setText(especie.getTaxonPadre().getTaxonPadre().getNombre());
                            genus.setText(especie.getTaxonPadre().getNombre());
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

    static class ViewHolder{
        View fragmentView;
    }
}