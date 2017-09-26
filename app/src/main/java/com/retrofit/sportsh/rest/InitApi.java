package com.retrofit.sportsh.rest;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.retrofit.sportsh.R;
import com.retrofit.sportsh.adapter.MainAdapter;
import com.retrofit.sportsh.model.InfoTemp;
import com.retrofit.sportsh.model.ProductInfo;
import com.retrofit.sportsh.model.DataProducts;
import com.retrofit.sportsh.response.ProductResponse;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitApi {
    private static Realm realm;

    public static void initApi(final RecyclerView recyclerView, String catId, final Context context) {
        realm = Realm.getDefaultInstance();
        InfoTemp infoTemp = realm.where(InfoTemp.class).findFirst();
        Log.d("dddd","tempIdApi" + infoTemp.getId());
        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<ProductResponse> call = apiInterface.getProducts(catId, String.valueOf(infoTemp.getId()));
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse productsResponse = response.body();
                    List<DataProducts> dataProducts = productsResponse.getData();
                    List<ProductInfo> productInfos = dataProducts.get(0).getObject();
                    boolean success = dataProducts.get(0).isSuccess();
                    if (success){
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.delete(ProductInfo.class);
                        realm.insert(productInfos);
                        realm.commitTransaction();
                        recyclerView.setAdapter(new MainAdapter(context, productInfos, R.layout.list_cardview));
                    }
                }else {
                    Log.d("dddd",""+13456789);
                }

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d("dddd","err" + t.getMessage());
            }
        });
    }
}

