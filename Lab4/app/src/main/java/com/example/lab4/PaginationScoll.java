package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScoll extends RecyclerView.OnScrollListener {
    private LinearLayoutManager linearLayoutManager;

    public PaginationScoll(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }
    public abstract void loadmoreItems();
    public abstract boolean isLoading();
}
