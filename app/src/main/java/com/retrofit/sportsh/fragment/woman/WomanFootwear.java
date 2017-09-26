package com.retrofit.sportsh.fragment.woman;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retrofit.sportsh.rest.InitApi;
import com.retrofit.sportsh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WomanFootwear extends Fragment {

    private RecyclerView recyclerView;

    public WomanFootwear() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_woman_footwear, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_woman_footwear);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        InitApi.initApi(recyclerView,"9", getContext());
    }

}
