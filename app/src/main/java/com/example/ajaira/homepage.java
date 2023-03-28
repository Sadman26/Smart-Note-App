package com.example.ajaira;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class homepage extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    TextView txt;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        txt=findViewById(R.id.txt);
        txt.setText(auth.getCurrentUser().getEmail());
    }
}