package com.example.shin.disposisi.FileLogin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientLogin {
    private static final String BASE_URL = "http://192.168.43.223/disposisi/";
    private static Retrofit retrofit = null;

    public static Retrofit GetApiClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
