package com.example.voluntnear;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voluntnear.bene.bene_create;
import com.example.voluntnear.bene.bene_login;
import com.example.voluntnear.bene.bene_reqsummary;
import com.example.voluntnear.volunt.volunt_home;
import com.example.voluntnear.volunt.volunt_login;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);//initializing the firebase


        //Creating a delay to create splash screen
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this, volunt_login.class); //bene_create.class
            startActivity(intent);finish();
        }
    }, 2000);


    }
}