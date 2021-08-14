package com.example.javaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
Button b;
Button c;
    EditText email;

    EditText pass;
    Map<String,Object> user=new HashMap<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context=MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.editTextPhone2);
        pass=findViewById(R.id.editTextTextPassword);
        b=findViewById(R.id.buttonR);
        c=findViewById(R.id.buttonsigin);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().length()==0||pass.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this,"Fill Details",Toast.LENGTH_LONG).show();
                }
                else {
                    user.put("email",email.getText().toString());

                    user.put("password",pass.getText().toString());
                    db.collection("users").whereEqualTo("phone",email.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful())
                            {
                                QuerySnapshot querySnapshot=task.getResult();
                                if (querySnapshot.isEmpty())
                                {

                                }
                                else {
                                    DocumentSnapshot document=querySnapshot.getDocuments().get(0);
                                   if(pass.getText().toString().equals(document.get("password").toString()))
                                   {
                                       Log.d("TAG","i am here");
                                       Toast.makeText(MainActivity.this,"Log in succesfully",Toast.LENGTH_LONG).show();

                                       Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                                       startActivity(intent);
                                   }
                                }
                            }
                        }
                    });
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent myactivity=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(myactivity);

            }
        });
    }

}
