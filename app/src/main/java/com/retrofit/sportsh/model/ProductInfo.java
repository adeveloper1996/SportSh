package com.retrofit.sportsh.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProductInfo extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("img1")
    @Expose
    private String img1;
    @SerializedName("img2")
    @Expose
    private String img2;
    @SerializedName("img3")
    @Expose
    private String img3;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("composition")
    @Expose
    private String composition;
    @SerializedName("is_favourite")
    @Expose
    private int isFavorite;

    private boolean isPress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg1() {
        return "http://samalbek.bnm.kz" + img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return "http://samalbek.bnm.kz" + img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return "http://samalbek.bnm.kz" + img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isPress() {
        return isPress;
    }

    public void setPress(boolean press) {
        isPress = press;
    }
}
