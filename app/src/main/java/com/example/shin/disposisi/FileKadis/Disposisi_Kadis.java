package com.example.shin.disposisi.FileKadis;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Disposisi_Kadis extends Fragment{

    View v;
    private RecyclerView RV_Disposisi_Kadis;
    private List<Surat> DataDisposisiKadis;
    SwipeRefreshLayout Rafresh;

    public Disposisi_Kadis(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.disposisi_kadis,container,false);
        RV_Disposisi_Kadis = v.findViewById(R.id.RV_DisposisiKadis);
        Rafresh = v.findViewById(R.id.RafreshKadis);
        Rafresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TampilkanDisposisi();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rafresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TampilkanDisposisi();
    }

    private void TampilkanDisposisi(){
        DataDisposisiKadis = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiDisposisiKadis.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiDisposisiKadis apiDisposisiKadis = retrofit.create(ApiDisposisiKadis.class);
        Call<List<Surat>> call = apiDisposisiKadis.getData();

        call.enqueue(new Callback<List<Surat>>() {
            @Override
            public void onResponse(Call<List<Surat>> call, Response<List<Surat>> response) {
                DataDisposisiKadis = response.body();
                RV_Adapter_Disposisi_Kadis RV_adapter = new RV_Adapter_Disposisi_Kadis(getContext(),DataDisposisiKadis);
                RV_Disposisi_Kadis.setLayoutManager(new LinearLayoutManager(getActivity()));
                RV_Disposisi_Kadis.setAdapter(RV_adapter);
            }

            @Override
            public void onFailure(Call<List<Surat>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
