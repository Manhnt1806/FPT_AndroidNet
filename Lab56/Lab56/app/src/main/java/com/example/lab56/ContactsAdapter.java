package com.example.lab56;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab56.databinding.ItemUserBinding;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    Context mContext;
    List<Contacts> list;

    public ContactsAdapter(Context mContext, List<Contacts> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ItemUserBinding binding = ItemUserBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contacts user = list.get(position);
        holder.binding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public final class MyViewHolder extends RecyclerView.ViewHolder{
        ItemUserBinding binding;
        public MyViewHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
