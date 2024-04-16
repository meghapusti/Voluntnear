package com.example.voluntnear;

import static com.example.voluntnear.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.classes.Request;
import com.example.voluntnear.classes.User;
import com.example.voluntnear.volunt.total_req;
import com.example.voluntnear.volunt.volunt_home;
import com.example.voluntnear.volunt.volunt_tasks;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class volunt_taskdetails extends AppCompatActivity {
    private Button acceptTaskButton;
    private ImageButton backvtasksButton;
    private FirebaseAuth mAuth;
    public String reqID;
    public String reqType;
    public String date;
    public String time;
    public String addr;
    public String destloc;
    public String remark;
    public String userId;
    public Request taskreq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_taskdetails);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        TextView reqtypeText = findViewById(R.id.ReqType);
        TextView reqnameText = findViewById(R.id.reqername);
        TextView telenumText = findViewById(R.id.reqtelenum);
        TextView dateText = findViewById(R.id.reqdate);
        TextView timeText = findViewById(R.id.reqtime);
        TextView initlocText = findViewById(R.id.initloc);
        TextView destilocText = findViewById(id.destiLoc);
        TextView remarksText = findViewById(id.remarks);


        acceptTaskButton = findViewById(R.id.acceptTaskButton);
        backvtasksButton = findViewById(R.id.backvtasksButton);

        Intent intent = getIntent();
        if (intent != null) {
            reqID = intent.getStringExtra("reqID");
            reqType = intent.getStringExtra("reqType");
            addr = intent.getStringExtra("addr");
            date = intent.getStringExtra("date");
            time = intent.getStringExtra("time");
            destloc = intent.getStringExtra("dest");
            remark = intent.getStringExtra("remarks");
            userId = intent.getStringExtra("userID");
        }

        reqtypeText.setText(reqType);

        dateText.setText(date);
        timeText.setText(time);
        initlocText.setText(addr);
        destilocText.setText(destloc);
        remarksText.setText(remark);


        DatabaseReference mdata = FirebaseDatabase.getInstance().getReference("Users").child("Bene").child(userId);
        mdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    if (!childSnapshot.getKey().equals("Pending")) {
                        String childKey = childSnapshot.getKey();
                        String username = String.valueOf(childSnapshot.child("name").getValue());
                        String password = String.valueOf(childSnapshot.child("password").getValue());
                        String telenum = String.valueOf(childSnapshot.child("phno").getValue());
                        String role = String.valueOf(childSnapshot.child("role").getValue());

                        reqnameText.setText(username);
                        telenumText.setText(telenum);
                        taskreq = new Request(reqID,childKey,reqType,date,time,addr,destloc,null,null,remark);
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        backvtasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(volunt_taskdetails.this, volunt_home.class));
            }
        });

        acceptTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //add to accepted tasks under volunteer
                String voluntId = mAuth.getCurrentUser().getUid();
                Map<String, Object> reqData = new HashMap<>();
                total_req req = new total_req(reqID,addr,date,reqType,userId,time,destloc,remark);
                reqData.put(req.getRequestId(),req);

                DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Volunt").child(voluntId).child("Accepted");
                reqRef.child(reqID).setValue(reqData);

                //add to accepted tasks under bene
                DatabaseReference benereqRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Bene").child(userId).child("Accepted");
                benereqRef.child(reqID).setValue(reqData);

                //remove from pending
                DatabaseReference benepenRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Bene").child(userId).child("Pending");
                benepenRef.child(reqID).removeValue();

                //remove from requests
                DatabaseReference accRef = FirebaseDatabase.getInstance().getReference().child("Requests").child(reqID);
                accRef.child(reqID).removeValue();

                startActivity(new Intent(volunt_taskdetails.this, volunt_tasks.class));

            }
        });
    }
}