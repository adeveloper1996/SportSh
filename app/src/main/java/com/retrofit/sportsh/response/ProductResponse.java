package com.retrofit.sportsh.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.retrofit.sportsh.model.DataProducts;

import java.util.List;

/**
 * Created by Nursultan on 27.06.2017.
 */

public class ProductResponse {
        @SerializedName("data")
        @Expose
        private List<DataProducts> data = null;

        public List<DataProducts> getData() {
            return data;
        }

        public void setData(List<DataProducts> data) {
            this.data = data;
        }
}
