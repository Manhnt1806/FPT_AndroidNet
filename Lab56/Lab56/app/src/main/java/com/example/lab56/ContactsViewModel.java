package com.example.lab56;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactsViewModel extends ViewModel {
    public MutableLiveData<List<Contacts>> listMutableLiveData;
    public List<Contacts> list;

    int index = 1;
    public ContactsViewModel() {
        listMutableLiveData = new MutableLiveData<>();
        initData();
    }

    public void initData() {
        list = new ArrayList<>();
        listMutableLiveData.setValue(list);
    }

    public MutableLiveData<List<Contacts>> getUserListLiveData() {
        return listMutableLiveData;
    }

    public void addUSer(Contacts user){
        list.add(user);
        listMutableLiveData.setValue(list);
    }

    public void clickAddUSer(View view){
        Contacts user = new Contacts("Name "+index , "Số điện thoại: 098765432" + index);
        addUSer(user);
        index++;
    }
}
