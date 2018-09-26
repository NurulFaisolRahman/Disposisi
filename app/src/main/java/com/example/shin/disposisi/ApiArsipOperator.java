package com.example.shin.disposisi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiArsipOperator {

    String BASE_URL = "http://192.168.43.223/disposisi/";

    @GET("ArsipOperator.php")
    Call<List<Daftar_Arsip_Operator>> getData();
}
