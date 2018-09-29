package com.example.shin.disposisi.FileSeksi;

import com.example.shin.disposisi.Surat;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiSeksi {
    String BASE_URL = "http://192.168.43.223/disposisi/";
    @GET("seksi.php")
    Call<List<Surat>> getData();
}
