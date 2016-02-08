package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.udistrital.plantae.R;

/**
 * Created by hghar on 5/20/15.
 */
public class EditValueDialogFragment extends DialogFragment {

    private static final String ID1 = "id1";
    private static final String CLASS_TYPE1 = "classType1";
    private static final String VALUE1 = "value1";
    private static final String PARENT_VALUES1 = "parentValues1";
    private static final String PARENT_VALUE2 = "parentValue2";

    private Long id1;
    private String classType1;
    private String value1;
    private String parentValue;
    private List<String> parentValues;

    private OnValueChangedListener mListener;

    public interface OnValueChangedListener {
        void onValueChanged(Long id, String classType, String newValue, String parentValue);
    }

    public static EditValueDialogFragment newInstance(Long id, String classType,
                                                      String value) {
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

    public static EditValueDialogFragment newInstance(Long id, String classType,
                                                      String value,
                                                      String parentValue,
                                                      ArrayList<String> parentValues) {
        EditValueDialogFragment fragment =  new EditValueDialogFragment();
        Bundle args = new Bundle();
        if (id != null) {
            args.putLong(ID1, id);
        }
        args.putString(CLASS_TYPE1, classType);
        if (value != null) {
            args.putString(VALUE1, value);
        }
        if (parentValue != null) {
            args.putString(PARENT_VALUE2, parentValue);
        }
        args.putStringArrayList(PARENT_VALUES1, parentValues);
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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        id1 = getArguments().getLong(ID1);
        classType1 = getArguments().getString(CLASS_TYPE1);
        value1 = getArguments().getString(VALUE1);
        parentValue = getArguments().getString(PARENT_VALUE2);
        parentValues = getArguments().getStringArrayList(PARENT_VALUES1);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (value1 == null) {
            builder.setTitle(R.string.title_add_value_dialog_fragment);
        } else {
            builder.setTitle(R.string.title_edit_value_dialog_fragment);
        }

        if (parentValues == null) {
            final EditText input = new EditText(getActivity());
            input.setText(value1);
            builder.setView(input);
            builder.setPositiveButton(R.string.ok_action_dialog_fragment, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if (input.getTextSize() > 0) {
                        mListener.onValueChanged(id1, classType1, input.getText().toString(), null);
                    }else{
                        Toast.makeText(getActivity(), getActivity().getString(R.string.value_required), Toast.LENGTH_LONG).show();
                    }
                }
            }).setNegativeButton(R.string.cancel_action_dialog_fragment, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
        } else {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            View view = inflater.inflate(R.layout.parent_child_value_dialog, null);
            builder.setView(view);
            final AutoCompleteTextView parentCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.parent_value);
            final EditText value = (EditText) view.findViewById(R.id.value);
            parentCompleteTextView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, parentValues));
            parentCompleteTextView.setText(parentValue);
            value.setText(value1);
            builder.setPositiveButton(R.string.ok_action_dialog_fragment, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if (value.getTextSize() > 0 && parentCompleteTextView.getTextSize() > 0) {
                        String parentInputValue = parentCompleteTextView.getText().toString();
                        if (!parentValues.contains(parentInputValue)) {
                            Toast.makeText(getActivity(), getActivity().getString(R.string.must_select_parent_value), Toast.LENGTH_LONG).show();
                            return;
                        }
                        mListener.onValueChanged(id1, classType1, value.getText().toString(), parentCompleteTextView.getText().toString());
                    }else{
                        Toast.makeText(getActivity(), getActivity().getString(R.string.value_parent_value_required), Toast.LENGTH_LONG).show();
                    }
                }
            }).setNegativeButton(R.string.cancel_action_dialog_fragment, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
        }
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
