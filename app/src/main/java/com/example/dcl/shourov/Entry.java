package com.example.dcl.shourov;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Entry extends AppCompatActivity {
    private EditText nam,Phn,Eml, wk1, wk2, wk3;
    private Button Btn;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        nam = (EditText) findViewById(R.id.nm);
        Phn = (EditText) findViewById(R.id.phn);
        Eml =  (EditText) findViewById(R.id.eml);
        wk1 = (EditText) findViewById(R.id.w1);
        wk2 = (EditText) findViewById(R.id.w2);
        wk3 = (EditText) findViewById(R.id.w3);
        Btn = (Button) findViewById(R.id.btn);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();

            }
        });
    }

    public void AddData() {

        String name = nam.getText().toString();
        String email = Eml.getText().toString();
        String Em =  Phn.getText().toString();
        String wr1 = wk1.getText().toString();
        String wr2 = wk2.getText().toString();
        String wr3 = wk3.getText().toString();




        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(Em)) {
            if (!TextUtils.isEmpty(wr1) || !TextUtils.isEmpty(wr2) || !TextUtils.isEmpty(wr3)){

             //   SaveData saveData = new SaveData(name, email, Em,wr1, wr2, wr3);
                SaveData saveData = new SaveData();
                saveData.setNam(name);
                saveData.setEml(email);
                saveData.setPhn(Em);
                saveData.setWk1(wr1);
                saveData.setWk2(wr2);
                saveData.setWk3(wr3);

                databaseReference.push().setValue(saveData);

            Toast.makeText(getApplicationContext(), "Work Entry is Successful", Toast.LENGTH_LONG).show();
        }

        else {

                Toast.makeText(getApplicationContext(), "Enter At Least 1 work", Toast.LENGTH_LONG).show();
            }
            }

            else
                {


                    Toast.makeText(getApplicationContext(), "Name,Phone & Gmail Entry is Mandatory", Toast.LENGTH_LONG).show();

                }
    }
}
