package com.example.voluntnear;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.bene.bene_login;
import com.example.voluntnear.bene.bene_signup;
import com.example.voluntnear.volunt.volunt_login;
import com.example.voluntnear.volunt.volunt_signup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class role_select extends AppCompatActivity {
    //declaring components on page
    private Button continbutton;
    private ChipGroup chipGroup;
    private ImageButton backRoleButton;
    private String selectedRole = ""; //only exist within this activity --> redirect to which page
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_role_select);

        //linking components
        chipGroup = findViewById(R.id.chipGroup);
        continbutton =  findViewById(R.id.continbutton);
        backRoleButton = findViewById(R.id.backRoleButton);

        //getting the chip input
        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup chipGroup, @NonNull List<Integer> checkedIds) {
                if (checkedIds.isEmpty()){
                    role = "";
                }
                else{
                    for (int i: checkedIds){
                        Chip chip = findViewById(i);
                        role = (String) chip.getText();
                    }
                }
                selectedRole = role;
            }
        });

        continbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (login_signup.track_click.equals("signup")){
                    if (selectedRole.equals("Beneficiary")){
                        startActivity(new Intent(role_select.this, bene_signup.class));
                    }
                    else if (selectedRole.equals("Volunteer")){
                        startActivity(new Intent(role_select.this, volunt_signup.class));
                    }
                    else{
                        Toast.makeText(role_select.this, "Please a role", Toast.LENGTH_SHORT).show();
                    }
                }
                if (login_signup.track_click.equals("login")){
                    if (selectedRole.equals("Beneficiary")){
                        startActivity(new Intent(role_select.this, bene_login.class));
                    }
                    else if (selectedRole.equals("Volunteer")){
                        startActivity(new Intent(role_select.this, volunt_login.class));
                    }
                    else{
                        Toast.makeText(role_select.this, "Please a role", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        backRoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(role_select.this, login_signup.class));
            }
        });
    }
}