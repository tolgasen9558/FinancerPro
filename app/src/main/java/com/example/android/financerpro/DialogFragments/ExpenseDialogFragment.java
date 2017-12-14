package com.example.android.financerpro.DialogFragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExpenseDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private final int YEAR = 0;
    private final int MONTH = 1;
    private final int DAY = 2;

    Date date;
    TextView dateTV;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        @SuppressLint("InflateParams")
        final ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.dialog_add_expense, null);
        final EditText amountET = layout.findViewById(R.id.et_expensedialog_amount);
        final EditText descriptionET = layout.findViewById(R.id.et_expensedialog_description);
        final EditText categoryET = layout.findViewById(R.id.et_expensedialog_category);
        dateTV = layout.findViewById(R.id.tv_expensedialog_date);
        final ImageView photoIV = layout.findViewById(R.id.iv_expensedialog_photo);

        //Get current date
        date = new Date();
        List<Integer> dateCreds = getYearMonthDayFromDate(date);
        int year = dateCreds.get(YEAR);
        int month = dateCreds.get(MONTH);
        int day = dateCreds.get(DAY);

        //Set the dialog and textview to current date
        dateTV.setText(String.format(Locale.US, "%2d/%2d/%4d", month + 1, day, year));
        final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this, year, month + 1, day);

        dateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

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
                                date, category, amount, bitmap);
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
        Bitmap bitmap;

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

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        dateTV.setText(String.format(Locale.US, "%2d/%2d/%4d", i1 + 1, i2, i));
        date = getDateFromYearMonthDay(i, i1, i2);
    }

    private List<Integer> getYearMonthDayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<Integer> list = new ArrayList<>();
        list.add(year);
        list.add(month);
        list.add(day);
        return list;
    }

    private Date getDateFromYearMonthDay(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
