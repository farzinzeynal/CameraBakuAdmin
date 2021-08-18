package com.farzinzeynal.camerabakuadmin.ui.retrofit.model.dto;

import com.google.gson.annotations.SerializedName;

public class ItemDto
{


    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("price")
    public String price;

    @SerializedName("photo")
    public byte[] photo;

    @SerializedName("item_type")
    public String item_type;

    @SerializedName("rating")
    public String rating;

    @SerializedName("created_date")
    public String created_date;

    @SerializedName("active")
    public Boolean active;

    public ItemDto() {
    }

    public ItemDto(String id, String title, String price, byte[] photo, String item_type, String rating, String created_date, Boolean active) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.photo = photo;
        this.item_type = item_type;
        this.rating = rating;
        this.created_date = created_date;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
