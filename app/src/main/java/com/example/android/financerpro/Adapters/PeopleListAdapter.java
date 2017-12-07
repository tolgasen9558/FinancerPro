package com.example.android.financerpro.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.financerpro.R;
import java.util.List;
import java.util.Locale;


public class PeopleListAdapter extends RecyclerView.Adapter<PeopleListAdapter.MyViewHolder> {

    private List<String> peopleNames;
    private List<Double> moneySpentList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, moneySpent;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_person_name);
            moneySpent = (TextView) view.findViewById(R.id.tv_money_spent);
        }
    }


    public PeopleListAdapter(List<String> names, List<Double> moneySpent) {
        this.peopleNames = names;
        this.moneySpentList = moneySpent;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_people, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String name = peopleNames.get(position);
        Double moneySpent = moneySpentList.get(position);
        holder.name.setText(name);
        holder.moneySpent.setText(String.format(Locale.US, "%.2f", moneySpent));
    }

    @Override
    public int getItemCount() {
        return peopleNames.size();
    }
}