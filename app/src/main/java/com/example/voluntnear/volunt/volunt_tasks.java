package com.example.voluntnear.volunt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.R;

public class volunt_tasks extends AppCompatActivity {

    ImageButton backvtaskButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_tasks);
        backvtaskButton = findViewById(R.id.backvtaskButton);
        backvtaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(volunt_tasks.this, volunt_home.class));
            }
        });
    }
}