package com.example.userapi_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.userapi_demo.adapter.DogAdapter;
import com.example.userapi_demo.model.Dog;
import com.example.userapi_demo.retrofit.DogApi;
import com.example.userapi_demo.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Viewing extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing);

        recyclerView = findViewById(R.id.dogs_recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDogs();
    }

    private void loadDogs(){
        RetrofitService retrofitService = new RetrofitService();
        DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);
        dogApi.getAllDogs()
                .enqueue(new Callback<List<Dog>>() {
                    @Override
                    public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Dog>> call, Throwable t) {
                        Toast.makeText(Viewing.this, "Failed to load dogs", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void populateListView(List<Dog> dogList) {
        DogAdapter dogAdapter = new DogAdapter(dogList);
        recyclerView.setAdapter(dogAdapter);
    }

}