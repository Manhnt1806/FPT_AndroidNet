package com.example.lab78;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public SearchView searchView;
    UserAdapter userAdapter;
    Retrofit retrofit;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button refreshButton;
    private ProgressDialog progressDialog;
    List<Example> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_user);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        refreshButton = findViewById(R.id.refreshButton);

        // Thiết lập sự kiện cho SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this::refreshData);

        // Thiết lập sự kiện cho Button
        refreshButton.setOnClickListener(view -> refreshData());

        // Hiển thị ProgressDialog khi tải dữ liệu
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải...");
        progressDialog.setCancelable(false);

        // Khởi động lần đầu
        refreshData();

    }
    public void onRefreshButtonClick(View view) {
        refreshData();
    }
    private void refreshData() {
        progressDialog.show();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiUsser apiPhoto = retrofit.create(ApiUsser.class);
        Call<List<Example>> call = apiPhoto.getListUser();
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                progressDialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                list = response.body();
                if (list != null) {
                    for (Example example : list) {
                        example.setShowStar(example.getId() % 3 == 0);
                    }
                    userAdapter = new UserAdapter(MainActivity.this, list, retrofit);
                    recyclerView.setAdapter(userAdapter);
                    userAdapter.setOnAlbumLongClickListener(position -> {
                        showDeleteConfirmationDialog(position);
                    });
                }
            }
            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                progressDialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showDeleteConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc muốn xóa album này?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            deleteAlbum(position);
        });
        builder.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }
    private void deleteAlbum(int position) {
        if (list != null && list.size() > position) {
            list.remove(position);
            userAdapter.notifyItemRemoved(position);
        } else {
            Log.e("DeleteAlbum", "Invalid position or empty list: position = " + position + ", list size = " + (list != null ? list.size() : 0));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    public void onBackPressed(){
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}