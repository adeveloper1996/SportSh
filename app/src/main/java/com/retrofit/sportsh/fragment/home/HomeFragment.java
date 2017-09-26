package com.retrofit.sportsh.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retrofit.sportsh.R;
import com.retrofit.sportsh.adapter.BrendAdapter;
import com.retrofit.sportsh.adapter.SliderImageAdapter;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {
    private static ViewPager mPager;
    private RecyclerView recyclerView;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager(view);
    }

    private void initViewPager(View view) {
        mPager = (ViewPager) view.findViewById(R.id.slide_view_pager);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_home);
        CircleIndicator circleIndicator = (CircleIndicator) view.findViewById(R.id.indicatorSlider);

        mPager.setAdapter(new SliderImageAdapter(getActivity()));
        mPager.addOnPageChangeListener(viewPagerPageChangeListener);
        circleIndicator.setViewPager(mPager);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new BrendAdapter(getContext()));
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

}
