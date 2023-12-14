package com.example.lab3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.json.Result;
import com.example.lab3.json.Root;
import com.google.gson.Gson;

import java.io.Reader;
import java.util.List;

public class RcvAdapter extends RecyclerView.Adapter<RcvAdapter.ResultViewHolder> {
    private Context context;
    private List<Result> mList;
    public RcvAdapter(Context context, List<Result> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.cv, parent, false);
            RcvAdapter.ResultViewHolder viewHolder = new RcvAdapter.ResultViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RcvAdapter.ResultViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Result result = mList.get(position);
            holder.tvName.setText("Name: " + result.getName().getFirst() +" "+ result.getName().getLast());
            holder.tvEmail.setText("Email: " + result.getEmail());
            holder.tvGender.setText("Gender: " + result.getGender());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvEmail, tvGender;
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvGender = itemView.findViewById(R.id.tv_gender);
        }
    }
}
