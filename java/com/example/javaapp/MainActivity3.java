package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {


    Button book;
    EditText name;
    EditText email;
    EditText guest;
    EditText indate;
    EditText outdate;
    EditText time;
    Switch ac;
    Switch breakfast;
    Map<String,Object> user=new HashMap<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        name=findViewById(R.id.editTextTextPersonName);
        email=findViewById(R.id.editTextTextEmailAddress2);
        guest=findViewById(R.id.editTextNumberguest);
        indate=findViewById(R.id.editTextDate);
        outdate=findViewById(R.id.editTextDate2);
        time=findViewById(R.id.editTextTime3);
        ac=findViewById(R.id.switch1);
        breakfast=findViewById(R.id.switch2);
        book=findViewById(R.id.buttonbook);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getTextSize()==0||email.getTextSize()==0||guest.getTextSize()==0||indate.getTextSize()==0||outdate.getTextSize()==0||time.getTextSize()==0)
                {
                    Toast.makeText(MainActivity3.this,"Fill Details",Toast.LENGTH_LONG).show();
                }
                else {
                    user.put("name",name.getText().toString());
                    user.put("email",email.getText().toString());
                    user.put("guest",guest.getText().toString());
                    user.put("indate",indate.getText().toString());
                    user.put("outdate",outdate.getText().toString());
                    user.put("time",time.getText().toString());
                    user.put("ac",ac.getText().toString());
                    user.put("breakfast",breakfast.getText().toString());

                    db.collection("Boooking").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity3.this,"Room Booked Succesfully",Toast.LENGTH_LONG).show();
                            Intent myactivity=new Intent(MainActivity3.this,MainActivity.class);
                            startActivity(myactivity);
                        }
                    });
                }
            }
        });



    }
}