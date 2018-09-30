package com.example.shin.disposisi.FileOperator;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shin.disposisi.R;

public class DetailSurat extends AppCompatActivity {

    EditText SuratDari,TanggalSurat,NomorSurat,DiterimaTanggal,NomorAgenda,Sifat,Perihal;
    Button Cetak;
    DownloadManager Download;

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
        Cetak = findViewById(R.id.Cetak);

        SuratDari.setText(" : "+RV_Adapter_Arsip_Operator.SuratDari);
        TanggalSurat.setText(" : "+RV_Adapter_Arsip_Operator.TanggalSurat);
        NomorSurat.setText(" : "+RV_Adapter_Arsip_Operator.NomorSurat);
        DiterimaTanggal.setText(" : "+RV_Adapter_Arsip_Operator.DiterimaTanggal);
        NomorAgenda.setText(" : "+RV_Adapter_Arsip_Operator.NomorAgenda);
        Sifat.setText(" : "+RV_Adapter_Arsip_Operator.Sifat);
        Perihal.setText(" : "+RV_Adapter_Arsip_Operator.Perihal);

        Cetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Download = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("http://192.168.43.223/disposisi/CetakPDF.php?NomorSurat="+RV_Adapter_Arsip_Operator.NomorSurat);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Disposisi_"+RV_Adapter_Arsip_Operator.NomorSurat+".pdf");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Download.enqueue(request);
            }
        });


    }
}
