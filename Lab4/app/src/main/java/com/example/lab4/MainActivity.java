package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.lab4.Gson.IconRoot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<IconRoot> iconRoots = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://icon.io.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Lab4Service randomService = retrofit.create(Lab4Service.class);
        Call<IconRoot> userList = randomService.getListIcon(1);
        userList.enqueue(new Callback<IconRoot>() {
            @Override
            public void onResponse(Call<IconRoot> call, Response<IconRoot> response) {
                recyclerViewAdapter.setData(iconRoots);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<IconRoot> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
}