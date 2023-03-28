package com.example.ajaira;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    RatingBar ratingBar;
    FloatingActionButton fab;
    Switch aSwitch;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingBar = findViewById(R.id.rate);
        fab = findViewById(R.id.floatx);
        aSwitch = findViewById(R.id.switchx);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                database.getReference().child("Other").child("rating").setValue(v);
                Toast.makeText(MainActivity.this, "Rating: " + v, Toast.LENGTH_SHORT).show();
            }
        });
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch.isChecked()) {
                    Toast.makeText(MainActivity.this, "Switch is on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Switch is off", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pressed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}