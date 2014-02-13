package edu.udistrital.plantae;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class EspecimenListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_especimen_list, container, false);
		setListAdapter(new ArrayAdapter<String>(
                rootView.getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new String[]{"Specimen1","Specimen2","Specimen3","Specimen4","Specimen5","Specimen6"
                }));
		return rootView;
	}

}
