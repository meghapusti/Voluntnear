package com.example.voluntnear.bene;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voluntnear.R;

public class bene_create extends AppCompatActivity {
    private ImageButton backbreqcreateButton;
    private Button breqCreateButton;
    private TextView breqType;
    private EditText breqDate;
    private EditText breqTime;
    private EditText breqInit;
    private EditText breqDesti;
    private EditText breqRemark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bene_create);
        breqCreateButton = findViewById(R.id.breqCreateButton);
        breqType = findViewById(R.id.breqType);
        breqDate = findViewById(R.id.breqDate);
        breqTime = findViewById(R.id.breqTime);
        breqInit = findViewById(R.id.breqInit);
        breqDesti = findViewById(R.id.breqDesti);
        breqRemark = findViewById(R.id.breqRemark);
        backbreqcreateButton = findViewById(R.id.backbreqcreateButton);

        backbreqcreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bene_create.this, bene_request.class));
            }
        });

    }
}