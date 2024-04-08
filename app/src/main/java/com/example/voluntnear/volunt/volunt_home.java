package com.example.voluntnear.volunt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.R;
import com.example.voluntnear.edit_profile;

public class volunt_home extends AppCompatActivity {

    ImageButton vtaskButton;
    ImageButton veditprofileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_home);

        vtaskButton = findViewById(R.id.vtaskButton);
        veditprofileButton = findViewById(R.id.veditProfileButton);
        veditprofileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(volunt_home.this, edit_profile.class));
            }
        });
        vtaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(volunt_home.this, volunt_tasks.class));
            }
        });
    }
}