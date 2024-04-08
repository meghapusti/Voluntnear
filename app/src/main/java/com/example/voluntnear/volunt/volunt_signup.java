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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voluntnear.R;
import com.example.voluntnear.bene.bene_login;
import com.example.voluntnear.bene.bene_signup;
import com.example.voluntnear.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class volunt_signup extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText vemailField;
    EditText vnameField;
    EditText vhpField;
    EditText vpwdField;

    Button vSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_signup);

        vemailField = findViewById(R.id.vemailField);
        vnameField = findViewById(R.id.vnameField);
        vhpField = findViewById(R.id.vhpField);
        vpwdField = findViewById(R.id.vpwdField);
        vSignUpButton = findViewById(R.id.vSignUpButton);
        mAuth = FirebaseAuth.getInstance();

        vSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = vemailField.getText().toString();
                String password = vpwdField.getText().toString();
                String phoneno = vhpField.getText().toString();
                String name = vnameField.getText().toString();

                if (TextUtils.isEmpty(email)){
                    vemailField.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    vnameField.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(phoneno)){
                    vhpField.setError("Phone No is required");
                    return;
                }
                if (password.length() < 6){
                    vpwdField.setError("Password must be at least 6 characters long ");
                    return;
                }
                //User user1 = new User(email,name, password, phoneno, "bene");

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(volunt_signup.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(volunt_signup.this, volunt_login.class));finish();
                        }
                        else{
                            Toast.makeText(volunt_signup.this, "Sign Up Failed :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}