package com.example.ajaira;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    EditText email,password;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = findViewById(R.id.usrnewemail);
        password = findViewById(R.id.usrnewpass);
        signup= findViewById(R.id.signupbtn);
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
    }
}