package com.example.shin.disposisi.FileSeksi;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.example.shin.disposisi.R;
import com.example.shin.disposisi.Surat;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Seksi extends AppCompatActivity {

    private RecyclerView RV_Seksi;
    private List<Surat> DataSeksi;
    SwipeRefreshLayout Rafresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seksi);
        RV_Seksi = findViewById(R.id.RV_Seksi);
        Rafresh = findViewById(R.id.RafreshSeksi);
        Rafresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TampilkanSurat();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rafresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        TampilkanSurat();
    }

    private void TampilkanSurat(){
        DataSeksi = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSeksi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiSeksi apiSeksi = retrofit.create(ApiSeksi.class);
        Call<List<Surat>> call = apiSeksi.getData();

        call.enqueue(new Callback<List<Surat>>() {
            @Override
            public void onResponse(Call<List<Surat>> call, Response<List<Surat>> response) {
                DataSeksi = response.body();
                RV_Adapter_Seksi RV_adapter = new RV_Adapter_Seksi(Seksi.this, DataSeksi);
                RV_Seksi.setLayoutManager(new LinearLayoutManager(Seksi.this));
                RV_Seksi.setAdapter(RV_adapter);
            }

            @Override
            public void onFailure(Call<List<Surat>> call, Throwable t) {
                Toast.makeText(Seksi.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
