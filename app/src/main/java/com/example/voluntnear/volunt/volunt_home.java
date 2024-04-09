package com.example.voluntnear.volunt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.R;
import com.example.voluntnear.edit_profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import for maps

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ValueEventListener;

public class volunt_home extends AppCompatActivity implements OnMapReadyCallback{

    private ImageButton vtaskButton;
    private ImageButton veditprofileButton;
    private DatabaseReference mDatabase;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_home);

        // Initialize Firebase Database reference
        mDatabase = FirebaseDatabase.getInstance().getReference("beneficiaries");
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);


        vtaskButton = findViewById(R.id.vtaskButton);
        veditprofileButton = findViewById(R.id.veditProfileButton);
        veditprofileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(volunt_home.this, edit_profile.class));
            }
        });
        vtaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(volunt_home.this, volunt_tasks.class));
            }
        });

    }

    //map implementation
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location=new LatLng(1.3521 , 103.8198);
        googleMap.addMarker(new MarkerOptions().position(location).title("Singapore"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,12));


        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Retrieve beneficiary data and add marker to map
                    String name = snapshot.child("name").getValue(String.class);
                    Double latitude = snapshot.child("latitude").getValue(Double.class);
                    Double longitude = snapshot.child("longitude").getValue(Double.class);
                    LatLng location = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(location).title(name));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });


    }
}