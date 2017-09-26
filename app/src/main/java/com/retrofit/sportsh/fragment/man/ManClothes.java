package com.retrofit.sportsh.fragment.man;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retrofit.sportsh.rest.InitApi;
import com.retrofit.sportsh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManClothes extends Fragment {

    private RecyclerView recyclerView;

    public ManClothes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_man_clothes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("dddd",""+123);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_man_clothes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        InitApi.initApi(recyclerView,"5",getActivity());

    }

}
