package com.example.ajaira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    EditText email,password;
    Button signup;
    ImageView signinlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Signup");
        setContentView(R.layout.activity_signup);
        email = findViewById(R.id.usrnewemail);
        password = findViewById(R.id.usrnewpass);
        signup= findViewById(R.id.signupbtn);
        signinlink = findViewById(R.id.linktosignin);
        signup.setOnClickListener(v -> {
            String email1 = email.getText().toString();
            String password1 = password.getText().toString();
            auth.createUserWithEmailAndPassword(email1,password1);
            //if created successfully then toast
            if(auth.getCurrentUser()!=null)
            {
                Toast.makeText(signup.this, "User Created", Toast.LENGTH_SHORT).show();
                email.setText("");
                password.setText("");
            }
            else{
                Toast.makeText(signup.this, "User not Created", Toast.LENGTH_SHORT).show();
            }
        });
        signinlink.setOnClickListener(v -> {
            Intent intent = new Intent(signup.this,login.class);
            startActivity(intent);
        });
    }
}