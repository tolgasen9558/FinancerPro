package com.example.android.financerpro.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.android.financerpro.Adapters.BalancesListAdapter;
import com.example.android.financerpro.DataModels.BalanceEntry;
import com.example.android.financerpro.DataModels.DebtEntry;
import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BalancesFragment extends Fragment {

    private List<DebtEntry> debts = new ArrayList<>();

    private RecyclerView recyclerView;
    private BalancesListAdapter mAdapter;
    private FrameLayout frameLayout;

    private OnFragmentInteractionListener mListener;

    public BalancesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void calculateDebts(){
        debts.clear();
        List<BalanceEntry> balanceEntries = new ArrayList<>();
        List<String> peopleNames = FinancerAppData.getInstance().getPeopleNames();
        int numPeople = peopleNames.size();

        if (numPeople < 2) {
            return;
        }

        //Create balance entry list
        for(int i = 0; i < numPeople; i++) {
            String name = peopleNames.get(i);
            balanceEntries.add(new BalanceEntry(name, FinancerAppData.getInstance().getMoneySpent(name)));
        }

        //Calculate average amount of checks
        Double average = 0.0;
        for (BalanceEntry balanceEntry : balanceEntries) {
            average += balanceEntry.getAmountPaid() / numPeople;
        }

        //Set everyone's balance
        for (BalanceEntry balanceEntry : balanceEntries) {
            balanceEntry.setBalance(balanceEntry.getAmountPaid() - average);
        }

        //Sort the list in descending balance order
        Collections.sort(balanceEntries, new Comparator<BalanceEntry>() {
            @Override
            public int compare(BalanceEntry b1, BalanceEntry b2) {
                return b2.getBalance().compareTo(b1.getBalance());
            }
        });

        while (balanceEntries.size() > 1) {
            BalanceEntry personReceives = balanceEntries.get(0);
            BalanceEntry personPays = balanceEntries.get(balanceEntries.size() - 1);
            if(personPays.getBalance() == 0){
                balanceEntries.remove(personPays);
                continue;
            }
            if(personReceives.getBalance() == 0){
                balanceEntries.remove(personReceives);
                continue;
            }

            Double amountToBePaid;
            amountToBePaid = Math.min(personReceives.getBalance(), - personPays.getBalance());
            personReceives.setBalance(personReceives.getBalance() - amountToBePaid);
            personPays.setBalance(personPays.getBalance() + amountToBePaid);
            debts.add(new DebtEntry(personPays.getPersonName(), personReceives.getPersonName()
                    , amountToBePaid));
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_balances, container, false);
        recyclerView = frameLayout.findViewById(R.id.recyclerview_balances);

        mAdapter = new BalancesListAdapter(debts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(null);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        calculateDebts();
        if(mAdapter.getItemCount() == 0){
            Toast.makeText(getActivity().getApplicationContext(),
                    "There is no balance to show.", Toast.LENGTH_LONG).show();
        }
        return frameLayout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
