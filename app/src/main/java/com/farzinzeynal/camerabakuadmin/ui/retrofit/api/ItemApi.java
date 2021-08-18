package com.farzinzeynal.camerabakuadmin.ui.retrofit.api;

import com.farzinzeynal.camerabakuadmin.ui.retrofit.model.ItemRequest;
import com.farzinzeynal.camerabakuadmin.ui.retrofit.model.dto.ItemDto;

import java.util.Map;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ItemApi
{
    @POST("post_item")
    Call<ItemDto> createItem(@Body ItemRequest itemRequest);

}
