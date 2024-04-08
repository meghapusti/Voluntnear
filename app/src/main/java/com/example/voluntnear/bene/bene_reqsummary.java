package com.example.voluntnear.bene;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.R;

public class bene_reqsummary extends AppCompatActivity {

    ImageButton backbreqsummButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bene_reqsummary);

        backbreqsummButton = findViewById(R.id.backbreqsummButton);
        backbreqsummButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bene_reqsummary.this, bene_home.class));
            }
        });


    }
}