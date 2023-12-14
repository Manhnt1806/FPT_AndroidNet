package com.example.lab3;

import com.example.lab3.json.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Lab3Service {
    @GET("/")
    Call<Root> getListUser(@Query("results") int results);
}
