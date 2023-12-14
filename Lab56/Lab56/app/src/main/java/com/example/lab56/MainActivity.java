package com.example.lab56;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.lab56.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ContactsViewModel viewModel;
    ContactsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.rcUser.setLayoutManager(manager);
        viewModel.getUserListLiveData().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> users) {
                adapter = new ContactsAdapter(MainActivity.this,users);
                binding.rcUser.setAdapter(adapter);
            }
        });
        binding.setViewModel(viewModel);
    }
}