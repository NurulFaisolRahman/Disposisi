package com.example.shin.disposisi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText nama, sandi;
    Button Login;
    String Nama, Sandi;
    public static ApiInterfaceLogin apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        cekSudahLogin();

        nama = findViewById(R.id.nama);
        sandi = findViewById(R.id.sandi);
        Login = findViewById(R.id.Login);

        apiInterface = ApiClientLogin.GetApiClient().create(ApiInterfaceLogin.class);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nama = nama.getText().toString();
                Sandi = sandi.getText().toString();
            Call<User> call = apiInterface.performUserLogin(Nama,Sandi);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body().getResponse().equals("sukses")){
                        simpanDataLogin(Nama, Sandi,response.body().getTinkatan());
                        if (response.body().getTinkatan().equals("operator")){
                            Intent operator = new Intent(Login.this, Operator.class);
                            startActivity(operator);
                            finish();
                        }
                        else if (response.body().getTinkatan().equals("kadis")){
                            Intent kadis = new Intent(Login.this, Kadis.class);
                            startActivity(kadis);
                            finish();
                        }
                    }
                    else{
                        Toast.makeText(Login.this, "Nama/Sandi Salah ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
            }
        });
    }

    private void cekSudahLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String level = sharedPreferences.getString("level","");
        if (!level.isEmpty()){
            if (level.equals("operator")){
                Intent operator = new Intent(Login.this, Operator.class);
                startActivity(operator);
                finish();
            }
            else if (level.equals("kadis")){
                Intent kadis = new Intent(Login.this, Kadis.class);
                startActivity(kadis);
                finish();
            }
        }
    }

    private void simpanDataLogin(String Nama, String Sandi, String level){
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Nama", Nama);
        editor.putString("Sandi", Sandi);
        editor.putString("level", level);
        editor.commit();
    }
}
