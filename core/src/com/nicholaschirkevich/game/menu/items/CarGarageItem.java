package com.nicholaschirkevich.game.menu.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.interfaces.UpdateGarageTable;
import com.nicholaschirkevich.game.listeners.CarGarageItemClickListener;
import com.nicholaschirkevich.game.interfaces.UpdateGarageCarItem;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class CarGarageItem extends Group implements UpdateGarageCarItem {
    Texture slot_vehicle;
    Texture car_texture;
    Texture speed_text;
    Texture speed_bar;
    Texture weight_text;
    Texture weight_bar;
    Texture delimiterTexture;
    Image background;
    Image icon;
    Car car;
    UpdateGarageTable updateGarageTable;
    Integer index;
    Skin skin;
    Image buyImage;
    Label countLabel;
    ActionResolver actionResolver;


    public CarGarageItem(Car car, Integer index, UpdateGarageTable updateGarageTable, Skin skin, ActionResolver actionResolver) {


        this.actionResolver = actionResolver;
        this.updateGarageTable = updateGarageTable;
        this.index = index;
        car_texture = AssetsManager.getTextureRegion(car.getID()).getTexture();
        speed_text = AssetsManager.getTextureRegion(Constants.SPEED_TEXT_ID).getTexture();
        speed_bar = AssetsManager.getTextureRegion(Constants.SPEED_BAR_ID).getTexture();
        delimiterTexture = AssetsManager.getTextureRegion(Constants.DELIMITER_ID).getTexture();
        weight_text = AssetsManager.getTextureRegion(Constants.WEIGHT_TEXT_ID).getTexture();
        weight_bar = AssetsManager.getTextureRegion(Constants.WEIGHT_BAR_ID).getTexture();
        buyImage = new Image();
        countLabel = new Label("", skin);
        this.car = car;
        this.skin = skin;
        if (GameManager.getCurrentCar().getID().equals(car.getID())) setUpBackgroung(true);
        else setUpBackgroung(false);
        setUpCarName(car.getCarNameImage());
        setUpIcon();
        setUpIconSpeed();
        setUpIconWeight();
        setUpIconSpeedBar();
        setUpIconWeightBar();
        setUpDelimiterSpeedBar();
        setUpBuyImage();
        addListener(new CarGarageItemClickListener(this));


        setBounds(0, 0, slot_vehicle.getWidth(), slot_vehicle.getHeight());
    }


    public void setUpBuyImage() {

        if (car.getID().equals(GameManager.getCurrentCar().getID())) {
            buyImage = new Image(AssetsManager.getTextureRegion(Constants.BTTN_CAR_SHOP_CHECKED_ID));
            buyImage.setBounds(getX() + 253, getY() + 28, buyImage.getPrefWidth(), buyImage.getPrefHeight());
            addActor(buyImage);

        } else {
            if (!car.getID().equals("SP000")) {
                if (!GameManager.getMyCars().contains(car.getID())) {
                    if (car.getIsForRealMoney()) {
                        buyImage = new Image(AssetsManager.getTextureRegion(Constants.BTTN_CAR_SHOP_BUY_REAL_EMPTY_DIS_ID));
                        countLabel = new Label(car.getPrice().toString(), skin);
                    } else {
                        countLabel = new Label(String.valueOf(car.getPrice().intValue()), skin);
                        if (GameManager.getCoinCounter() > car.getPrice())
                            buyImage = new Image(AssetsManager.getTextureRegion(Constants.BTTN_CAR_SHOP_BUY_EMPTY_ID));
                        else
                            buyImage = new Image(AssetsManager.getTextureRegion(Constants.BTTN_CAR_SHOP_BUY_EMPTY_DIS_ID));
                    }
                }
            }

            countLabel.setBounds(getX() + 275 - countLabel.getPrefWidth() / 4, getY() + 28, countLabel.getPrefWidth(), countLabel.getPrefHeight());
            countLabel.setFontScale(0.4f, 0.4f);
            buyImage.setBounds(getX() + 223, getY() + 28, buyImage.getPrefWidth(), buyImage.getPrefHeight());
            addActor(buyImage);
            addActor(countLabel);

        }


    }

    private void setUpBackgroung(boolean selected) {
        if (selected) {
            updateGarageTable.setSelectedItme(index);
            slot_vehicle = AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_SELECTED_ID).getTexture();
        } else
            slot_vehicle = AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_ID).getTexture();
        background = new Image(slot_vehicle);
        background.setBounds(getX(), getY(), slot_vehicle.getWidth(), slot_vehicle.getHeight());
        addActor(background);

    }

    private void setUpIcon() {
        icon = new Image(car_texture);
        icon.setBounds(getX() + 39 - car_texture.getWidth() / 2, getY() + 7, car_texture.getWidth(), car_texture.getHeight());
        addActor(icon);
    }

    private void setUpIconSpeed() {
        Label speedLabel = new Label(GameManager.getStrings().get(Constants.SC_SPEED_LBL), AssetsManager.getUiSkin());
        speedLabel.setBounds(getX() + 85, getY() + 48, 40, 10);
        speedLabel.setFontScale(0.3f, 0.3f);
        addActor(speedLabel);
        // Image iconSpeed = new Image(speed_text);
//        iconSpeed.setBounds(getX() + 93, getY() + 48, 40, 10);
//        addActor(iconSpeed);
    }

    private void setUpIconSpeedBar() {


        TextureRegion speedBar = new TextureRegion(speed_bar);
        speedBar.setRegion(0, 0, car.getSpeed(), 8);
        Image iconSpeedBar = new Image(speedBar);
        iconSpeedBar.setX(getX() + 93);
        iconSpeedBar.setY(getY() + 35);
        addActor(iconSpeedBar);
    }

    private void setUpDelimiterSpeedBar() {

        Image delimiter = new Image(delimiterTexture);
        delimiter.setBounds(getX() + 78, getY(), delimiter.getWidth(), delimiter.getHeight());
        addActor(delimiter);
    }

    private void setUpIconWeightBar() {

        TextureRegion weigthBar = new TextureRegion(weight_bar);
        weigthBar.setRegion(0, 0, car.getWeight(), 8);
        weigthBar.split(10, weigthBar.getRegionHeight());
        Image iconWeightBar = new Image(weigthBar);

        iconWeightBar.setX(getX() + 93);
        iconWeightBar.setY(getY() + 8);
        addActor(iconWeightBar);
    }

    private void setUpIconWeight() {
        Label weightLabel = new Label(GameManager.getStrings().get(Constants.SC_WEIGHT_LBL), AssetsManager.getUiSkin());
        weightLabel.setFontScale(0.3f,0.3f);
        weightLabel.setBounds(getX() + 85, getY() + 20, 40, 10);
        addActor(weightLabel);
//        Image iconWeight = new Image(weight_text);
//        iconWeight.setBounds(getX() + 93, getY() + 20, 40, 10);
//        addActor(iconWeight);
    }

    private void setUpCarName(String imageId) {
        Label labelName = new Label(GameManager.getStrings().get(car.getCarNameText()), AssetsManager.getUiSkin());
        labelName.setFontScale(0.5f, 0.5f);
//        Texture carNameTexture = AssetsManager.getTextureRegion(imageId).getTexture();
//        Image carName = new Image(carNameTexture);
//        carName.setBounds(getX() + 93, getY() + 65, carNameTexture.getWidth(), carNameTexture.getHeight());
        labelName.setX(getX() + 87);
        labelName.setY(getY() + 65);
        addActor(labelName);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void onUpdateGarageCarItem(boolean selected) {

        updateGarageCarItem(selected);

    }

    public void updateGarageCarItem(boolean selected) {
        if (!car.getIsForRealMoney() && GameManager.getCoinCounter() > car.getPrice() && !GameManager.getMyCars().contains(car.getID())) {
            GameManager.buyCar(car.getPrice());
            if (selected) {

                GameManager.addCar(car.getID());
                GameManager.setCurrentCarID(car.getID());
                buyImage.remove();
                countLabel.setText("");
                buyImage = new Image(AssetsManager.getTextureRegion(Constants.BTTN_CAR_SHOP_CHECKED_ID));
                buyImage.setX(253);
                buyImage.setY(28);
                addActor(buyImage);
                background.setDrawable(new SpriteDrawable(new Sprite(AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_SELECTED_ID))));
                updateGarageTable.updateTable();
                updateGarageTable.setSelectedItme(index);
            } else {
                buyImage.remove();
                background.setDrawable(new SpriteDrawable(new Sprite(AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_ID))));
            }

        }else if(car.getIsForRealMoney() &&!GameManager.getMyCars().contains(car.getID())){
         if(selected)
         {
             actionResolver.buyProduct("sp000");
         }
            else {

         }
        } else if (GameManager.getMyCars().contains(car.getID())) {

            if (selected) {
                GameManager.setCurrentCarID(car.getID());
                background.setDrawable(new SpriteDrawable(new Sprite(AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_SELECTED_ID))));
                buyImage = new Image(AssetsManager.getTextureRegion(Constants.BTTN_CAR_SHOP_CHECKED_ID));
                buyImage.setX(253);
                buyImage.setY(28);
                addActor(buyImage);
                updateGarageTable.updateTable();
                updateGarageTable.setSelectedItme(index);
            } else {
                buyImage.remove();
                background.setDrawable(new SpriteDrawable(new Sprite(AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_ID))));

            }

        }
    }


}
