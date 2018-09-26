package com.example.shin.disposisi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Operator extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapterOperator adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operator);

        tabLayout = findViewById(R.id.TabFragmentUmum);
        viewPager = findViewById(R.id.ViewPagerUmum);
        adapter = new ViewPagerAdapterOperator(getSupportFragmentManager());

        adapter.AddFragment(new Disposisi_Operator(),"Disposisi");
        adapter.AddFragment(new Arsip_Operator(),"Arsip");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tentang:
                Toast.makeText(this, "Aplikasi versi 0.0.1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                hapusDataLogin();
                pindahActivity();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void hapusDataLogin(){
        SharedPreferences sharedPreferences =getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private void pindahActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
