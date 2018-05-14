package com.imdb.sa.imdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {
    private static final String TAG = Signup.class.getSimpleName();
    public static final String BASE_URL = "http://samoviesapi20180427042140.azurewebsites.net/api/users/";
    private static Retrofit retrofit = null;
    private EditText username, password, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=(EditText) findViewById(R.id.userSignup);
        password = (EditText) findViewById(R.id.passSingup);
        name = (EditText) findViewById(R.id.nameSignup);
        Button signUpButton = (Button) findViewById(R.id.butSignup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        JSONObject user = new JSONObject();
        try{
            user.put("username",username.getText().toString());
            user.put("password",password.getText().toString());
            user.put("name",name.getText().toString());
        }catch(JSONException e){


        }

        SAMoviesAPIService saMoviesAPIService= retrofit.create(SAMoviesAPIService.class);
        Call<User> call = saMoviesAPIService.createUser(user.toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("REQUEST USER FAILURE", t.getLocalizedMessage());
            }
        });
    }
}
