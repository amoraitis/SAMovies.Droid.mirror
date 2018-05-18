package com.imdb.sa.imdb;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.imdb.sa.Model.SAMoviesAPIService;
import com.imdb.sa.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Signup extends AppCompatActivity {
    private static final String TAG = Signup.class.getSimpleName();
    private static Retrofit retrofit = null;
    private String usernameText;
    private EditText username, password, rePassword, name;
    Button chechAvailability, signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=(EditText) findViewById(R.id.userSignup);
        chechAvailability = (Button) findViewById(R.id.checkAvailabilityBtn);
        password = (EditText) findViewById(R.id.passSingup);
        rePassword = (EditText) findViewById(R.id.passReSignup);
        name = (EditText) findViewById(R.id.nameSignup);
        signUpButton = (Button) findViewById(R.id.butSignup);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usernameText = s.toString();
                if(chechAvailability.getVisibility()== View.INVISIBLE){
                    chechAvailability.setVisibility(View.VISIBLE);
                    password.setVisibility(View.INVISIBLE);
                    rePassword.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.INVISIBLE);
                    signUpButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        chechAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameText.equals("")){
                    Toast.makeText(getApplicationContext(), "Write something!", Toast.LENGTH_SHORT).show();
                }else{
                    userExist();
                }


            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(password.getText().toString().equals(rePassword.getText().toString())){
                    createUser();
                }else{
                    Toast.makeText(getApplicationContext(), "Passwords dont match!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void userExist(){
        configureRetrofit();
        String username_Text = username.getText().toString();
        SAMoviesAPIService saMoviesAPIService = retrofit.create(SAMoviesAPIService.class);
        Call<User> call = saMoviesAPIService.userExist(username_Text);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
                }else{
                    chechAvailability.setVisibility(View.INVISIBLE);
                    password.setVisibility(View.VISIBLE);
                    rePassword.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    signUpButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Couldn't perform action!",Toast.LENGTH_LONG).show();
            }
        });
    }


    private void createUser() {
        configureRetrofit();
        Gson gson = new Gson();
        String user = gson.toJson(new User(
                username.getText().toString(),
                password.getText().toString(),
                name.getText().toString()
        ));

        SAMoviesAPIService saMoviesAPIService= retrofit.create(SAMoviesAPIService.class);
        Call<User> call = saMoviesAPIService.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getApplicationContext(), response.body()==null? "" : response.body().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Couldn't perform action!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void configureRetrofit(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SAMoviesAPIService.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

}
