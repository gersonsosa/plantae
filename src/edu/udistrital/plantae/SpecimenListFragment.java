package edu.udistrital.plantae;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.*;
import edu.udistrital.plantae.logicadominio.datosespecimen.EspecimenDetallado;
import edu.udistrital.plantae.logicadominio.datosespecimen.EspecimenSencillo;
import edu.udistrital.plantae.logicadominio.recoleccion.Recoleccion;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.EspecimenDao;
import edu.udistrital.plantae.persistencia.ViajeDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerson Sosa on 5/5/14.
 */
public class SpecimenListFragment extends ListFragment implements View.OnClickListener{
    private Viaje viaje;
    private EspecimenDao especimenDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_specimen_list, container, false);
        Long viajeID = getArguments().getLong("viaje");
        // Cargar los especímenes del viaje seleccionado por defecto o un aviso de agregar un nuevo especimen
        ViajeDao viajeDao = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession().getViajeDao();
        viaje = viajeDao.loadDeep(viajeID);
        getActivity().setTitle(viaje.getNombre());
        List<Recoleccion> recolecciones = viaje.getRecolecciones();
        if (recolecciones.size()>0){
            List<ListItem> listItems = new ArrayList<ListItem>(recolecciones.size());
            for (Recoleccion recoleccion : recolecciones) {
                if (recoleccion.getEspecimen() instanceof EspecimenSencillo){
                    EspecimenSencillo especimen = (EspecimenSencillo) recoleccion.getEspecimen();
                    listItems.add(new ListItem(especimen.getId(), especimen.generarNumeroDeColeccion(), especimen.getDescripcionEspecimen(), 0, "0", true));
                }else if (recoleccion.getEspecimen() instanceof EspecimenDetallado){
                    EspecimenSencillo especimen = (EspecimenSencillo) recoleccion.getEspecimen();
                    listItems.add(new ListItem(especimen.getId(), especimen.getNumeroDeColeccion(), especimen.getDescripcionEspecimen(), 0, "0", true));
                }
            }
            setListAdapter(new ListItemAdapter(getActivity().getApplicationContext(), listItems, this));
        }/*else{
            View noSpecimens = rootView.findViewById(R.id.no_specimens);
            noSpecimens.setVisibility(View.VISIBLE);
            View specimenList = rootView.findViewById(android.R.id.list);
            specimenList.setVisibility(View.GONE);
        }*/
        especimenDao = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession().getEspecimenDao();
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.specimen_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = getActivity().getApplicationContext();
        switch (item.getItemId()){
            case R.id.action_add:
                // call CreateSpecimenActivity
                Intent createSpecimenIntent = new Intent(getActivity().getApplicationContext(), CreateSpecimenActivity.class);
                createSpecimenIntent.putExtra("viaje", viaje.getId());
                startActivity(createSpecimenIntent);
                return true;
            case R.id.action_delete:
                // show a confirmation message
                new AlertDialog.Builder(context)
                        .setMessage(R.string.delete_specimen_confirmation_message)
                        .setMessage(R.string.delete_specimen_confirmation_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO comprobar cuales especimenes estan seleccionados
                                // TODO borrar especimenes seleccionados
                                // TODO actualizar lista de especimenes
                            }

                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
                return true;
            case R.id.action_edit:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

    }
}