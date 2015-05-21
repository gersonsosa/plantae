package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import edu.udistrital.plantae.R;

/**
 * Created by hghar on 5/20/15.
 */
public class EditValueDialogFragment extends DialogFragment {

    private static final String ID1 = "id1";
    private static final String CLASS_TYPE1 = "classType1";
    private static final String VALUE1 = "value1";

    private Long id1;
    private String classType1;
    private String value1;

    private OnValueChangedListener mListener;

    public interface OnValueChangedListener {
        void onValueChanged(Long id, String classType, String newValue);
    }

    public static EditValueDialogFragment newInstance(Long id, String classType, String value) {
        EditValueDialogFragment fragment =  new EditValueDialogFragment();
        Bundle args = new Bundle();
        if (id != null) {
            args.putLong(ID1, id);
        }
        args.putString(CLASS_TYPE1, classType);
        if (value != null) {
            args.putString(VALUE1, value);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnValueChangedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnValueChangedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        id1 = getArguments().getLong(ID1);
        classType1 = getArguments().getString(CLASS_TYPE1);
        value1 = getArguments().getString(VALUE1);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (value1 == null) {
            builder.setTitle(R.string.title_add_value_dialog_fragment);
        } else {
            builder.setTitle(R.string.title_edit_value_dialog_fragment);
        }
        final EditText input = new EditText(getActivity());
        input.setText(value1);
        builder.setView(input);
        builder.setPositiveButton(R.string.ok_action_dialog_fragment, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (input.getTextSize() > 0) {
                    mListener.onValueChanged(id1, classType1, input.getText().toString());
                }
            }
        }).setNegativeButton(R.string.cancel_action_dialog_fragment, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
