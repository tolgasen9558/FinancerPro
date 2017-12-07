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
import com.example.android.financerpro.CheckEntry;
import com.example.android.financerpro.R;

import java.util.ArrayList;
import java.util.List;

public class BillsFragment extends Fragment {

    private List<CheckEntry> checkEntries = new ArrayList<>();
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

    private void prepareData(){
        checkEntries.add(new CheckEntry("Person 1", "Info1", 10.0));
        checkEntries.add(new CheckEntry("Person 2", "Info2", 20.0));
        checkEntries.add(new CheckEntry("Person 3", "Info3", 50.0));
        checkEntries.add(new CheckEntry("Person 4", "Info4", 100.0));

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_bills, container, false);
        recyclerView = frameLayout.findViewById(R.id.recyclerview_bills);

        mAdapter = new BillsListAdapter(checkEntries);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(null);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareData();
        return frameLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
