package com.example.android.financerpro.Fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.example.android.financerpro.DataModels.CheckEntry;
import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;

public class AddPersonDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.dialog_add_person, null);
        final EditText amountET = layout.findViewById(R.id.et_addpersondialog_amount);
        final EditText nameET = layout.findViewById(R.id.et_addperson_name);
        final EditText infoET = layout.findViewById(R.id.et_addperson_info);
        builder.setView(layout)
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Double amount = Double.parseDouble(amountET.getText().toString());
                        String name = nameET.getText().toString();
                        String info = infoET.getText().toString();
                        FinancerAppData.getInstance().addNewCheck(new CheckEntry(name, info, amount));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddPersonDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
