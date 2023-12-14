package com.example.demo_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

public class CarInfoActivity extends AppCompatActivity implements CarView {
    CarPresenter carPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        carPresenter = new CarPresenter(this);
        carPresenter.getCarInfo(3333);
    }

    @Override
    public void displayCarInfo(CarModel carModel) {

    }

    @Override
    public void displayListCar(List<CarModel> carModelList) {

    }
}