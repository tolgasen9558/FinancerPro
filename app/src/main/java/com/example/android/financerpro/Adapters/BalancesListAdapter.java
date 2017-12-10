package com.example.android.financerpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.financerpro.DataModels.DebtEntry;
import com.example.android.financerpro.R;

import java.util.List;
import java.util.Locale;


public class BalancesListAdapter extends RecyclerView.Adapter<BalancesListAdapter.MyViewHolder> {

    private List<DebtEntry> debtEntries;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView personPays, personReceives, amount;

        public MyViewHolder(View view) {
            super(view);
            personPays = (TextView) view.findViewById(R.id.tv_balance_person_pays);
            personReceives = (TextView) view.findViewById(R.id.tv_balance_person_receives);
            amount = (TextView) view.findViewById(R.id.tv_balance_amount);
        }
    }


    public BalancesListAdapter(List<DebtEntry> debtEntries) {
        this.debtEntries = debtEntries;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_balance, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String personPaysName = debtEntries.get(position).getPersonPays();
        Double amount = debtEntries.get(position).getAmount();
        String personReceivesName = debtEntries.get(position).getPersonReceives();
        holder.personPays.setText(personPaysName);
        holder.amount.setText(String.format(Locale.US, "$ %.2f", amount));
        holder.personReceives.setText(personReceivesName);
    }

    @Override
    public int getItemCount() {
        return debtEntries.size();
    }
}