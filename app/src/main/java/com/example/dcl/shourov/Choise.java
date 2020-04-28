package com.example.dcl.shourov;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Choise extends AppCompatActivity {
    RadioButton rd1 , rd2;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise);

        rd1 = (RadioButton)findViewById(R.id.radbtn1);
        rd2 = (RadioButton)findViewById(R.id.radbtn2);
        bt = (Button)findViewById(R.id.btn);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rd1.isChecked() == true) {

                    Intent i = new Intent(getApplicationContext(),Search.class);
                    startActivity(i);


                }
                else

                {

                    Intent j = new Intent(getApplicationContext(),Entry.class);
                    startActivity(j);


                }

            }
        });

    }
}
