package com.nicholaschirkevich.game.entity;

import java.util.ArrayList;

/**
 * Created by Nikolas on 11.03.2016.
 */
public class CarsType {
    private Integer id;
    private String name;
    private ArrayList<Car> cars;
    private String titleImage;
    private String titleImageName;


    public CarsType() {
        cars = new ArrayList<Car>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getTitleImageName() {
        return titleImageName;
    }

    public void setTitleImageName(String titleImageName) {
        this.titleImageName = titleImageName;
    }
}
