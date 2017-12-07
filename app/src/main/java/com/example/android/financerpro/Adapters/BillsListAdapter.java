package com.example.android.financerpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.financerpro.CheckEntry;
import com.example.android.financerpro.R;
import java.util.List;
import java.util.Locale;


public class BillsListAdapter extends RecyclerView.Adapter<BillsListAdapter.MyViewHolder> {

    private List<CheckEntry> checkEntries;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView billInfo, personPaid, amount;

        public MyViewHolder(View view) {
            super(view);
            billInfo = (TextView) view.findViewById(R.id.tv_bill_info);
            personPaid = (TextView) view.findViewById(R.id.tv_person_paid);
            amount = (TextView) view.findViewById(R.id.tv_money_amount);
        }
    }


    public BillsListAdapter(List<CheckEntry> checkEntries) {
        this.checkEntries = checkEntries;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_bills, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String name = checkEntries.get(position).getPersonPaid();
        Double amount = checkEntries.get(position).getAmount();
        String info = checkEntries.get(position).getInfo();
        holder.personPaid.setText(name);
        holder.amount.setText(String.format(Locale.US, "$ %.2f", amount));
        holder.billInfo.setText(info);
    }

    @Override
    public int getItemCount() {
        return checkEntries.size();
    }
}