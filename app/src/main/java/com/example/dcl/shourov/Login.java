package com.example.dcl.shourov;



import   android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email, pass;
    Button login, signUP;
    FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email_signin);
        pass = (EditText) findViewById(R.id.pass_signin);
        login = (Button) findViewById(R.id.Login_btn);
        signUP = (Button) findViewById(R.id.SignUP_btn);

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j = new Intent (getApplicationContext(),Signup.class);
                startActivity(j);



            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sign_email, sign_pass;
                sign_email = email.getText().toString();
                sign_pass = pass.getText().toString();


                if (!TextUtils.isEmpty(sign_email) && !TextUtils.isEmpty(sign_pass)) {

                    mAuth.signInWithEmailAndPassword(sign_email , sign_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Intent s = new Intent(getApplicationContext(),Choise.class);
                                startActivity(s);

                            }
                            else
                            {
                                String a = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),"Error"+a,Toast.LENGTH_LONG).show();



                            }


                        }




                    });





                }
                else {

                    Toast.makeText(getApplicationContext(), "Enter Both Email & Password", Toast.LENGTH_LONG).show();
                }

            }


        });




    }

}



