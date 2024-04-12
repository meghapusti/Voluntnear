package com.example.voluntnear.volunt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.voluntnear.classes.User;//importing user class
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class volunt_signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText vemailField;
    private  EditText vnameField;
    private EditText vhpField;
    private EditText vpwdField;
    private Button vSignUpButton;
    private TextView vlogin;

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
        vlogin = findViewById(R.id.vlogin);
        mAuth = FirebaseAuth.getInstance();

        vlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(volunt_signup.this, volunt_login.class));finish();
            }
        });

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

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Get the current user's ID
                            String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                            // Create a Map to represent the user data
                            Map<String, Object> userData = new HashMap<>();

                            //Create User Obj
                            User user1 = new User(email,name, phoneno, "volunt");
                            userData.put(user1.getName(), user1);


                            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Volunt");

                            // Set the user data under a unique key using the current user's ID
                            usersRef.child(userId).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Data saved successfully
                                        Toast.makeText(volunt_signup.this, "Signed Up Successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(volunt_signup.this, volunt_login.class));finish();
                                    } else {
                                        // Error occurred while saving data
                                        Toast.makeText(volunt_signup.this, "Sign Up Failed :(", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            // Error occurred while creating user
                            Toast.makeText(volunt_signup.this, "Sign Up Failed :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });




    }
}
