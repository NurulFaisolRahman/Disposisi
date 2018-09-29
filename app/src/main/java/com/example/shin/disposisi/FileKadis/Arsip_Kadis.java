package com.example.shin.disposisi.FileKadis;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shin.disposisi.R;

public class Arsip_Kadis extends Fragment{

    View v;

    public Arsip_Kadis(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.arsip_kadis,container,false);
        return v;
    }
}
