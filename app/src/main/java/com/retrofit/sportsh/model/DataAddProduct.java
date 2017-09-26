package com.retrofit.sportsh.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataAddProduct {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("object")
    @Expose
    private List<String> object = null;
    @SerializedName("errors")
    @Expose
    private List<String> errors = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getObject() {
        return object;
    }

    public void setObject(List<String> object) {
        this.object = object;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}