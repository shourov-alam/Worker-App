package com.example.dcl.shourov;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<SaveData> saveDataList;
    Activity activity;
    Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameTV;

        public MyViewHolder(@NonNull View view) {
            super(view);

            nameTV = (TextView) view.findViewById(R.id.name_TV);
        }
    }


    public MyAdapter(List<SaveData> saveDataList, Activity activity, Context context) {
        this.saveDataList = saveDataList;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final SaveData saveData = saveDataList.get(position);
        holder.nameTV.setText(saveData.getNam());

        holder.nameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("name", saveData.getNam());
                intent.putExtra("email", saveData.getEml());
                intent.putExtra("phone", saveData.getPhn());
                intent.putExtra("work", saveData.getWk1() +"\n" + saveData.getWk2() + "\n" + saveData.getWk3());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return saveDataList.size();
    }
}
