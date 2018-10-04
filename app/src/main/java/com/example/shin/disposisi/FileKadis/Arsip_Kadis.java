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
import com.example.shin.disposisi.R;
import com.example.shin.disposisi.Surat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Arsip_Kadis extends Fragment{

    View v;
    private RecyclerView RV_Arsip_Kadis;
    private List<Surat> DataArsipKadis;
    SwipeRefreshLayout Rafresh;

    public Arsip_Kadis(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.arsip_kadis,container,false);
        RV_Arsip_Kadis = v.findViewById(R.id.RV_ArsipKadis);
        Rafresh = v.findViewById(R.id.RafreshArsipKadis);
        Rafresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TampilkanArsip();
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

    private void TampilkanArsip() {
        DataArsipKadis = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiArsipKadis.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiArsipKadis apiArsipKadis = retrofit.create(ApiArsipKadis.class);
        Call<List<Surat>> call = apiArsipKadis.getData();

        call.enqueue(new Callback<List<Surat>>() {
            @Override
            public void onResponse(Call<List<Surat>> call, Response<List<Surat>> response) {
                DataArsipKadis = response.body();
                RV_Adapter_Arsip_Kadis RV_adapter = new RV_Adapter_Arsip_Kadis(getContext(), DataArsipKadis);
                RV_Arsip_Kadis.setLayoutManager(new LinearLayoutManager(getActivity()));
                RV_Arsip_Kadis.setAdapter(RV_adapter);
            }

            @Override
            public void onFailure(Call<List<Surat>> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
