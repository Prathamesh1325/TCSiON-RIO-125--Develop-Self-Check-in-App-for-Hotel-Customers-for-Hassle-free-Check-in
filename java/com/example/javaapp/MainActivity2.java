package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
     Button d;
     EditText email;
     EditText phone;
     EditText pass;
     EditText conpass;
    Map<String,Object> user=new HashMap<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        email=findViewById(R.id.editTextTextEmailAddress);
        phone=findViewById(R.id.editTextPhone3);
        pass=findViewById(R.id.editTextTextPassword3);
        conpass=findViewById(R.id.editTextTextPassword4);


        d=findViewById(R.id.buttonsub);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getTextSize()==0||phone.getTextSize()==0||pass.getTextSize()==0||conpass.getTextSize()==0)
                {
                    Toast.makeText(MainActivity2.this,"Fill Details",Toast.LENGTH_LONG).show();
                }
                else {
                    user.put("email",email.getText().toString());
                    user.put("phone",phone.getText().toString());
                    user.put("password",pass.getText().toString());

                    db.collection("users").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity2.this,"Registered Succesfully",Toast.LENGTH_LONG).show();
                            Intent myactivity=new Intent(MainActivity2.this,MainActivity.class);
                            startActivity(myactivity);
                        }
                    });
                }
            }
        });
    }
}