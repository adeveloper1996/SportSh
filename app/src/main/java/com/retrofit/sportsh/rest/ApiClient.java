package com.retrofit.sportsh.rest;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nursultan on 27.06.2017.
 */

public class ApiClient {
    public static final String BASE_URL = "http://samalbek.bnm.kz/api.php/";
    private static Retrofit retrofit = null;
    private static volatile ApiInterface apiInterface;

    @NonNull
    public static ApiInterface getApiInterface() {
        ApiInterface service = apiInterface;
        if (service == null) {
            synchronized (ApiClient.class) {
                service = apiInterface;
                if (service == null) {
                    service = apiInterface = getClient().create(ApiInterface.class);
                }
            }
        }
        return service;
    }

    public static Retrofit getClient(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
