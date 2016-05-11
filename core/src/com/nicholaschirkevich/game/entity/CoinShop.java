package com.nicholaschirkevich.game.entity;

/**
 * Created by Nikolas on 10.05.2016.
 */
public class CoinShop {
    private int id;
    private float price;
    private int coins;
    private String name;
    private int discount;
    private String image_source;

    public CoinShop(int id, float price, int coins, String name, int discount, String image_source) {
        this.id = id;
        this.price = price;
        this.coins = coins;
        this.name = name;
        this.discount = discount;
        this.image_source = image_source;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }
}
