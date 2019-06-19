package com.example.esstelleague;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * @author daphne
 * PlayFragment which loads the recyclerView.
 */

public class PlayFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Attraction> mDataset;

    /**
     * onCreateView method is created by extending Fragment.
     * @param inflater LayoutInflater object.
     * @param container ViewGroup container object.
     * @param savedInstanceState Bundle obj. savedInstanceState.
     * @return View object.
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        super.onCreate(savedInstanceState);

        // Init mDataset
        this.mDataset = ProjectFactory.getInstance().getDataset();

        mRecyclerView =  view.findViewById(R.id.attractieRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new AttractionAdapter(mDataset, this.getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

}
