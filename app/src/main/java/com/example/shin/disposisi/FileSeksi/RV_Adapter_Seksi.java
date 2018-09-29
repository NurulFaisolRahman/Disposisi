package com.example.shin.disposisi.FileSeksi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.shin.disposisi.R;
import com.example.shin.disposisi.Surat;
import java.util.List;

public class RV_Adapter_Seksi extends RecyclerView.Adapter<RV_Adapter_Seksi.ViewHolderSeksi>{

    private Context ContextSeksi;
    private List<Surat> DataSeksi;

    public RV_Adapter_Seksi(Context contextSeksi, List<Surat> dataSeksi) {
        ContextSeksi = contextSeksi;
        DataSeksi = dataSeksi;
    }

    @NonNull
    @Override
    public ViewHolderSeksi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ContextSeksi).inflate(R.layout.daftar_surat_seksi,parent,false);
        return new ViewHolderSeksi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSeksi holder, int position) {
        holder.SuratDari.setText("Surat dari : "+DataSeksi.get(position).getSurat_dari());
        holder.NomorSurat.setText("Nomor Surat : "+DataSeksi.get(position).getNomor_surat());
        holder.TanggalSurat.setText("Tanggal Surat : "+DataSeksi.get(position).getTanggal_surat());
        holder.DiterimaTanggal.setText("Diterima Tanggal : "+DataSeksi.get(position).getDiterima_tanggal());
        holder.NomorAgenda.setText("Nomor Agenda : "+DataSeksi.get(position).getNomor_agenda());
        holder.Sifat.setText("Sifat : "+DataSeksi.get(position).getSifat());
        holder.Perihal.setText("Perihal : "+DataSeksi.get(position).getPerihal());
    }

    @Override
    public int getItemCount() {
        return DataSeksi.size();
    }

    public static class ViewHolderSeksi extends RecyclerView.ViewHolder {

        TextView SuratDari,NomorSurat,TanggalSurat,DiterimaTanggal,NomorAgenda,Sifat,Perihal;
        LinearLayout Seksi;

        public ViewHolderSeksi(View ItemView){
            super(ItemView);

            Seksi = ItemView.findViewById(R.id.Seksi);

            SuratDari = ItemView.findViewById(R.id.SeksiSuratDari);
            NomorSurat = ItemView.findViewById(R.id.SeksiNomorSurat);
            TanggalSurat = ItemView.findViewById(R.id.SeksiTanggalSurat);
            DiterimaTanggal= ItemView.findViewById(R.id.SeksiDiterimaTanggal);
            NomorAgenda = ItemView.findViewById(R.id.SeksiNomorAgenda);
            Sifat = ItemView.findViewById(R.id.SeksiSifat);
            Perihal = ItemView.findViewById(R.id.SeksiPerihal);
        }
    }
}
