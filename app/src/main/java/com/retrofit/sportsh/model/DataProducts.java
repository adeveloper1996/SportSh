package com.retrofit.sportsh.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nursultan on 27.06.2017.
 */

public class DataProducts {
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("object")
    @Expose
    private List<ProductInfo> object = null;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ProductInfo> getObject() {
        return object;
    }

    public void setObject(List<ProductInfo> object) {
        this.object = object;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }
}
