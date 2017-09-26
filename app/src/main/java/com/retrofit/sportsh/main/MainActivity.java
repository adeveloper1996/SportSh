package com.retrofit.sportsh.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.retrofit.sportsh.R;
import com.retrofit.sportsh.adapter.ExpandableListAdapter;
import com.retrofit.sportsh.basket.BasketActivity;
import com.retrofit.sportsh.fragment.children.ChildClothes;
import com.retrofit.sportsh.fragment.children.ChildFootWear;
import com.retrofit.sportsh.fragment.favorite.FavoriteFragment;
import com.retrofit.sportsh.fragment.home.HomeFragment;
import com.retrofit.sportsh.fragment.man.ManClothes;
import com.retrofit.sportsh.fragment.man.ManFootWear;
import com.retrofit.sportsh.fragment.sport.BasketballFragment;
import com.retrofit.sportsh.fragment.sport.FitnessFragment;
import com.retrofit.sportsh.fragment.sport.FootballFragment;
import com.retrofit.sportsh.fragment.sport.TennisFragment;
import com.retrofit.sportsh.fragment.woman.WomanClothes;
import com.retrofit.sportsh.fragment.woman.WomanFootwear;
import com.retrofit.sportsh.model.DataTempAuth;
import com.retrofit.sportsh.model.ExpandedMenuModel;
import com.retrofit.sportsh.model.InfoTemp;
import com.retrofit.sportsh.response.TempAuthResponse;
import com.retrofit.sportsh.rest.ApiClient;
import com.retrofit.sportsh.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ExpandableListAdapter mMenuAdapter;
    private ExpandableListView expandableList;
    private NavigationView navigationView;
    private List<ExpandedMenuModel> listDataHeader;
    private HashMap<ExpandedMenuModel, List<String>> listDataChild;
    private Fragment fragment;
    private String titleFragment;
    private TextView txtHome;
    private TextView txtFavorite;
    private TextView txtBasket;

    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        realm = Realm.getDefaultInstance();
        RealmResults<InfoTemp> infoTemps = realm.where(InfoTemp.class).findAll();
        if(infoTemps.size() == 0){
            initTemp();
            Log.d("dddd","temp" + 123);
        }else {
            Toast.makeText(this,"temp id have",Toast.LENGTH_SHORT).show();
            Log.d("dddd","temp" + infoTemps.get(0).getId());
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        expandableList = (ExpandableListView) findViewById(R.id.navigationmenu);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        txtHome = (TextView) findViewById(R.id.txt_home);
        txtHome.setOnClickListener(this);
        txtFavorite = (TextView) findViewById(R.id.txt_favorite);
        txtFavorite.setOnClickListener(this);
        txtBasket = (TextView) findViewById(R.id.txt_basket);
        txtBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BasketActivity.class));
            }
        });
        if (navigationView != null) {
            setupDrawerContent(toolbar);
        }

        prepareListData();
        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, expandableList);

        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (i == 0){
                    if (i1 == 0){
                        fragment = new ManClothes();
                        titleFragment = "Clothes";
                    }
                    if (i1 == 1){
                        fragment = new ManFootWear();
                        titleFragment = "FootWear";
                    }
                    if (i1 == 2){
                      //  fragment = new ManAccessoriesFragment();
                        titleFragment = "Accessories";
                    }
                }
                if (i == 1){
                    if (i1 == 0){
                        fragment = new WomanClothes();
                        titleFragment = "Clothes";
                    }
                    if (i1 == 1){
                        fragment = new WomanFootwear();
                        titleFragment = "FootWear";
                    }
                    if (i1 == 2){
                       // fragment = new WomanAccessoriesFragment();
                        titleFragment = "Accessories";
                    }
                }
                if (i == 2){
                    if (i1 == 0){
                        fragment = new ChildClothes();
                        titleFragment = "Clothes";
                    }
                    if (i1 == 1){
                        fragment = new ChildFootWear();
                        titleFragment = "FootWear";
                    }
                    if (i1 == 2){
                       // fragment = new KidsAccessoriesFragment();
                        titleFragment = "Accessories";
                    }
                }
                if (i == 3){
                    if (i1 == 0){
                        fragment = new FootballFragment();
                        titleFragment = "Football";
                    }
                    if (i1 == 1){
                        fragment = new BasketballFragment();
                        titleFragment = "Basketball";
                    }
                    if (i1 == 2){
                        fragment = new TennisFragment();
                        titleFragment = "Tennis";
                    }
                    if (i1 == 3){
                        fragment = new FitnessFragment();
                        titleFragment = "Fitness";
                    }
                    if (i1 == 4){
                        //fragment = new RunningFragment();
                        titleFragment = "Run";
                    }
                }
                setFragment(fragment);
                setActionBarTitle(titleFragment);
                return false;
            }
        });
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Log.d("DEBUG", "heading clicked");
                return false;
            }
        });

    }

    private void initTemp() {
        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<TempAuthResponse> call = apiInterface.getTemp();
        call.enqueue(new Callback<TempAuthResponse>() {
            @Override
            public void onResponse(Call<TempAuthResponse> call, Response<TempAuthResponse> response) {
                TempAuthResponse tempAuthResponse = response.body();
                List<DataTempAuth> dataTempAuth = tempAuthResponse.getData();
                InfoTemp infoTemp = dataTempAuth.get(0).getTempId();
                boolean success = dataTempAuth.get(0).isSuccess();
                if (success){
                    Log.d("dddd","id" + infoTemp.getId());
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(infoTemp);
                    realm.commitTransaction();
                }

            }

            @Override
            public void onFailure(Call<TempAuthResponse> call, Throwable t) {
                Log.d("dddd",t.getMessage());
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<ExpandedMenuModel>();
        listDataChild = new HashMap<ExpandedMenuModel, List<String>>();

        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName("Man");
        item1.setIconImg(R.drawable.ic_accessibility_blue_grey_600_24dp);
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName("Woman");
        item2.setIconImg(R.drawable.ic_pregnant_woman_blue_grey_600_24dp);
        listDataHeader.add(item2);

        ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setIconName("Children");
        item3.setIconImg(R.drawable.ic_child_friendly_blue_grey_700_24dp);
        listDataHeader.add(item3);

        ExpandedMenuModel item4 = new ExpandedMenuModel();
        item4.setIconName("Sport");
        item4.setIconImg(R.drawable.ic_fitness_center_blue_grey_600_24dp);
        listDataHeader.add(item4);


        // Adding child data
        List<String> heading1 = new ArrayList<String>();
        heading1.add("Clothes");
        heading1.add("FootWear");
        heading1.add("Accessories");

        List<String> heading2 = new ArrayList<String>();
        heading2.add("Clothes");
        heading2.add("FootWear");
        heading2.add("Accessories");

        List<String> heading3 = new ArrayList<String>();
        heading3.add("Clothes");
        heading3.add("FootWear");
        heading3.add("Accessories");

        List<String> heading4 = new ArrayList<String>();
        heading4.add("Football");
        heading4.add("Basketball");
        heading4.add("Tennis");
        heading4.add("Fitness");
        heading4.add("Run");

        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), heading2);
        listDataChild.put(listDataHeader.get(2), heading3);
        listDataChild.put(listDataHeader.get(3), heading4);

    }

    private void setupDrawerContent(Toolbar toolbar) {
        drawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_home:
                fragment = new HomeFragment();
                titleFragment = "Main";
                break;
            case R.id.txt_favorite:
                fragment = new FavoriteFragment();
                titleFragment = "Favorite";
                break;
        }
        setFragment(fragment);
        setActionBarTitle(titleFragment);
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        mDrawerLayout.closeDrawer(navigationView);
    }

    public void setActionBarTitle(String item) {
        String title = item;
        getSupportActionBar().setTitle(title);
    }
}
