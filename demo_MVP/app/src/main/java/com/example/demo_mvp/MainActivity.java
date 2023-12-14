package com.example.demo_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
implements CarView{
    public CarPresenter carPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carPresenter = new CarPresenter(this);
        carPresenter.getCarInfo(1000);

    }

    @Override
    public void displayCarInfo(CarModel carModel) {
        Toast.makeText(this, carModel.name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayListCar(List<CarModel> carModelList) {

    }
}