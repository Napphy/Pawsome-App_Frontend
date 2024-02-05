package com.example.userapi_demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapi_demo.R;
import com.example.userapi_demo.model.Dog;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogHolder> {

    private List<Dog> dogList;

    public DogAdapter(List<Dog> dogList) {
        this.dogList = dogList;
    }


    @NonNull
    @Override
    public DogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_dog_item, parent, false);
        return new DogHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogHolder holder, int position) {
        Dog dog = dogList.get(position);
        holder.name.setText(dog.getName());
        holder.age.setText(String.valueOf(dog.getId()));
    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }
}
