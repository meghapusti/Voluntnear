package com.example.voluntnear;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class edit_profile extends AppCompatActivity {
    Button saveButton;
    EditText oldPwd;
    EditText newPwd;
    EditText newHPNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        saveButton = findViewById(R.id.saveButton);
        oldPwd = findViewById(R.id.oldPwd);
        newPwd = findViewById(R.id.newPwd);
        newHPNo = findViewById(R.id.newHPNo);



    }
}