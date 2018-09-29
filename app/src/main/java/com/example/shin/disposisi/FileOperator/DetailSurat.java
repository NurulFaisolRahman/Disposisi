package com.example.shin.disposisi.FileOperator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.shin.disposisi.R;

public class DetailSurat extends AppCompatActivity {

    EditText SuratDari,TanggalSurat,NomorSurat,DiterimaTanggal,NomorAgenda,Sifat,Perihal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_surat);

        SuratDari = findViewById(R.id.surat_dari);
        TanggalSurat = findViewById(R.id.tanggal_surat);
        NomorSurat = findViewById(R.id.nomor_surat);
        DiterimaTanggal = findViewById(R.id.diterima_tanggal);
        NomorAgenda = findViewById(R.id.nomor_agenda);
        Sifat = findViewById(R.id.sifat);
        Perihal = findViewById(R.id.perihal);

        SuratDari.setText(" : "+RV_Adapter_Arsip_Operator.SuratDari);
        TanggalSurat.setText(" : "+RV_Adapter_Arsip_Operator.TanggalSurat);
        NomorSurat.setText(" : "+RV_Adapter_Arsip_Operator.NomorSurat);
        DiterimaTanggal.setText(" : "+RV_Adapter_Arsip_Operator.DiterimaTanggal);
        NomorAgenda.setText(" : "+RV_Adapter_Arsip_Operator.NomorAgenda);
        Sifat.setText(" : "+RV_Adapter_Arsip_Operator.Sifat);
        Perihal.setText(" : "+RV_Adapter_Arsip_Operator.Perihal);
    }
}
