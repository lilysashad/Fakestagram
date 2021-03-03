package com.example.fakestagram;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import android.content.Intent;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    public static final String TAG = "LoginActivity";
    public EditText etUsername;
    public EditText etPassword;
    public Button btnLogin;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //if user has already signed in, and reopens app- no login screen, just main activity launch
        if(ParseUser.getCurrentUser() != null){

            goMainActivity();

        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);

            }

        });

    }

    private void loginUser(String username, String password){

        Log.i(TAG, "Attempting to log in user " + username);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if(e != null){

                    Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();

                    Log.e(TAG, "Issue with login", e);

                    return;

                }

                //navigate to main activity if user correctly signs in
                goMainActivity();

            }
        });
    }

    public void goMainActivity(){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish(); //once logged in, no returning to login activity

    }

}
