package com.example.voluntnear;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voluntnear.bene.bene_signup;
import com.google.firebase.auth.FirebaseAuth;

public class login_signup extends AppCompatActivity {
    public static String track_click =""; //track if user pressed login or signup need access on other activity
    private Button loginbutton;
    private Button signupbutton;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_signup);

        loginbutton = findViewById(R.id.loginbutton);
        signupbutton = findViewById(R.id.signupbutton);



        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                track_click = "login";
                startActivity(new Intent(login_signup.this, role_select.class));
            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                track_click = "signup";
                startActivity(new Intent(login_signup.this, role_select.class));
            }
        });



    }
}