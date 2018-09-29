package com.example.shin.disposisi.FileBidang;

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

public class Disposisi_Bidang extends Fragment{

    View v;
    private RecyclerView RV_Disposisi_Bidang;
    private List<Surat> DataDisposisiBidang;
    SwipeRefreshLayout Rafresh;

    public Disposisi_Bidang(){

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.disposisi_bidang,container,false);
        RV_Disposisi_Bidang = v.findViewById(R.id.RV_DisposisiBidang);
        Rafresh = v.findViewById(R.id.RafreshBidang);
        Rafresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TampilkanBidang();
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
        TampilkanBidang();
    }

    private void TampilkanBidang(){
        DataDisposisiBidang = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiDisposisiBidang.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiDisposisiBidang apiDisposisiBidang = retrofit.create(ApiDisposisiBidang.class);
        Call<List<Surat>> call = apiDisposisiBidang.getData();

        call.enqueue(new Callback<List<Surat>>() {
            @Override
            public void onResponse(Call<List<Surat>> call, Response<List<Surat>> response) {
                DataDisposisiBidang = response.body();
                RV_Adapter_Disposisi_Bidang RV_adapter = new RV_Adapter_Disposisi_Bidang(getContext(),DataDisposisiBidang);
                RV_Disposisi_Bidang.setLayoutManager(new LinearLayoutManager(getActivity()));
                RV_Disposisi_Bidang.setAdapter(RV_adapter);
            }

            @Override
            public void onFailure(Call<List<Surat>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
