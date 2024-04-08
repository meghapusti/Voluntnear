package com.example.voluntnear.volunt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.R;
import com.example.voluntnear.bene.bene_home;
import com.example.voluntnear.bene.bene_login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class volunt_login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText vLoginMail;
    private EditText vLoginPwd;
    private Button vLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_login);

        vLoginMail = findViewById(R.id.vLoginMail);
        vLoginPwd = findViewById(R.id.vLoginPwd);
        vLoginButton = findViewById(R.id.vLoginButton);
        mAuth = FirebaseAuth.getInstance();

        vLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = vLoginMail.getText().toString();
                String password = vLoginPwd.getText().toString();
                if (TextUtils.isEmpty(email)){
                    vLoginMail.setError("Email is required");
                    return;
                }
                if (password.length() < 6){
                    vLoginPwd.setError("Password must be at least 6 characters long ");
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(volunt_login.this, "Log In Success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(volunt_login.this, volunt_home.class));finish();
                        }
                        else{
                            Toast.makeText(volunt_login.this, "Email or Password is wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}