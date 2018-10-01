package com.example.shin.disposisi.FileKadis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.shin.disposisi.FileLogin.ApiClientLogin;
import com.example.shin.disposisi.R;
import com.example.shin.disposisi.KadisMendisposisi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Disposisi_Surat extends AppCompatActivity {

    String NomorSurat;
    Button KadisMendisposisi;
    EditText IsiDisposisiKadis;
    RadioGroup Bidang;
    RadioButton NamaBidang;
    public static ApiMendisposisi apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disposisi_surat);

        NomorSurat = RV_Adapter_Disposisi_Kadis.NomorSurat;
        KadisMendisposisi = findViewById(R.id.KadisMendisposisi);
        IsiDisposisiKadis = findViewById(R.id.IsiDisposisiKadis);
        Bidang = findViewById(R.id.Bidang);
        apiInterface = ApiClientLogin.GetApiClient().create(ApiMendisposisi.class);

        Bidang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                NamaBidang = findViewById(Bidang.getCheckedRadioButtonId());
            }
        });

        KadisMendisposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IsiDisposisi = IsiDisposisiKadis.getText().toString();
                String BidangTujuan = "";
                if (NamaBidang.getText().equals("Sekretaris")){
                    BidangTujuan = "sekretaris";
                }
                else if (NamaBidang.getText().equals("Bidang IKP")){
                    BidangTujuan = "ikp";
                }
                else if (NamaBidang.getText().equals("Bidang Aplikasi dan Informatika")){
                    BidangTujuan = "aptika";
                }
                else if (NamaBidang.getText().equals("Bidang SD-TIK")){
                    BidangTujuan = "sts";
                }
                Call<KadisMendisposisi> call = apiInterface.Mendisposisi(NomorSurat,BidangTujuan,IsiDisposisi);
                call.enqueue(new Callback<KadisMendisposisi>() {
                    @Override
                    public void onResponse(Call<KadisMendisposisi> call, Response<KadisMendisposisi> response) {
                        if (response.body().getRespon().equals("sukses")){
                            Toast.makeText(Disposisi_Surat.this, "Sukses", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<KadisMendisposisi> call, Throwable t) {
                        Toast.makeText(Disposisi_Surat.this, "Mohon Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
