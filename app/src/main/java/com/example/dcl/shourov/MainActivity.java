package com.example.dcl.shourov;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {

                    sleep(2 * 1000);
                    Intent i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);

                }
                catch (Exception ex) {
                }
            }


        };
        thread.start();
    }
}
