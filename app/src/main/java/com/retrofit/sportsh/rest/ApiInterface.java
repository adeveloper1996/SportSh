package com.retrofit.sportsh.rest;


import com.retrofit.sportsh.response.AddFavoriteResponse;
import com.retrofit.sportsh.response.ProductResponse;
import com.retrofit.sportsh.response.TempAuthResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("?act=get_products&page=0&limit=10")
    Call<ProductResponse> getProducts(@Query("cat_id") String catId, @Query("ta_id") String taId);

    @GET("?act=temp_auth")
    Call<TempAuthResponse> getTemp();

    @GET("?act=add_product_to_favourite")
    Call<AddFavoriteResponse> addToFavorite(@Query("prod_id") String id, @Query("ta_id") String taId);

    @GET("?act=del_product_from_favourite")
    Call<AddFavoriteResponse> deleteFromFavorite(@Query("prod_id") String id, @Query("ta_id") String taId);

    @GET("?act=add_product_to_cart")
    Call<AddFavoriteResponse> addToBasket(@Query("prod_id") String id, @Query("ta_id") String taId);

    @GET("?act=del_product_from_cart")
    Call<AddFavoriteResponse> deleteFromBasket(@Query("prod_id") String id, @Query("ta_id") String taId);

    @GET("?act=my_favourite")
    Call<ProductResponse> getFavorite(@Query("ta_id") String taId);

    @GET("?act=my_cart")
    Call<ProductResponse> getBasket(@Query("ta_id") String taId);
}
