package com.retrofit.sportsh.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.retrofit.sportsh.model.DataTempAuth;

import java.util.List;


public class TempAuthResponse {
    @SerializedName("data")
    @Expose
    private List<DataTempAuth> data = null;

    public List<DataTempAuth> getData() {
        return data;
    }

    public void setData(List<DataTempAuth> data) {
        this.data = data;
    }
}
