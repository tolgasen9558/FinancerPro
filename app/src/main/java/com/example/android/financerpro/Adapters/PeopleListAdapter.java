package com.example.android.financerpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;

import java.util.Locale;


public class PeopleListAdapter extends RecyclerView.Adapter<PeopleListAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, moneySpent;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_person_name);
            moneySpent = view.findViewById(R.id.tv_money_spent);
        }
    }


    public PeopleListAdapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_people, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String name = FinancerAppData.getInstance().getPeopleNames().get(position);
        Double moneySpent = FinancerAppData.getInstance().getMoneySpent(name);
        holder.name.setText(name);
        holder.moneySpent.setText(String.format(Locale.US, "%.2f", moneySpent));
    }

    @Override
    public int getItemCount() {
        return FinancerAppData.getInstance().getPeopleNames().size();
    }
}