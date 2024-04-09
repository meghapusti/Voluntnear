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
import com.example.voluntnear.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class bene_signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText bemailField;
    private EditText bnameField;
    private EditText bhpField;
    private EditText bpwdField;
    private Button bSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bene_signup);

        bemailField = findViewById(R.id.bemailField);
        bnameField = findViewById(R.id.bnameField);
        bhpField = findViewById(R.id.bhpField);
        bpwdField = findViewById(R.id.bpwdField);
        bSignUpButton = findViewById(R.id.bSignUpButton);
        mAuth = FirebaseAuth.getInstance();


        bSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = bemailField.getText().toString();
                String password = bpwdField.getText().toString();
                String phoneno = bhpField.getText().toString();
                String name = bnameField.getText().toString();

                if (TextUtils.isEmpty(email)){
                    bemailField.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    bnameField.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(phoneno)){
                    bhpField.setError("Phone No is required");
                    return;
                }
                if (password.length() < 6){
                    bpwdField.setError("Password must be at least 6 characters long ");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Get the current user's ID
                            String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                            // Create a Map to represent the user data
                            Map<String, Object> userData = new HashMap<>();

                            //Create User Obj
                            User user1 = new User(email,name,password, phoneno, "bene");
                            userData.put(user1.getName(), user1);


                            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Bene");

                            // Set the user data under a unique key using the current user's ID
                            usersRef.child(userId).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Data saved successfully
                                        Toast.makeText(bene_signup.this, "Signed Up Successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(bene_signup.this, bene_login.class));finish();
                                    } else {
                                        // Error occurred while saving data
                                        Toast.makeText(bene_signup.this, "Sign Up Failed :(", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            // Error occurred while creating user
                            Toast.makeText(bene_signup.this, "Sign Up Failed :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });




    }
}