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

import com.example.android.financerpro.Adapters.BillsListAdapter;
import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;

public class BillsFragment extends Fragment {

    private RecyclerView recyclerView;
    private BillsListAdapter mAdapter;
    private FrameLayout frameLayout;

    private OnFragmentInteractionListener mListener;

    public BillsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_bills, container, false);
        recyclerView = frameLayout.findViewById(R.id.recyclerview_bills);

        mAdapter = new BillsListAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(null);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        FinancerAppData.getInstance().setBillsListAdapter(mAdapter);


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
