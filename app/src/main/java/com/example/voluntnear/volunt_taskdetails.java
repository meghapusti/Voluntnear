package com.example.voluntnear;

import static com.example.voluntnear.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluntnear.classes.Request;
import com.example.voluntnear.volunt.total_req;
import com.example.voluntnear.volunt.volunt_home;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class volunt_taskdetails extends AppCompatActivity {
    private Button acceptTaskButton;
    private ImageButton backvtasksButton;
    public String reqID;
    public String reqType;
    public String addr;
    public String date;


    public String destloc;
    public String remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volunt_taskdetails);

        TextView reqtypeText = findViewById(R.id.ReqType);
        TextView datetimeText = findViewById(id.reqdate);
        TextView initlocText = findViewById(R.id.initloc);

        acceptTaskButton = findViewById(R.id.acceptTaskButton);
        backvtasksButton = findViewById(R.id.backvtasksButton);

        Intent intent = getIntent();
        if (intent != null) {
            reqID = intent.getStringExtra("reqID");
            reqType = intent.getStringExtra("reqType");
            addr = intent.getStringExtra("addr");
            date = intent.getStringExtra("date");
        }

        reqtypeText.setText(reqType);
        datetimeText.setText(date);
        initlocText.setText(addr);



        DatabaseReference mdata = FirebaseDatabase.getInstance().getReference("Requests").child("reqID");
        mdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String childKey = childSnapshot.getKey();
                    String reqID = String.valueOf(childSnapshot.getKey());
                    String reqType = String.valueOf(childSnapshot.child(childKey).child("requestType").getValue());
                    String init_name = String.valueOf(childSnapshot.child(childKey).child("init_name").getValue());
                    String date = String.valueOf(childSnapshot.child(childKey).child("date").getValue());
                    String name = String.valueOf(childSnapshot.child(childKey).child("name").getValue());
                    String userId = String.valueOf(childSnapshot.child(childKey).child("userID").getValue());
                    String time = String.valueOf(childSnapshot.child(childKey).child("time").getValue());
                    String desti_name = String.valueOf(childSnapshot.child(childKey).child("name").getValue());
                    String remarks = String.valueOf(childSnapshot.child(childKey).child("remarks").getValue());
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
    }
}