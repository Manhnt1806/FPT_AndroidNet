package com.example.asm_androidnet.Adapter;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm_androidnet.Api.ApiPhoto;
import com.example.asm_androidnet.DetailActivity;
import com.example.asm_androidnet.Model.Datum;
import com.example.asm_androidnet.Model.Example;
import com.example.asm_androidnet.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private Context context;
    private List<Datum> list;
    private Retrofit retrofit;

    public PhotoAdapter(Context context, List<Datum> list, Retrofit retrofit) {
        this.context = context;
        this.list = list;
        this.retrofit = retrofit;

    }
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Datum photo =list.get(position);
        String colorHex = photo.getColor();
        int colorInt = Color.parseColor(colorHex);
        holder.img.setBackgroundColor(colorInt);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DetailActivity.class)
                        .putExtra("key_photo", photo));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}
