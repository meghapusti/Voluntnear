package com.example.voluntnear.bene;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voluntnear.R;
import com.example.voluntnear.classes.Request;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bene_create extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageButton backbreqcreateButton;
    private Button breqCreateButton;
    private TextView breqType;
    private EditText breqDate;
    private EditText breqTime;
    private EditText breqRemark;

    //For Date
    int year;
    int month;
    int day;

    //For Time
    int currentMin;
    int currentHr;
    String amPm;

    //Inital Location
    String initName;
    LatLng initLoc;

    //Destination
    String destName;
    LatLng destLoc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bene_create);
        breqCreateButton = findViewById(R.id.breqCreateButton);
        breqType = findViewById(R.id.breqType);
        breqDate = findViewById(R.id.breqDate);
        breqTime = findViewById(R.id.breqTime);
        breqRemark = findViewById(R.id.breqRemark);
        backbreqcreateButton = findViewById(R.id.backbreqcreateButton);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        //setting text to request type
        breqType.setText(bene_request.reqType);

        backbreqcreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bene_create.this, bene_request.class));
            }
        });

        //Creating Date and Time Pop Up
        Calendar calendar = Calendar.getInstance();
        breqDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(bene_create.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yr, int mth, int dayofmth) {
                        breqDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        breqTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentHr = calendar.get(Calendar.HOUR_OF_DAY);
                currentMin = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(bene_create.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        if (hour>= 12){
                            amPm = "PM";
                        }
                        else {
                            amPm = "AM";
                        }
                        breqTime.setText(String.format("%02d:%02d",hour, min) + amPm);

                    }
                },currentHr, currentMin, false);
                timePickerDialog.show();
            }
        });


        //Initialise SDK
        Places.initialize(getApplicationContext(),"AIzaSyCNxGrLqZqeskhn646Mjf5jCOfp3b2FVRw");

        //Create new places client Instance
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment for INITIAL LOCATION
        AutocompleteSupportFragment autocompleteFragment_init = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment_init);

        autocompleteFragment_init.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment_init.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
               initName = place.getName();
               initLoc = place.getLatLng();
            }
            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Toast.makeText(bene_create.this, "Choose a Location!", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize the AutocompleteSupportFragment for DESTINATION
        AutocompleteSupportFragment autocompleteFragment_desti = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment_desti);

        autocompleteFragment_desti.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment_desti.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                destName = place.getName();
                destLoc = place.getLatLng();
            }
            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Toast.makeText(bene_create.this, "Choose a Location!", Toast.LENGTH_SHORT).show();
            }
        });

        breqCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = breqDate.getText().toString();
                String time = breqTime.getText().toString();
                String remarks = breqRemark.getText().toString();
                if (TextUtils.isEmpty(date)){
                    breqDate.setError("Date is required");
                    return;
                }
                if (TextUtils.isEmpty(time)){
                    breqTime.setError("Time is required");
                    return;
                }
                if (TextUtils.isEmpty(remarks)){
                    breqRemark.setError("Remarks is required");
                    return;
                }


                // Create a new unique key for the request
                String reqID = mDatabase.getReference("Requests").push().getKey();

                // Get current user ID
                String userId = mAuth.getCurrentUser().getUid();

                //Get reqType
                String reqType = bene_request.reqType;

                // Create a Map to represent the user data
                Map<String, Object> reqData = new HashMap<>();

                // Create a Request object with the request details
                Request request = new Request(reqID, userId, reqType, date, time, initName, destName, initLoc, destLoc, remarks);
                reqData.put(request.getRequestId(), request);

                //Get Reference to DB item of user
                DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference().child("Requests");

                //Get Reference to DB table Requests
                DatabaseReference beneRef = FirebaseDatabase.getInstance().getReference("Users").child("Bene").child(userId).child("Pending");
                beneRef.child(reqID).setValue(reqData);

                // Set the req data under a unique key using the current reqID
                reqRef.child(reqID).setValue(reqData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Data saved successfully
                            Toast.makeText(bene_create.this, "Request Created!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(bene_create.this, bene_reqsummary.class));finish();
                        } else {
                            // Error occurred while saving data
                            Toast.makeText(bene_create.this, "Fail to create request :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}