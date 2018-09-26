package com.example.shin.disposisi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Arsip_Operator extends Fragment {

    View v;
    private RecyclerView RV_Arsip_Operator;
    private List<Daftar_Arsip_Operator> DataArsipOperator;

    public Arsip_Operator() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.arsip_operator,container,false);
        RV_Arsip_Operator = v.findViewById(R.id.RV_ArsipOperator);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataArsipOperator = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiDisposisiKadis.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiArsipOperator apiArsipOperator = retrofit.create(ApiArsipOperator.class);
        Call<List<Daftar_Arsip_Operator>> call = apiArsipOperator.getData();

        call.enqueue(new Callback<List<Daftar_Arsip_Operator>>() {
            @Override
            public void onResponse(Call<List<Daftar_Arsip_Operator>> call, Response<List<Daftar_Arsip_Operator>> response) {
                DataArsipOperator = response.body();
                RV_Adapter_Arsip_Operator RV_adapter = new RV_Adapter_Arsip_Operator(getContext(), DataArsipOperator);
                RV_Arsip_Operator.setLayoutManager(new LinearLayoutManager(getActivity()));
                RV_Arsip_Operator.setAdapter(RV_adapter);
            }

            @Override
            public void onFailure(Call<List<Daftar_Arsip_Operator>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
