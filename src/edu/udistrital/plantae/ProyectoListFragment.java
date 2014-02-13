package edu.udistrital.plantae;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ProyectoListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_proyecto_list, container, false);
		setListAdapter(new ArrayAdapter<String>(
                rootView.getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new String[]{"Proyecto1","Proyecto2","Proyecto3"
                }));
		return rootView;
	}

}
