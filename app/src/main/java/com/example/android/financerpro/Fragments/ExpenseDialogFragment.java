package com.example.android.financerpro.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.Date;

public class ExpenseDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.dialog_add_expense, null);
        final EditText amountET = layout.findViewById(R.id.et_expensedialog_amount);
        final EditText descriptionET = layout.findViewById(R.id.et_expensedialog_description);
        final EditText categoryET = layout.findViewById(R.id.et_expensedialog_category);
        final ImageView photoIV = layout.findViewById(R.id.iv_expensedialog_photo);
        photoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImageDialog.build(new PickSetup()).setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult pickResult) {
                        photoIV.setImageBitmap(pickResult.getBitmap());
                        Toast.makeText(getContext(), "Photo set", Toast.LENGTH_SHORT).show();
                    }
                }).show(getFragmentManager());

            }
        });

        builder.setView(layout)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Double amount = Double.parseDouble(amountET.getText().toString());
                        String description = descriptionET.getText().toString();
                        String category = categoryET.getText().toString();
                        Bitmap bitmap = drawableToBitmap(photoIV.getDrawable());
                        FinancerAppData.getInstance().addNewExpense(description,
                                new Date(), category, amount, bitmap);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ExpenseDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
