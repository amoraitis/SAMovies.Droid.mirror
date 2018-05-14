package com.imdb.sa.imdb;
import com.imdb.sa.Model.SAMoviesAPIService;
import com.imdb.sa.Model.User;
import com.imdb.sa.helpers.SaveSharedPreference;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.UnknownServiceException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private final String username = "user", password="pass";
    private static final String TAG = Login.class.getSimpleName();
    public static final String BASE_URL = "http://samoviesapi20180427042140.azurewebsites.net/api/users/";
    private static Retrofit retrofit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText userNameText = (EditText) findViewById(R.id.userLogin);
        final EditText passWordText = (EditText) findViewById(R.id.passLogin);
        Button signupButLogin = (Button) findViewById(R.id.signupButLogin);
        signupButLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });

        Button signinButLogin = (Button) findViewById(R.id.signinButLogin);
        signinButLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(userNameText.getText().toString().equals(username) && passWordText.getText().toString().equals(password)){
                    Intent i = new Intent(Login.this, Home.class);
                    SaveSharedPreference.setUserName(getApplicationContext(),userNameText.getText().toString());
                    startActivity(i);
                }
                else{
                    Toast.makeText(Login.this, "Username or password not found", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    private void connectAndGetUser(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        SAMoviesAPIService saMoviesAPIService = retrofit.create(SAMoviesAPIService.class);
        Call<User> call = saMoviesAPIService.getUser(1);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Intent i = new Intent(Login.this, Home.class);
                SaveSharedPreference.setUserName(getApplicationContext(),response.body().toString());
                startActivity(i);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Cannot sign in", Toast.LENGTH_LONG);
            }
        });
    }
}
