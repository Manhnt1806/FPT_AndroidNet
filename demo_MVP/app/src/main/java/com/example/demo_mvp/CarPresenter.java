package com.example.demo_mvp;

public class CarPresenter {
    public CarView carView;
    public CarPresenter(CarView carView){
        this.carView = carView;
    }
    public void getCarInfo(int id){
        //Giả sử như có lệnh truy vấn DB thì làm ở đây
        CarModel carModel = new CarModel(id, "BWM");
        carView.displayCarInfo(carModel);
    }
}
