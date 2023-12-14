package com.example.lab4;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.Gson.IconRoot;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.IconRootViewHolder> {
    public List<IconRoot> mItemList;
    public void setData(List<IconRoot> mItemList) {
        this.mItemList = mItemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.IconRootViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new IconRootViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.IconRootViewHolder holder, int position) {
        IconRoot iconRoot = mItemList.get(position);
        if(iconRoot == null){
            return;
        }
        holder.tvName.setText(iconRoot.getName());
        holder.imgIcon.setImageResource(Integer.parseInt(iconRoot.getAvatar()));
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }
    public class IconRootViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView imgIcon;
        public IconRootViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItem);
            imgIcon = itemView.findViewById(R.id.imgIcon);
        }
    }

}
