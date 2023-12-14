package com.example.asm_androidnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asm_androidnet.Adapter.PhotoAdapter;
import com.example.asm_androidnet.Api.ApiPhoto;
import com.example.asm_androidnet.Model.Datum;
import com.example.asm_androidnet.Model.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public RecyclerView rcvPhoto;

    public ProgressBar progressBar;
    public PhotoAdapter adapter;
    Retrofit retrofit;
    List<Datum> photoList = new ArrayList<>();
    int page = 1;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvPhoto = findViewById(R.id.rcv_Photo);
        progressBar = findViewById(R.id.progressBar);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcvPhoto.setLayoutManager(staggeredGridLayoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loadData();

        rcvPhoto.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = staggeredGridLayoutManager.getChildCount();
                int totalItemCount = staggeredGridLayoutManager.getItemCount();
                int[] firstVisibleItems = staggeredGridLayoutManager.findFirstVisibleItemPositions(null);

                if (!isLoading && (firstVisibleItems[0] + visibleItemCount) >= totalItemCount) {
                    page++;
                    loadMoreData();
                }
            }
        });
    }



    public void loadData(){
        ApiPhoto apiPhoto = retrofit.create(ApiPhoto.class);
        Call<Example> list = apiPhoto.getList(page, 1);
        list.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example listPhotos = response.body();
                if (listPhotos != null) {
                    photoList = listPhotos.getData();
                    adapter = new PhotoAdapter(MainActivity.this, photoList, retrofit);
                    rcvPhoto.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void loadMoreData() {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);

        ApiPhoto apiPhoto = retrofit.create(ApiPhoto.class);
        Call<Example> list = apiPhoto.getList(page, 1);
        list.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example listPhotos = response.body();
                if (listPhotos != null) {
                    List<Datum> newPhotos = listPhotos.getData();
                    photoList.addAll(newPhotos);
                    adapter.notifyDataSetChanged();
                }

                isLoading = false;
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                isLoading = false;
            }
        });
    }
}