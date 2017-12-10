package com.example.android.financerpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.financerpro.DataModels.CheckEntry;
import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;

import java.util.Locale;


public class BillsListAdapter extends RecyclerView.Adapter<BillsListAdapter.MyViewHolder> {


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView billInfo, personPaid, amount;

        public MyViewHolder(View view) {
            super(view);
            billInfo = (TextView) view.findViewById(R.id.tv_bill_info);
            personPaid = (TextView) view.findViewById(R.id.tv_person_paid);
            amount = (TextView) view.findViewById(R.id.tv_money_amount);
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_bills, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CheckEntry checkEntry = FinancerAppData.getInstance().getCheckEntries().get(position);
        String name = checkEntry.getPersonPaid();
        Double amount = checkEntry.getAmount();
        String info = checkEntry.getInfo();
        holder.personPaid.setText(name);
        holder.amount.setText(String.format(Locale.US, "$ %.2f", amount));
        holder.billInfo.setText(info);
    }

    @Override
    public int getItemCount() {
        return FinancerAppData.getInstance().getCheckEntries().size();
    }
}