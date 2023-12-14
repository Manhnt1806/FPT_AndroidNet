package com.example.asm_androidnet.Api;

import com.example.asm_androidnet.Model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
public interface ApiPhoto {
    //https://reqres.in/api/unknown?page=1
    @GET("api/unknown")
    Call<Example> getList(@Query("page") int page,
                          @Query("per_page") int per_page);
}

