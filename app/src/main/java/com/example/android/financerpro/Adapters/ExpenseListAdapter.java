package com.example.android.financerpro.Adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.financerpro.DataModels.ExpenseEntry;
import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.MyViewHolder> {


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView expenseName, expenseDate, expenseCategory, expenseAmount;
        public ImageView expenseIV;

        public MyViewHolder(View view) {
            super(view);
            expenseName = (TextView) view.findViewById(R.id.tv_expense_name);
            expenseDate = (TextView) view.findViewById(R.id.tv_expense_date);
            expenseCategory = (TextView) view.findViewById(R.id.tv_expense_category);
            expenseAmount = (TextView) view.findViewById(R.id.tv_expense_amount);
            expenseIV = (ImageView) view.findViewById(R.id.iv_expense_add_photo);
        }
    }


    public ExpenseListAdapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_expenses, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ExpenseEntry expense = FinancerAppData.getInstance().getExpensesList().get(position);
        String name = expense.getName();
        Date date = expense.getDate();
        String category = expense.getCategory();
        Double amount = expense.getAmount();
        Bitmap photo = expense.getImageBitmap();

        holder.expenseName.setText(name);
        holder.expenseDate.setText(convertDateToString(date));
        holder.expenseCategory.setText(category);
        holder.expenseAmount.setText(String.format(Locale.US, "$ %.2f", amount));
        holder.expenseIV.setImageBitmap(photo);
    }

    @Override
    public int getItemCount() {
        return FinancerAppData.getInstance().getExpensesList().size();
    }

    private String convertDateToString(Date date){
        String output;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        output = String.format(Locale.US, "%d.%d.%d", month + 1, day, year);
        return output;
    }
}