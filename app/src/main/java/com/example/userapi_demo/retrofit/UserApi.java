package com.example.userapi_demo.retrofit;
import com.example.userapi_demo.model.User;
import java.util.List;
import retrofit2.*;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface UserApi {
    @GET("/api/")
    Call<List<User>> getAllUsers();

    @POST("/api/add-dog")
    Call<User> save(@Body User user);
}
