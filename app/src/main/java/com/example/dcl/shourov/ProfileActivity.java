package com.example.dcl.shourov;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    TextView nameTV, workTV;
    Button phoneBTN, emailBTN;
    String name, email, phone, work;
    public static final int MY_PERMISSIONS_REQUEST_CALL = 52;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
    }

    private void init() {

        nameTV = (TextView) findViewById(R.id.name_TV);
        workTV = (TextView) findViewById(R.id.work_TV);
        phoneBTN = (Button) findViewById(R.id.phone_BTN);
        emailBTN = (Button) findViewById(R.id.email_BTN);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        work = intent.getStringExtra("work");

        nameTV.setText(name);
        workTV.setText(work);
        phoneBTN.setText(phone);
        emailBTN.setText(email);


        emailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendEmail(emailBTN.getText().toString());
            }
        });

        phoneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeCall(phoneBTN.getText().toString());
            }
        });

    }


    public void makeCall(String number){

        if (phoneBTN.getText().toString().equals("")){

            Toast.makeText(ProfileActivity.this, "No number found for phone call !!!", Toast.LENGTH_SHORT).show();


        }else {


            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));

            if (!checkCallPermission()) {

                checkCallPermission();

            }else {

                startActivity(callIntent);
            }


        }
    }


    public void sendEmail(String email) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        i.putExtra(Intent.EXTRA_TEXT   , "Hello ,");

        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ProfileActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean checkCallPermission() {


        if (ContextCompat.checkSelfPermission(ProfileActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(ProfileActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL);


            return false;

        } else {

            return true;
        }
    }

}
