package com.example.shin.disposisi.FileBidang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.shin.disposisi.R;

public class Arsip_Bidang extends Fragment {

    View v;

    public Arsip_Bidang(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.arsip_bidang,container,false);
        return v;
    }
}
