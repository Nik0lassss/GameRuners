package com.nicholaschirkevich.game.util;

import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;

import java.util.ArrayList;

/**
 * Created by Nikolas on 10.06.2016.
 */
public class CarSortingManager {

    static ArrayList<String> tamplateList = new ArrayList<String>() {{
        add("SP000");
        add("CC080");
        add("CC100");
        add("CC220");
        add("SP220");
        add("CC280");
        add("CC430");
        add("CC130");
        add("CC170");
        add("SP100");
        add("SP220");
        add("SP130");
        add("SP8");
        add("EC5");
        add("EC2");
        add("EC1");
        add("EC3");
        add("");
        add("CC350");
        add("SP280");
        add("SP080");
        add("CC1022");
        add("CC1023");
        add("CC1024");
        add("SP0270");
        add("CC1025");
    }};

    public static ArrayList<Car> sortCars(ArrayList<CarsType> carsTypes) {
        ArrayList<Car> sortCar = new ArrayList<Car>();
        for (String carTamplateId : tamplateList) {
            for (CarsType carType : carsTypes)
                for (Car car : carType.getCars()) {
                    if (car.getID().equals(carTamplateId)) {
                        sortCar.add(car);
                    }
                }
        }
        return sortCar;
    }
}
