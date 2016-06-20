package com.nicholaschirkevich.game.menu.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.nicholaschirkevich.game.entity.CoinShop;
import com.nicholaschirkevich.game.interfaces.UpdateGarageCarItem;
import com.nicholaschirkevich.game.interfaces.UpdateGarageTable;
import com.nicholaschirkevich.game.listeners.CarGarageItemClickListener;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class CoinShopItem extends Group implements UpdateGarageCarItem {

    private Texture slot_vehicle, slot_vehicle_left, buy_real_empty_texture;
    private Texture coins;
    private Label coin_count_label;
    private Texture delimiterTexture;
    private Image background, background_left, buy_real_empty_image, discount_image;
    private CoinShop coinShop;
    private UpdateGarageTable updateGarageTable;
    private Integer index;
    private int addHeight =0;
    private float scaleX=0.25f, scaleY = 0.4f;

    public CoinShopItem(CoinShop coinShop, Integer index, UpdateGarageTable updateGarageTable) {

        this.updateGarageTable = updateGarageTable;
        this.index = index;

        setUpBackgroung();
        coins = AssetsManager.getTextureRegion(coinShop.getImage_source()).getTexture();

        delimiterTexture = AssetsManager.getTextureRegion(Constants.DELIMITER_ID).getTexture();

        this.coinShop = coinShop;
        setUpCoinsImage(coinShop.getImage_source());
        if (coinShop.getDiscount() != 0) setUpDiscount(coinShop.getDiscount());

        setUpCoinCountLabel();
        setUpBuyRealEmpty();


        setUpDelimiterSpeedBar();
        addListener(new CarGarageItemClickListener(this));


        setBounds(0, 0, slot_vehicle.getWidth(), slot_vehicle.getHeight()+addHeight);
    }


    private void setUpDiscount(int discount) {
        switch (discount) {
            case 30:
                discount_image = new Image(AssetsManager.getTextureRegion(Constants.DISCOUNT_30_ID));
                break;
            case 50:
                discount_image = new Image(AssetsManager.getTextureRegion(Constants.DISCOUNT_50_ID));
                break;
            case 100:
                discount_image = new Image(AssetsManager.getTextureRegion(Constants.DISCOUNT_100_ID));
                break;
        }

        discount_image.setScale(0.8f, 0.8f);
        discount_image.setBounds(getX() + 38, getY() + 67, discount_image.getPrefWidth(), discount_image.getPrefHeight());

        addActor(discount_image);
    }

    private void setUpCoinCountLabel() {
        coin_count_label = new Label(String.valueOf(coinShop.getCoins()), AssetsManager.getUiSkin());
        coin_count_label.setBounds(getX() + 40 - coin_count_label.getPrefWidth() / 4, getY(), coin_count_label.getPrefWidth(), coin_count_label.getPrefHeight());
        coin_count_label.setFontScale(0.6f, 0.6f);
        addActor(coin_count_label);
    }

    private void setUpBackgroung() {

        updateGarageTable.setSelectedItme(index);
        slot_vehicle =AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_SELECTED_ID).getTexture();
        slot_vehicle_left = AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_ID).getTexture();
        background = new Image(slot_vehicle);
        background.setBounds(getX(), getY(), 92, slot_vehicle.getHeight()+addHeight);
        background_left = new Image(slot_vehicle_left);
        background_left.setBounds(getX() + 92, getY(), slot_vehicle_left.getWidth() - 92, slot_vehicle_left.getHeight()+addHeight);
        addActor(background);
        addActor(background_left);

    }

    private void setUpBuyRealEmpty() {
        Label labelPriceCoins = new Label(String.valueOf(coinShop.getPrice()), AssetsManager.getUiSkin());
        labelPriceCoins.setBounds(getX() + 190, getY() + 55, labelPriceCoins.getPrefWidth(), labelPriceCoins.getPrefHeight());
        labelPriceCoins.setFontScale(0.6f, 0.6f);
        Label labelNameCoinPack = new Label(coinShop.getName(),  AssetsManager.getUiSkin());
        labelNameCoinPack.setAlignment(Align.bottomLeft);
        labelNameCoinPack.setBounds(getX() + 195 - labelNameCoinPack.getPrefWidth() / 4, getY()+45  - labelNameCoinPack.getHeight()/2, labelNameCoinPack.getPrefWidth(), labelNameCoinPack.getPrefHeight());
        labelNameCoinPack.setFontScale(0.5f, 0.5f);

        buy_real_empty_texture = AssetsManager.getTextureRegion(Constants.BUTTON_BUT_REAL_EMPTY_ID).getTexture();
        buy_real_empty_image = new Image(buy_real_empty_texture);
        buy_real_empty_image.setBounds(getX() + 160, getY() + 55, buy_real_empty_image.getPrefWidth()+10, buy_real_empty_image.getPrefHeight());
        addActor(buy_real_empty_image);
        addActor(labelPriceCoins);
        addActor(labelNameCoinPack);
    }


    private void setUpDelimiterSpeedBar() {

        Image delimiter = new Image(delimiterTexture);
        delimiter.setBounds(getX() + 87, getY(), delimiter.getWidth(), delimiter.getHeight());
        addActor(delimiter);
    }


    private void setUpCoinsImage(String imageId) {
        Texture carNameTexture = AssetsManager.getTextureRegion(imageId).getTexture();
        Image coinsImage = new Image(carNameTexture);
        switch (coinShop.getDiscount()) {
            case 30:
                scaleX = 0.3f;
                scaleY=0.43f;
                break;
            case 50:
                scaleX = 0.4f;
                scaleY=0.43f;
                break;
            case 100:
                scaleX = 0.45f;
                scaleY=0.4f;
                break;
        }

        coinsImage.setScale(scaleX, scaleY);
        coinsImage.setBounds(getX()+52- carNameTexture.getWidth() /4f, getY() + 15, carNameTexture.getHeight(), carNameTexture.getHeight());
//        carName.setBounds(getX() + 35 - carNameTexture.getWidth() / 6.2f, getY() + 15, carNameTexture.getHeight(), carNameTexture.getHeight());
        addActor(coinsImage);
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
        if (selected) {
            // GameManager.setCurrentCarID(car.getID());
            background.setDrawable(new SpriteDrawable(new Sprite(AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_SELECTED_ID))));
            updateGarageTable.updateTable();
            updateGarageTable.setSelectedItme(index);
        } else
            background.setDrawable(new SpriteDrawable(new Sprite(AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_ID))));


    }


}
