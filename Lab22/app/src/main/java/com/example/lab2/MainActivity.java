package com.example.lab2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab2.json.Root;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String apiPost = "https://postman-echo.com/post";
    String randomUser = "https://api.randomuser.me/?results=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtAge = findViewById(R.id.edtAge);
        Button btnGet = findViewById(R.id.btnGet);
        Button btnPost = findViewById(R.id.btnPost);
        Lab2AsyncTask asyncTask = new Lab2AsyncTask();
        Lab2AsyncTask2 asyncTask2 = new Lab2AsyncTask2  ();

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask.setListener(new Lab2Listener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinished(String result) {
                        Root root = new Gson().fromJson(result,Root.class);
                        TextView txtRoot = findViewById(R.id.txtRoot);
                        txtRoot.setText("Seed: "+root.getInfo().getSeed().toString());
                    }
                    @Override
                    public void onUpdate(int progress) {

                    }
                });
                asyncTask.execute(randomUser, "GET");
            }
        });


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask2.setListener(edtName.getText().toString(), Integer.parseInt(edtAge.getText().toString()), new Lab2Listener() {
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