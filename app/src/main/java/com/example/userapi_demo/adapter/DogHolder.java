package com.example.userapi_demo.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapi_demo.R;


public class DogHolder extends RecyclerView.ViewHolder {

    TextView name, age;
    public DogHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.dogList_name);
        age = itemView.findViewById(R.id.dogList_age);
    }
}
