package com.example.shin.disposisi.FileOperator;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class Arsip_Operator extends Fragment {

    View v;
    private RecyclerView RV_Arsip_Operator;
    private List<Surat> DataArsipOperator;
    SwipeRefreshLayout Rafresh;

    public Arsip_Operator() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.arsip_operator,container,false);
        RV_Arsip_Operator = v.findViewById(R.id.RV_ArsipOperator);
        Rafresh = v.findViewById(R.id.RafreshOperator);
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TampilkanArsip();
    }

    private void TampilkanArsip(){
        DataArsipOperator = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiArsipOperator.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiArsipOperator apiArsipOperator = retrofit.create(ApiArsipOperator.class);
        Call<List<Surat>> call = apiArsipOperator.getData();

        call.enqueue(new Callback<List<Surat>>() {
            @Override
            public void onResponse(Call<List<Surat>> call, Response<List<Surat>> response) {
                DataArsipOperator = response.body();
                RV_Adapter_Arsip_Operator RV_adapter = new RV_Adapter_Arsip_Operator(getContext(), DataArsipOperator);
                RV_Arsip_Operator.setLayoutManager(new LinearLayoutManager(getActivity()));
                RV_Arsip_Operator.setAdapter(RV_adapter);
            }

            @Override
            public void onFailure(Call<List<Surat>> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
