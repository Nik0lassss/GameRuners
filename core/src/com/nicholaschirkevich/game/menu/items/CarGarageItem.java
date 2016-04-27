package com.nicholaschirkevich.game.menu.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.interfaces.UpdateGarageTable;
import com.nicholaschirkevich.game.listeners.CarGarageItemClickListener;
import com.nicholaschirkevich.game.interfaces.UpdateGarageCarItem;
import com.nicholaschirkevich.game.util.AssetsManager;
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
    Car car;
    UpdateGarageTable updateGarageTable;
    Integer index;

    //    public CarGarageItem() {
//
//
//
//        slot_vehicle = new  Texture("slot_vehicle.png");
//        car_texture = new  Texture("other_car_1_1.png");
//        speed_text = new Texture("speed_text.png");
//        speed_bar = new Texture("speed_bar.png");
//
//        weight_text = new Texture("weight_text.png");
//        weight_bar = new Texture("weight_bar.png");
//
//
//        Pixmap bigTexture = new Pixmap(Gdx.files.getFileHandle("weight_bar.png", Files.FileType.Internal));
//
//        int w = weight_bar.getWidth();
//        int h = weight_bar.getHeight();
//        int srcX =  weight_bar.getHeight();
//        int srcY =  weight_bar.getHeight();
//
//        Pixmap partTexture = new Pixmap(w, h, Pixmap.Format.RGBA8888);
//        partTexture.drawPixmap(bigTexture, 0, 0, srcX, srcY, w, h);
//
//        Texture tx = new Texture(partTexture, Pixmap.Format.RGBA8888, false);
//
//
//        Image background = new Image(slot_vehicle);
//        background.setBounds(getX(), getY(), slot_vehicle.getWidth(), slot_vehicle.getHeight());
//        addActor(background);
//
//
//        Image icon = new Image(car_texture);
//        icon.setBounds(getX()+3, getY()+3, 80, 80);
//        addActor(icon);
//
//
//        Image iconSpeed = new Image(speed_text);
//        iconSpeed.setBounds(getX()+88, getY()+40, 40, 10);
//        addActor(iconSpeed);
//
//        Image iconSpeedBar = new Image(speed_bar);
//        iconSpeedBar.setBounds(getX()+88, getY()+30, 90, 8);
//        addActor(iconSpeedBar);
//
//        Image iconWeight = new Image(weight_text);
//        iconWeight.setBounds(getX()+88, getY()+20, 40, 10);
//        addActor(iconWeight);
//
//        Image iconWeightBar = new Image(weight_bar);
//        iconWeightBar.setOrigin(40,8);
//        //iconWeightBar.setScaling(Scaling.fillY);
//        iconWeightBar.setBounds(getX()+88, getY()+10, 20, 8);
//
//        addActor(iconWeightBar);
////        sprite = new Sprite(slot_vehicle);
//
//        setBounds(0, 0, slot_vehicle.getWidth(), slot_vehicle.getHeight());
//    }


    public CarGarageItem(Car car, Integer index, UpdateGarageTable updateGarageTable) {


        this.updateGarageTable = updateGarageTable;
        this.index = index;
        //car_texture = new  Texture("other_car_2_1.png");
        car_texture = AssetsManager.getTextureRegion(car.getID()).getTexture();
        speed_text = new Texture("speed_text.png");
        speed_bar = new Texture("speed_bar.png");
        delimiterTexture = new Texture("delimiter.png");
        weight_text = new Texture("weight_text.png");
        weight_bar = new Texture("weight_bar.png");
        this.car = car;
        if (GameManager.getCurrentCar().getID().equals(car.getID())) setUpBackgroung(true);
        else setUpBackgroung(false);
        setUpCarName(car.getCarNameImage());
        setUpIcon();
        setUpIconSpeed();
        setUpIconWeight();
        setUpIconSpeedBar();
        setUpIconWeightBar();
        setUpDelimiterSpeedBar();
        addListener(new CarGarageItemClickListener(this));

//        Pixmap bigTexture = new Pixmap(Gdx.files.getFileHandle("weight_bar.png", Files.FileType.Internal));
//
//        int w = weight_bar.getWidth();
//        int h = weight_bar.getHeight();
//        int srcX =  weight_bar.getHeight();
//        int srcY =  weight_bar.getHeight();
//
//        Pixmap partTexture = new Pixmap(w, h, Pixmap.Format.RGBA8888);
//        partTexture.drawPixmap(bigTexture, 0, 0, srcX, srcY, w, h);
//
//        Texture tx = new Texture(partTexture, Pixmap.Format.RGBA8888, false);


//        sprite = new Sprite(slot_vehicle);

        setBounds(0, 0, slot_vehicle.getWidth(), slot_vehicle.getHeight());
    }

    private void setUpBackgroung(boolean selected) {
        if (selected) {
            updateGarageTable.setSelectedItme(index);
            slot_vehicle = new Texture("slot_vehicle_2_selected.png");
        } else slot_vehicle = new Texture("slot_vehicle.png");
        background = new Image(slot_vehicle);
        background.setBounds(getX(), getY(), slot_vehicle.getWidth(), slot_vehicle.getHeight());
        addActor(background);

    }

    private void setUpIcon() {
        Image icon = new Image(car_texture);
        icon.setBounds(getX() + 39 - car_texture.getWidth() / 2, getY() + 7, car_texture.getWidth(), car_texture.getHeight());
        addActor(icon);
    }

    private void setUpIconSpeed() {
        Image iconSpeed = new Image(speed_text);
        iconSpeed.setBounds(getX() + 93, getY() + 40, 40, 10);
        addActor(iconSpeed);
    }

    private void setUpIconSpeedBar() {


        TextureRegion speedBar = new TextureRegion(speed_bar);
        speedBar.setRegion(0,0,car.getSpeed(),8);
        Image iconSpeedBar = new Image(speedBar);
        iconSpeedBar.setX(getX() + 93);
        iconSpeedBar.setY(getY() + 30);
        addActor(iconSpeedBar);
    }

    private void setUpDelimiterSpeedBar() {

        Image delimiter = new Image(delimiterTexture);
        delimiter.setBounds(getX() + 78, getY() + 30, delimiter.getWidth(), delimiter.getHeight());
        addActor(delimiter);
    }

    private void setUpIconWeightBar() {

//        Image iconWeightBar = new Image(weight_bar);
//        iconWeightBar.setOrigin(40, 8);
        TextureRegion weigthBar  = new TextureRegion(weight_bar);
        weigthBar.setRegion(0,0, car.getWeight(),8);
        weigthBar.split(10, weigthBar.getRegionHeight());
        Image  iconWeightBar = new Image(weigthBar);

        iconWeightBar.setX(getX() + 93);
        iconWeightBar.setY(getY() + 10);
        //iconWeightBar.setScaling(Scaling.fillY);
        //iconWeightBar.setBounds(getX() + 93, getY() + 10, 90, 8);
        addActor(iconWeightBar);
    }

    private void setUpIconWeight() {
        Image iconWeight = new Image(weight_text);
        iconWeight.setBounds(getX() + 93, getY() + 20, 40, 10);
        addActor(iconWeight);
    }

    private void setUpCarName(String imageId) {
        Texture carNameTexture = AssetsManager.getTextureRegion(imageId).getTexture();
        Image carName = new Image(carNameTexture);
        carName.setBounds(getX() + 93, getY() + 65, carNameTexture.getWidth(), carNameTexture.getHeight());
        addActor(carName);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void onUpdateGarageCarItem(boolean selected) {
        //removeActor(background);
//        background = new Image(new  Texture("slot_vehicle_2_selected.png"));
//        background.setBounds(getX(), getY(), slot_vehicle.getWidth(), slot_vehicle.getHeight());
        //addActor(background);
        updateGarageCarItem(selected);
        System.out.println("clicked");
//        if(selected) GameManager.setCurrentCarID(car.getID());
//        background.setDrawable(new SpriteDrawable(new Sprite(new Texture("slot_vehicle_2_selected.png"))));
        //updateGarageTable.updateTable();
    }

    public void updateGarageCarItem(boolean selected) {
        if (selected)
        {
            GameManager.setCurrentCarID(car.getID());
            background.setDrawable(new SpriteDrawable(new Sprite(new Texture("slot_vehicle_2_selected.png"))));
            updateGarageTable.updateTable();
            updateGarageTable.setSelectedItme(index);
        } else  background.setDrawable(new SpriteDrawable(new Sprite(new Texture("slot_vehicle.png"))));


    }


}
