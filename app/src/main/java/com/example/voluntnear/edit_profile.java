package com.example.voluntnear;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import androidx.annotation.NonNull;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.voluntnear.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class edit_profile extends AppCompatActivity {
    private Button saveButton;
    private EditText oldPwd;
    private EditText newPwd;
    private EditText newHPNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        saveButton = findViewById(R.id.saveButton);
        oldPwd = findViewById(R.id.oldPwd);
        newPwd = findViewById(R.id.newPwd);
        newHPNo = findViewById(R.id.newHPNo);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });
    }
    private void updateUserProfile() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            String oldPassword = oldPwd.getText().toString();
            String newPassword = newPwd.getText().toString();
            String newPhoneNumber = newHPNo.getText().toString();

            // Validate fields
            if (TextUtils.isEmpty(newPassword)) {
                newPwd.setError("New password is required");
                return;
            }

            // Update password if new password is provided
            if (!TextUtils.isEmpty(newPassword)) {
                user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(edit_profile.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(edit_profile.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            // Update phone number if new phone number is provided
            if (!TextUtils.isEmpty(newPhoneNumber)) {
                DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference().child("Users");
                mDatabase.child(userId).child("phoneNumber").setValue(newPhoneNumber);
                Toast.makeText(edit_profile.this, "Phone number updated successfully", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(edit_profile.this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

}