package com.example.android.financerpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.financerpro.BalanceEntry;
import com.example.android.financerpro.CheckEntry;
import com.example.android.financerpro.R;
import java.util.List;
import java.util.Locale;


public class BalancesListAdapter extends RecyclerView.Adapter<BalancesListAdapter.MyViewHolder> {

    private List<BalanceEntry> balanceEntries;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView personPays, personReceives, amount;

        public MyViewHolder(View view) {
            super(view);
            personPays = (TextView) view.findViewById(R.id.tv_balance_person_pays);
            personReceives = (TextView) view.findViewById(R.id.tv_balance_person_receives);
            amount = (TextView) view.findViewById(R.id.tv_balance_amount);
        }
    }


    public BalancesListAdapter(List<BalanceEntry> balanceEntries) {
        this.balanceEntries = balanceEntries;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_balance, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String personPaysName = balanceEntries.get(position).getPersonPays();
        Double amount = balanceEntries.get(position).getAmount();
        String personReceivesName = balanceEntries.get(position).getPersonReceives();
        holder.personPays.setText(personPaysName);
        holder.amount.setText(String.format(Locale.US, "$ %.2f", amount));
        holder.personReceives.setText(personReceivesName);
    }

    @Override
    public int getItemCount() {
        return balanceEntries.size();
    }
}