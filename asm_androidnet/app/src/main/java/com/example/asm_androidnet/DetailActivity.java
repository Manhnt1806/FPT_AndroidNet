package com.example.asm_androidnet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.asm_androidnet.Model.Datum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity {
    String url="", urlDown="";
    ImageView imgFull;
    TextView tvAlt, tvId, tvPhotographer, tvKichThuoc, tvColor;
    ImageButton btnDown, btnSetWall;
    Datum photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imgFull = findViewById(R.id.detail_photo);
        tvAlt = findViewById(R.id.alt_photo);
        tvId = findViewById(R.id.id_photo);
        tvPhotographer = findViewById(R.id.tv_photographer);
        tvKichThuoc = findViewById(R.id.tv_kichthuoc);
        tvColor = findViewById(R.id.tv_color);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        if (intent != null) {
            Datum photo = (Datum) intent.getSerializableExtra("key_photo");
            if (photo != null) {
                String colorHex = photo.getColor();
                int colorInt = Color.parseColor(colorHex);
                imgFull.setBackgroundColor(colorInt);
                tvId.setText(photo.getId()+"");
                tvAlt.setText(photo.getName());
                tvColor.setText(photo.getColor());
                tvKichThuoc.setText(photo.getYear()+"");
            }
        }

    }


}