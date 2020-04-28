package com.example.dcl.shourov;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText signUP_email ,signUP_pass;
    Button signUP_btn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        signUP_email = (EditText)findViewById(R.id.Email_signUP);
        signUP_pass = (EditText) findViewById(R.id.Password_signUP);
        signUP_btn = (Button) findViewById(R.id.SignUP_btn);


        signUP_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass;
                email = signUP_email.getText().toString();
                pass = signUP_pass.getText().toString();
                if (! TextUtils.isEmpty(email) && ! TextUtils.isEmpty(pass)) {

                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),"Registration Done",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), Login.class);
                                startActivity(i);

                            }

                            else
                                {

                                String e  = task.getException().getLocalizedMessage();
                                Toast.makeText(getApplicationContext(), "Error"+e,Toast.LENGTH_LONG).show();
                            }


                        }


                    });


                }
                else if (! TextUtils.isEmpty(email))

                {
                    Toast.makeText(getApplicationContext(), "Please Enter Password",Toast.LENGTH_LONG).show();
                }

                else if (! TextUtils.isEmpty(pass))

                {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Email", Toast.LENGTH_LONG).show();
                }

                else if( TextUtils.isEmpty(email) &&  TextUtils.isEmpty(pass)){


                    Toast.makeText(getApplicationContext(), "Email And Password Must Required", Toast.LENGTH_LONG).show();

            }

            }
        });




    }

}

