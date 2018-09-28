package com.example.shin.disposisi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RV_Adapter_Arsip_Operator extends RecyclerView.Adapter<RV_Adapter_Arsip_Operator.ViewHolderArsipOperator>{

    Context ContextArsipOperator;
    List<Surat> DataArsipOperator;

    public RV_Adapter_Arsip_Operator(Context contextArsipOperator, List<Surat> dataArsipOperator) {
        ContextArsipOperator = contextArsipOperator;
        DataArsipOperator = dataArsipOperator;
    }

    @NonNull
    @Override
    public ViewHolderArsipOperator onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ContextArsipOperator).inflate(R.layout.daftar_arsip_operator,parent,false);
        final ViewHolderArsipOperator viewHolderArsipOperator = new ViewHolderArsipOperator(view);

        return viewHolderArsipOperator;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArsipOperator holder, int position) {
        holder.SuratDari.setText("Surat dari : "+DataArsipOperator.get(position).getSurat_dari());
        holder.NomorSurat.setText("Nomor Surat : "+DataArsipOperator.get(position).getNomor_surat());
        holder.TanggalSurat.setText("Tanggal Surat : "+DataArsipOperator.get(position).getTanggal_surat());
        holder.DiterimaTanggal.setText("Diterima Tanggal : "+DataArsipOperator.get(position).getDiterima_tanggal());
        holder.NomorAgenda.setText("Nomor Agenda : "+DataArsipOperator.get(position).getNomor_agenda());
        holder.Sifat.setText("Sifat : "+DataArsipOperator.get(position).getSifat());
        holder.Perihal.setText("Perihal : "+DataArsipOperator.get(position).getPerihal());
    }

    @Override
    public int getItemCount() {
        return DataArsipOperator.size();
    }

    public static class ViewHolderArsipOperator extends RecyclerView.ViewHolder {

        TextView SuratDari,NomorSurat,TanggalSurat,DiterimaTanggal,NomorAgenda,Sifat,Perihal;
        LinearLayout ArsipOperator;

        public ViewHolderArsipOperator(View ItemView){
            super(ItemView);

            ArsipOperator = ItemView.findViewById(R.id.ArsipOperator);

            SuratDari = ItemView.findViewById(R.id.ArsipOperatorSuratDari);
            NomorSurat = ItemView.findViewById(R.id.ArsipOperatorNomorSurat);
            TanggalSurat = ItemView.findViewById(R.id.ArsipOperatorTanggalSurat);
            DiterimaTanggal= ItemView.findViewById(R.id.ArsipOperatorDiterimaTanggal);
            NomorAgenda = ItemView.findViewById(R.id.ArsipOperatorNomorAgenda);
            Sifat = ItemView.findViewById(R.id.ArsipOperatorSifat);
            Perihal = ItemView.findViewById(R.id.ArsipOperatorPerihal);
        }
    }
}
