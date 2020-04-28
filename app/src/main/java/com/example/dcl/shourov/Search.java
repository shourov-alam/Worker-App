package com.example.dcl.shourov;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    private EditText workET;
    private Button searchBTN;
    private RecyclerView personRV;
    private DatabaseReference databaseReference;
    String searchText;
    List<SaveData> saveDataList = new ArrayList<>();
    List<SaveData> searchedList = new ArrayList<>();
    SaveData saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        getData();

        workET = (EditText) findViewById(R.id.work_ET);
        searchBTN = (Button) findViewById(R.id.search_BTN);
        personRV = (RecyclerView) findViewById(R.id.person_RV);
        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (workET.getText().toString().equals("")) {

                    workET.setError("Please enter a value !!!");

                } else {

                    searchText = workET.getText().toString();

                    if( saveDataList.size() >0 ){

                        for(int i=0; i< saveDataList.size(); i++){

                            if (saveDataList.get(i).getWk1().contains(searchText)
                                    || saveDataList.get(i).getWk2().contains(searchText)
                                    || saveDataList.get(i).getWk3().contains(searchText)) {

                                SaveData saveData = saveDataList.get(i);
                                searchedList.add(saveData);
                            }
                        }


                        MyAdapter adapter = new MyAdapter(searchedList, Search.this, Search.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Search.this, 1);
                        personRV.setLayoutManager(layoutManager);
                        personRV.addItemDecoration(new GridSpacingItemDecoration(1, 0, true));
                        personRV.setItemAnimator(new DefaultItemAnimator());
                        personRV.setAdapter(adapter);

                    }

                }


            }
        });

    }

    private  void getData(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    saveData = snapshot.getValue(SaveData.class);
                    saveDataList.add(saveData);

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}



