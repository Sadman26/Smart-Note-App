package com.example.ajaira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    EditText email,password;
    Button signinbtn;
    ImageView linktosignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.usremail);
        password = findViewById(R.id.usrpass);
        signinbtn = findViewById(R.id.signinbtn);
        linktosignup = findViewById(R.id.linktosignup);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //signin with email and password firebase
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                auth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(login.this,task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(login.this, "Login Done!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        linktosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });
    }
}