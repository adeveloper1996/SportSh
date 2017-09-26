package com.retrofit.sportsh.fragment.sport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retrofit.sportsh.R;

public class TennisFragment extends Fragment {

    public TennisFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tennis, container, false);
    }


}
