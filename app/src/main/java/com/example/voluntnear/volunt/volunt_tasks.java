package com.example.voluntnear.volunt;

import static com.example.voluntnear.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voluntnear.R;
import com.example.voluntnear.R.id;
import com.example.voluntnear.bene.AcceptedAdapter;
import com.example.voluntnear.bene.accepted_req;
import com.example.voluntnear.bene.bviewClasses.PendingAdapter;
import com.example.voluntnear.bene.bviewClasses.pending_req;
import com.example.voluntnear.classes.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class volunt_tasks extends AppCompatActivity {

    private ImageButton backvtaskButton;
    private RecyclerView accRecycleV;
    ArrayList<acc_req> accList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_tasks);
        backvtaskButton = findViewById(R.id.backvtaskButton);
        accRecycleV = findViewById(id.accRecycleV);


        //setting up accepted recycler view
        accRecycleV.setHasFixedSize(true);
        accRecycleV.setLayoutManager(new LinearLayoutManager(this));
        accList = new ArrayList<>();
        AccAdapter accAdapter = new AccAdapter(this, accList);
        accRecycleV.setAdapter(accAdapter);


        //Getting reference to database node
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference mNode_accepted = FirebaseDatabase.getInstance().getReference("Users").child("Volunt").child(userId).child("Accepted");


        mNode_accepted.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String childKey = childSnapshot.getKey();
                    String reqID = String.valueOf(childSnapshot.getKey());
                    String reqType = String.valueOf(childSnapshot.child(childKey).child("reqtype").getValue());
                    String addr = String.valueOf(childSnapshot.child(childKey).child("addr").getValue());
                    String date = String.valueOf(childSnapshot.child(childKey).child("date").getValue());
                    acc_req a_req = new acc_req(reqID,addr,date,reqType);
                    accList.add(a_req);
                }
                accAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        backvtaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(volunt_tasks.this, volunt_home.class));
            }
        });
    }
}