package com.example.lab4;

import com.example.lab4.Gson.IconRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Lab4Service {
    //getLatestIconPacks?page=1
    @GET("/getLatestIconPacks/")
    Call<IconRoot> getListIcon(@Query("page") int page);
}
