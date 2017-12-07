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

import com.example.android.financerpro.Adapters.PeopleListAdapter;
import com.example.android.financerpro.R;

import java.util.ArrayList;
import java.util.List;

public class PeopleFragment extends Fragment {

    private List<String> peopleNames = new ArrayList<>();
    private List<Double> moneySpentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PeopleListAdapter mAdapter;
    private FrameLayout frameLayout;

    private OnFragmentInteractionListener mListener;

    public PeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void prepareData(){
        peopleNames.add("Tristan");
        moneySpentList.add(18.5);
        peopleNames.add("Tolga");
        moneySpentList.add(10.5);
        peopleNames.add("Peter");
        moneySpentList.add(20.0);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_people, container, false);
        recyclerView = frameLayout.findViewById(R.id.recyclerview_check_calculator);

        mAdapter = new PeopleListAdapter(peopleNames, moneySpentList);
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
