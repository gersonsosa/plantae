package edu.udistrital.plantae.ui;

import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;

import edu.udistrital.plantae.R;

/**
 * Created by hghar on 6/17/14.
 */
public abstract class ListMultiSelectionActionMode implements ActionMode.Callback {

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater menuInflater = mode.getMenuInflater();
        // Change for receiven value
        menuInflater.inflate(R.menu.list_contextual, menu);
        return true;
    }
}