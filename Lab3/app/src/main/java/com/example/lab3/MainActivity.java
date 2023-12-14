package com.example.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.json.Result;
import com.example.lab3.json.Root;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String apiPost = "https://postman-echo.com/post";
    String randomUser = "https://api.randomuser.me/?results=10";
    List<Result> results =new ArrayList<>();
    RcvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtAge = findViewById(R.id.edtAge);
        Button btnGet = findViewById(R.id.btnGet);
        Button btnPost = findViewById(R.id.btnPost);

//        Lab3AsyncTask asyncTask = new Lab3AsyncTask();
        Lab3AsyncTask2 asyncTask2 = new Lab3AsyncTask2();

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView rcvGet = findViewById(R.id.rcv);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                rcvGet.setLayoutManager(layoutManager);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.randomuser.me/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Lab3Service randomService = retrofit.create(Lab3Service.class);
                Call<Root> userList = randomService.getListUser(10);
                userList.enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        Root root = response.body();
                        results = root.getResults();
                        adapter = new RcvAdapter(MainActivity.this, results);
                        rcvGet.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {
                        Toast.makeText(MainActivity.this,
                                t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask2.setListener(edtName.getText().toString(), Integer.parseInt(edtAge.getText().toString()), new Lab3Listener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinished(String result) {

                        try {
                            JSONObject post = new JSONObject(result);
                            JSONObject form = post.getJSONObject("form");
                            String name = form.getString("name");
                            String age = form.getString("age");
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                            builder.setTitle("Name: "+name+" - Age: "+age +"\n");
                            builder.create().show();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Sai format JSON !!!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onUpdate(int progress) {

                    }
                });
                asyncTask2.execute(apiPost, "POST");
            }
        });
    }
}