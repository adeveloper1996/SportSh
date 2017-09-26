package com.retrofit.sportsh.fragment.favorite;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.retrofit.sportsh.R;
import com.retrofit.sportsh.adapter.FavoriteAdapter;
import com.retrofit.sportsh.model.DataProducts;
import com.retrofit.sportsh.model.InfoTemp;
import com.retrofit.sportsh.model.ProductInfo;
import com.retrofit.sportsh.response.ProductResponse;
import com.retrofit.sportsh.rest.ApiClient;
import com.retrofit.sportsh.rest.ApiInterface;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private Realm realm;


    public FavoriteFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = (LinearLayout) view.findViewById(R.id.li_empty);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_favorite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));

        realm = Realm.getDefaultInstance();
        InfoTemp infoTemp = realm.where(InfoTemp.class).findFirst();

        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<ProductResponse> call = apiInterface.getFavorite(String.valueOf(infoTemp.getId()));
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse productResponse = response.body();
                List<DataProducts> dataProducts = productResponse.getData();
                List<ProductInfo> productInfos = dataProducts.get(0).getObject();

                boolean success = dataProducts.get(0).isSuccess();
                if(success) {
                    if(productInfos.size() != 0) {
                        linearLayout.setVisibility(View.GONE);
                        recyclerView.setAdapter(new FavoriteAdapter(getActivity(), productInfos,R.layout.list_cardview));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });

    }
}
