package com.example.voluntnear.bene;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voluntnear.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class bene_create extends AppCompatActivity {
    private ImageButton backbreqcreateButton;
    private Button breqCreateButton;
    private TextView breqType;
    private EditText breqDate;
    private EditText breqTime;
    private EditText breqInit;
    private EditText breqDesti;
    private EditText breqRemark;

    //For Date
    int year;
    int month;
    int day;

    //For Time
    int currentMin;
    int currentHr;
    String amPm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bene_create);
        breqCreateButton = findViewById(R.id.breqCreateButton);
        breqType = findViewById(R.id.breqType);
        breqDate = findViewById(R.id.breqDate);
        breqTime = findViewById(R.id.breqTime);
        breqInit = findViewById(R.id.breqInit);
        breqDesti = findViewById(R.id.breqDesti);
        breqRemark = findViewById(R.id.breqRemark);
        backbreqcreateButton = findViewById(R.id.backbreqcreateButton);

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








        breqCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = breqDate.getText().toString();
                String time = breqTime.getText().toString();
                String init_loc = breqInit.getText().toString();
                String dest_loc = breqDesti.getText().toString();
                String remarks = breqDesti.getText().toString();
                //String requestby = //Take Name of Requester

            }
        });

    }
}