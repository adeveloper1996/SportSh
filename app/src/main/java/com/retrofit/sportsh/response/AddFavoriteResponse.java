package com.retrofit.sportsh.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.retrofit.sportsh.model.DataAddProduct;

import java.util.List;

public class AddFavoriteResponse {

    @SerializedName("data")
    @Expose
    private List<DataAddProduct> data = null;

    public List<DataAddProduct> getData() {
        return data;
    }

    public void setData(List<DataAddProduct> data) {
        this.data = data;
    }
}
