package com.example.voluntnear.bene;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.R;
import com.example.voluntnear.edit_profile;

public class bene_home extends AppCompatActivity {

    ImageButton beditProfileButton;
    ImageButton brequestButton;
    ImageButton bcreateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bene_home);

        beditProfileButton = findViewById(R.id.beditProfileButton);
        brequestButton = findViewById(R.id.brequestButton);
        bcreateButton = findViewById(R.id.bcreateButton);
        beditProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bene_home.this, edit_profile.class));
            }
        });
        brequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bene_home.this, bene_reqsummary.class));
            }
        });

        bcreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bene_home.this, bene_request.class));
            }
        });


    }
}