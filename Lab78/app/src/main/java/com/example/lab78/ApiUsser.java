package com.example.lab78;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiUsser {
    @GET("albums")
    Call<List<Example>> getListUser();
}
