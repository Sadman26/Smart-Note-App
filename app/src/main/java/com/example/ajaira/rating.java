package com.example.ajaira;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class rating extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText review;
    RatingBar rating;
    Button submit;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent intent2=new Intent(rating.this,homepage.class);
                startActivity(intent2);
                finish();
                return true;
            case R.id.notes:
                Intent intent1=new Intent(rating.this,MainActivity.class);
                startActivity(intent1);
                finish();
                return true;
            case R.id.logout:
                mAuth.signOut();
                Intent intent=new Intent(rating.this,login.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        review = findViewById(R.id.reviewtxt);
        rating = findViewById(R.id.ratingtxt);
        submit= findViewById(R.id.reviewsubmitbtn);
        //title
        getSupportActionBar().setTitle("Review & Rating");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reviewtxt = review.getText().toString();
                float ratingtxt = rating.getRating();
                String ratingtxt1 = String.valueOf(ratingtxt);
                if(reviewtxt.isEmpty()){
                    review.setError("Please enter your review");
                    review.requestFocus();
                    return;
                }
                if(ratingtxt==0){
                    Toast.makeText(rating.this, "Please Give Atleast One Star", Toast.LENGTH_SHORT).show();
                    rating.requestFocus();
                    return;
                }
                database.getReference().child("User").child(mAuth.getCurrentUser().getUid()).child("Others").child("Review").setValue(reviewtxt);
                database.getReference().child("User").child(mAuth.getCurrentUser().getUid()).child("Others").child("Rating").setValue(ratingtxt);
                Toast.makeText(rating.this, "Thank You For Your Review", Toast.LENGTH_SHORT).show();
                review.setText("");
                rating.setRating(0);
            }
        });
    }
}