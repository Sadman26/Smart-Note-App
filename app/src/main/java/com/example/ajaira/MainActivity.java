package com.example.ajaira;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
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
    }
}