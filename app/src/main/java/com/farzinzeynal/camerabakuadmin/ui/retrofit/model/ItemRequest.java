package com.farzinzeynal.camerabakuadmin.ui.retrofit.model;


import com.google.gson.annotations.SerializedName;

public class ItemRequest
{
    @SerializedName("title")
    public String title;

    @SerializedName("price")
    public Long price;

    @SerializedName("photo")
    public String photo;

    @SerializedName("item_type")
    public String item_type;

    @SerializedName("rating")
    public Float rating;

    @SerializedName("created_date")
    public String created_date;

    public ItemRequest() {
    }

    public ItemRequest(String title, Long price, String photo, String item_type, Float rating, String created_date) {
        this.title = title;
        this.price = price;
        this.photo = photo;
        this.item_type = item_type;
        this.rating = rating;
        this.created_date = created_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
