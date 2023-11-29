package com.appdet.quizhub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.appdet.quizhub.SignUp;
import com.appdet.quizhub.homepage;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    Button btnLogin, btnSignup;
    TextInputEditText etLoginUsername, etLoginPassword;
    private DBHelper DBHelper;
    String username_holder;
    private SystemBars systemBars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // to call the SystemBars class that immerse the screen and reveal if swiped up
        systemBars = new SystemBars(this);
        systemBars.enableSwipeToToggleSystemBars();

        btnSignup = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);

        DBHelper = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etLoginUsername.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();
                boolean isValid = DBHelper.checkUser(username, password);


                if (isValid) {
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();

                    username_holder = etLoginUsername.getText().toString().trim();

                    Intent intent = new Intent(Login.this, homepage.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Account not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignUp activity
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });



    }



}