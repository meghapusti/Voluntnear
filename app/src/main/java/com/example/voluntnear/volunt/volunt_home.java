package com.example.voluntnear.volunt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voluntnear.R;
import com.example.voluntnear.volunt.voluntAdaptor;
import com.example.voluntnear.classes.Request;
import com.example.voluntnear.edit_profile;
import com.example.voluntnear.volunt_taskdetails;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class volunt_home extends AppCompatActivity implements OnMapReadyCallback{

    private ImageButton vtaskButton;
    private ImageButton veditprofileButton;
    private GoogleMap mMap;
    ArrayList<total_req> totalreqlist;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_home);

        RecyclerView voluntRecycleV = findViewById(R.id.voluntRecycleV);

        voluntRecycleV.addOnItemTouchListener(
                new RecyclerItemClickListener(context, voluntRecycleV, new RecyclerItemClickListener.OnItemClickListener(){
                    @Override public void onItemClick(View view, int position){
                        total_req clickedItem = totalreqlist.get(position);

                        Intent intent = new Intent(volunt_home.this, volunt_taskdetails.class);
                        intent.putExtra("reqID", clickedItem.getRequestId());
                        intent.putExtra("reqType", clickedItem.getReqtype());
                        intent.putExtra("addr", clickedItem.getAddr());
                        intent.putExtra("date", clickedItem.getDate());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
        //setting up recycle view
        voluntRecycleV.setHasFixedSize(true);
        voluntRecycleV.setLayoutManager(new LinearLayoutManager(this));
        totalreqlist = new ArrayList<>();
        voluntAdaptor myAdaptor = new voluntAdaptor(this, totalreqlist);
        voluntRecycleV.setAdapter(myAdaptor);

        //Initializing Firebase Reference for Tasks
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference voluntnode = FirebaseDatabase.getInstance().getReference("Requests");

        voluntnode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()){
                    String childKey = childSnapshot.getKey();
                    String reqID = String.valueOf(childSnapshot.getKey());
                    String reqType = String.valueOf(childSnapshot.child(childKey).child("requestType").getValue());
                    String addr = String.valueOf(childSnapshot.child(childKey).child("init_name").getValue());
                    String date = String.valueOf(childSnapshot.child(childKey).child("date").getValue());
                    total_req req1 = new total_req(reqID,addr,date,reqType);
                    totalreqlist.add(req1);
                }
                myAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Initialize Firebase Database reference
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
        mMap.addMarker(new MarkerOptions().position(location).title("Singapore"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,10));

        //test
        DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        reqRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    // Get the key for object child as child path of childSnapshot
                    DataSnapshot onlyChildSnapshot = childSnapshot.getChildren().iterator().next();
                    String key=childSnapshot.getChildren().iterator().next().getKey();

                   double latitude = childSnapshot.child(key).child("initLoc").child("latitude").getValue(Double.class);
                   double longitude = childSnapshot.child(key).child("initLoc").child("longitude").getValue(Double.class);

                    Log.d("Latitude", String.valueOf(latitude));
                    Log.d("Longitude", String.valueOf(longitude));

                    // Get the HashMap for this key
                    LatLng position=new LatLng(latitude,longitude);
                    mMap.addMarker(new MarkerOptions().position(position).title(""));

                    // Access the values in the HashMap as needed
                    // For example, to retrieve a field named "initLoc" which is a LatLng object

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });


    }
}