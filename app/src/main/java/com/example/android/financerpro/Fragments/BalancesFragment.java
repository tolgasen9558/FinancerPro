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

import com.example.android.financerpro.Adapters.BalancesListAdapter;
import com.example.android.financerpro.Adapters.BillsListAdapter;
import com.example.android.financerpro.BalanceEntry;
import com.example.android.financerpro.CheckEntry;
import com.example.android.financerpro.R;
import java.util.ArrayList;
import java.util.List;

public class BalancesFragment extends Fragment {

    private List<BalanceEntry> balanceEntries = new ArrayList<>();
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

    private void prepareData(){
        balanceEntries.add(new BalanceEntry("Person 1", "Person4", 3.0));
        balanceEntries.add(new BalanceEntry("Person 2", "Person3", 4.0));
        balanceEntries.add(new BalanceEntry("Person 3", "Person5", 2.0));
        balanceEntries.add(new BalanceEntry("Person 4", "Person6", 12.0));

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_balances, container, false);
        recyclerView = frameLayout.findViewById(R.id.recyclerview_balances);

        mAdapter = new BalancesListAdapter(balanceEntries);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(null);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareData();
        return frameLayout;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
