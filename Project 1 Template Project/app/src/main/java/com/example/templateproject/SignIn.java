package com.example.templateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    EditText firstname, lastname, email, password, retypepassword;
    Button signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firstname = (EditText) findViewById(R.id.FirstName);
        lastname = (EditText) findViewById(R.id.LastName);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
        retypepassword = (EditText) findViewById(R.id.Password2);
        signin = (Button) findViewById(R.id.SignInBtn);
        DB = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String FirstName = firstname.getText().toString();
                String LastName = lastname.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String Password2 = retypepassword.getText().toString();

                if (firstname.equals("") || lastname.equals("") || email.equals("") || password.equals("") || retypepassword.equals(""))
                    Toast.makeText(SignIn.this, "Fill All Fields !!!", Toast.LENGTH_SHORT).show();
                else
                    if (Password.equals(Password2)){
                        Boolean checkemail = DB.checkemail(email);
                        if (checkemail==false){
                            Boolean insert = DB.insertData(FirstName, LastName, Email, Password);
                            if (insert==true){
                                Toast.makeText(SignIn.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignIn.this, Login.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(SignIn.this, "Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            Toast.makeText(SignIn.this, "User Already Inserted!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignIn.this, "Password not match!", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}