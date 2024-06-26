package com.example.voluntnear.bene;

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
import com.example.voluntnear.bene.bviewClasses.PendingAdapter;
import com.example.voluntnear.bene.bviewClasses.pending_req;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bene_reqsummary extends AppCompatActivity {
    private ImageButton backbreqsummButton;
    private RecyclerView pendingRecycleV;
    private RecyclerView acceptedRecycleV;
    ArrayList<pending_req> reqList;
    ArrayList<accepted_req> accList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bene_reqsummary);
        backbreqsummButton = findViewById(R.id.backbreqsummButton);
        pendingRecycleV = findViewById(R.id.pendingRecycleV);
        acceptedRecycleV = findViewById(R.id.acceptedRecycleV);

        //setting up pending recycle view
        pendingRecycleV.setHasFixedSize(true);
        pendingRecycleV.setLayoutManager(new LinearLayoutManager(this));
        reqList = new ArrayList<>();
        PendingAdapter myAdpator = new PendingAdapter(this,reqList);
        pendingRecycleV.setAdapter(myAdpator);

        //setting up accepted recycler view
        acceptedRecycleV.setHasFixedSize(true);
        acceptedRecycleV.setLayoutManager(new LinearLayoutManager(this));
        accList = new ArrayList<>();
        AcceptedAdapter accAdapter = new AcceptedAdapter(this, accList);
        acceptedRecycleV.setAdapter(accAdapter);


        //Getting reference to database node
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference mNode_pending = FirebaseDatabase.getInstance().getReference("Users").child("Bene").child(userId).child("Pending");
        DatabaseReference mNode_accepted = FirebaseDatabase.getInstance().getReference("Users").child("Bene").child(userId).child("Accepted");

        //Loop through the pending requests under current user

        mNode_pending.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String childKey = childSnapshot.getKey();
                    String reqID = String.valueOf(childSnapshot.getKey());
                    String reqType = String.valueOf(childSnapshot.child(childKey).child("requestType").getValue());
                    String addr = String.valueOf(childSnapshot.child(childKey).child("init_name").getValue());
                    String date = String.valueOf(childSnapshot.child(childKey).child("date").getValue());
                    pending_req req1 = new pending_req(reqID,addr,date,reqType);
                    reqList.add(req1);
                }
                myAdpator.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });





        mNode_accepted.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String childKey = childSnapshot.getKey();
                    String reqID = String.valueOf(childSnapshot.getKey());
                    String reqType = String.valueOf(childSnapshot.child(childKey).child("reqtype").getValue());
                    String addr = String.valueOf(childSnapshot.child(childKey).child("addr").getValue());
                    String date = String.valueOf(childSnapshot.child(childKey).child("date").getValue());
                    accepted_req a_req = new accepted_req(reqID,addr,date,reqType);
                    accList.add(a_req);
                }
                accAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });







        backbreqsummButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bene_reqsummary.this, bene_home.class));
            }
        });
    }

}