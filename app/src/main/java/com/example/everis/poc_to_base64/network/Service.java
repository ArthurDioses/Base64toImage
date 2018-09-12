package com.example.everis.poc_to_base64.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("image")
    Call<ImageResponse>getImages();
}
