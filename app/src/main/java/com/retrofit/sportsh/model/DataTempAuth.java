package com.retrofit.sportsh.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nursultan on 27.06.2017.
 */

public class DataTempAuth {

    private int id;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("object")
    @Expose
    private InfoTemp tempId = null;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public InfoTemp getTempId() {
        return tempId;
    }

    public void setTempId(InfoTemp tempId) {
        this.tempId = tempId;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }
}
