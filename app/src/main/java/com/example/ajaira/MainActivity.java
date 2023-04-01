package com.example.ajaira;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    //import addlistenerforsinglevalueeventlistener

    EditText firenote;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.rating:
                Intent intent3=new Intent(MainActivity.this,rating.class);
                startActivity(intent3);
                finish();
                return true;
            case R.id.home:
                Intent intent2=new Intent(MainActivity.this,homepage.class);
                startActivity(intent2);
                finish();
                return true;
            case R.id.notes:
                Intent intent1=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
                return true;
            case R.id.logout:
                auth.signOut();
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Notes");
        firenote=findViewById(R.id.firenote);
        database.getReference().child("User").child(auth.getUid()).child("Notes").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    StringBuilder notes = new StringBuilder();
                    int count = 1;
                    List<DataSnapshot> snapshotList = new ArrayList<>();
                    for(DataSnapshot snapshot : task.getResult().getChildren()) {
                        snapshotList.add(snapshot);
                    }
                    Collections.reverse(snapshotList);
                    for(DataSnapshot snapshot : snapshotList) {
                        String noteText = snapshot.getValue().toString();
                        notes.append(count)
                                .append(". ")
                                .append(noteText)
                                .append("\n\n");
                        count++;
                    }
                    firenote.setText(notes.toString());
                    firenote.setTextSize(25);
                    firenote.setTextColor(getResources().getColor(R.color.black));
                    firenote.setTypeface(Typeface.DEFAULT_BOLD);
                    firenote.setBackground(null);
                }
            }
        });


    }
}