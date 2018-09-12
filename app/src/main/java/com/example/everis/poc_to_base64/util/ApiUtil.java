package com.example.everis.poc_to_base64.util;

import com.example.everis.poc_to_base64.network.RetrofitClient;
import com.example.everis.poc_to_base64.network.Service;

public class ApiUtil {
    public static final String BASE_URL ="https://demo0473494.mockable.io/";
    public static Service getService(){
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
