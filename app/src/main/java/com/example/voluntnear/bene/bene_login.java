package com.example.voluntnear.bene;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class bene_login extends AppCompatActivity {
    //firebase connector
    private FirebaseAuth mAuth;

    //Declare variables
    private EditText bLoginMail;
    private EditText bLoginPwd;
    private Button bLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bene_login);

        //Getting instances of buttons
        bLoginMail = findViewById(R.id.bLoginMail);
        bLoginPwd = findViewById(R.id.bLoginPwd);
        bLoginButton = findViewById(R.id.bLoginButton);
        mAuth = FirebaseAuth.getInstance();

        bLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = bLoginMail.getText().toString();
                String password = bLoginPwd.getText().toString();
                if (TextUtils.isEmpty(email)){
                    bLoginMail.setError("Email is required");
                    return;
                }
                if (password.length() < 6){
                    bLoginPwd.setError("Password must be at least 6 characters long ");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(bene_login.this, "Log In Success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(bene_login.this, bene_home.class));finish();
                        }
                        else{
                            Toast.makeText(bene_login.this, "Email or Password is wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}