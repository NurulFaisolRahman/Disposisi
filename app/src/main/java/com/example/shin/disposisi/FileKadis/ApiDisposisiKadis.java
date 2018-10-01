package com.example.shin.disposisi.FileKadis;

import com.example.shin.disposisi.Server;
import com.example.shin.disposisi.Surat;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiDisposisiKadis {
    String BASE_URL = Server.IP;
    @GET("disposisi.php")
    Call<List<Surat>> getData();
}
