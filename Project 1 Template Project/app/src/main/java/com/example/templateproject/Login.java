package com.example.templateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button login, sign_btn2;
    EditText username, password;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.LoginBtn);
        sign_btn2 = (Button) findViewById(R.id.Signin_Btn2);
        username = (EditText) findViewById(R.id.LoginUserName);
        password = (EditText) findViewById(R.id.LoginPass);
        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });

        sign_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });

    }
    public void login(){

        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (user.equals("Admin") && pass.equals("Chamara")) {
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
        }
        else if (user.equals("") || pass.equals(""))
            Toast.makeText(Login.this, "Enter All Fields!", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkemailpassword(user, pass);
                 if (checkuserpass == true) {
                    Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                }

                    else {
                        Toast.makeText(Login.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                    }
        }

    }


}