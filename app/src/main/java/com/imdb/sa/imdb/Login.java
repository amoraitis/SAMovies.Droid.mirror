package com.imdb.sa.imdb;
import com.imdb.sa.helpers.SaveSharedPreference;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private final String username = "user", password="pass";
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
}
