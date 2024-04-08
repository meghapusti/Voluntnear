package com.example.voluntnear;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.volunt.volunt_home;

public class volunt_taskdetails extends AppCompatActivity {
    Button acceptTaskButton;
    ImageButton backvtasksButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_taskdetails);

        acceptTaskButton = findViewById(R.id.acceptTaskButton);
        backvtasksButton = findViewById(R.id.backvtasksButton);

        backvtasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(volunt_taskdetails.this, volunt_home.class));
            }
        });
    }
}