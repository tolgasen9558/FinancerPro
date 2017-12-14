package com.example.android.financerpro.BaseActivities;
import com.example.android.financerpro.R;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import java.util.List;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.support.v7.app.AlertDialog;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Debt> debtList;
    private final Context c;
    private final DebtTrackerActivity m;
    private View UV;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView value;
        public TextView contact;

        public ViewHolder(View view) {

            super(view);
            name = (TextView) view.findViewById(R.id.title);
            value = (TextView) view.findViewById(R.id.genre);
            contact = (TextView) view.findViewById(R.id.year);
            //UV = view;
        }
    }

    public MyAdapter(List<Debt> moviesList, Context c, DebtTrackerActivity m) {
        this.c = c;
        this.m = m;
        this.debtList = moviesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_debt, parent, false);
        //UV = parent.getRootView();
        //UV = itemView;
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int pos = position;
        final Debt debt = debtList.get(position);
        final Context ddd = holder.name.getContext();
        UV = holder.itemView;
        holder.name.setText(debt.getName());
        holder.value.setText(Double.toString(debt.getValue()));
        holder.contact.setText(debt.getContact());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeDebt(debt, UV, ddd);
            }
        });
    }

    @Override
    public int getItemCount() {
        return debtList.size();
    }

    public void changeDebt(Debt debt, View v, Context ddd) {
        //AlertDialog.Builder alert = new AlertDialog.Builder(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(ddd);
        //LayoutInflater inflater = LayoutInflater.from(parent.);
        //Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater)ddd.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.click_dialog, null);
        //alert.setView(R.layout.click_dialog);
        alert.setView(dialogView);
        final Debt debt1 = debt;
        final View cc = UV;
        final EditText edit = (EditText) dialogView.findViewById(R.id.edit_name);
        Button but = (Button) dialogView.findViewById(R.id.Button_name);
        but.setText("EDIT NAME");
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input2 = edit.getText().toString();
                debt1.setName(input2);
            }
        });
        final EditText edit2 = (EditText) dialogView.findViewById(R.id.edit_amount);
        Button but2 = (Button) dialogView.findViewById(R.id.Button_amount);
        but2.setText("EDIT AMOUNT");
        but2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input3 = edit2.getText().toString();
                float x = Float.parseFloat(input3);
                debt1.setValue(x);
            }
        });
        final EditText edit3 = (EditText) dialogView.findViewById(R.id.edit_phone);
        Button but3 = (Button) dialogView.findViewById(R.id.Button_phone);
        but3.setText("EDIT PHONE");
        but3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input4 = edit3.getText().toString();
                debt1.setContact(input4);
            }
        });
        alert.show();
    }


}
