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
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> userData = new HashMap<>();
        mDatabase.child("Users").setValue(userData);


        //Creating a delay to create splash screen
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this, bene_create.class);//login_signup.class
            startActivity(intent);finish();
        }

    }, 2000);


    }
}