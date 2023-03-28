package com.example.ajaira;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class homepage extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    FloatingActionButton micbtn;
    EditText txt;
    Button savebtn;
    String current_time= DateFormat.getDateTimeInstance().format(new Date());
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        //title
        getSupportActionBar().setTitle("Homepage");
        micbtn=findViewById(R.id.micbtn);
        txt=findViewById(R.id.txtt);
        micbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
        savebtn=findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.getReference().child("User").child(auth.getCurrentUser().getUid()).child("Notes").child(current_time).setValue(txt.getText().toString());
                //if save toast
                Toast.makeText(homepage.this, "Saved", Toast.LENGTH_SHORT).show();
                txt.setText("");
            }
        });
    }
    private void speak() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"en-US");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Now");
        try{
            startActivityForResult(intent,1);
        }
        catch (Exception e){
            Toast.makeText(this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK && data!=null){
                    ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txt.setText(result.get(0));
                    database.getReference().child("User").child(auth.getCurrentUser().getUid()).child("Notes").child(current_time).setValue(result.get(0));
                }
                break;
        }
    }
}