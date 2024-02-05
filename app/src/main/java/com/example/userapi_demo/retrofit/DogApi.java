package com.example.userapi_demo.retrofit;

import com.example.userapi_demo.model.Dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface DogApi {
    @GET("/api/")
    Call<List<Dog>> getAllDogs();

    @POST("/api/add-dog")
    Call<Dog> save(@Body Dog dog);

    @GET("/api/dogs/{id}")
    Call<Dog> getDog(@Body Dog dog);
}
