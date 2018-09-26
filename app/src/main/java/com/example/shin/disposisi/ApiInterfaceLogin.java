package com.example.shin.disposisi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceLogin {
    @GET("login.php")
    Call<User> performUserLogin(@Query("nama") String nama, @Query("sandi") String sandi);
}
