package com.nicholaschirkevich.game.view_adapters;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.interfaces.UpdateGarageTable;
import com.nicholaschirkevich.game.menu.items.CarGarageItem;
import com.nicholaschirkevich.game.menu.items.CoinShopItem;

import java.util.ArrayList;

/**
 * Created by Nikolas on 12.03.2016.
 */
public class CoinShopAdapter implements UpdateGarageTable {
    private Table table;
    private ArrayList<CoinShopItem> carsTypes;
    private int selectedItmeIndex;
    private ArrayList<CarGarageItem> coinShopItems = new ArrayList<CarGarageItem>();

    public CoinShopAdapter(Table table, ArrayList<CoinShopItem> carsTypes) {
        this.table = table;
        this.carsTypes = carsTypes;
    }


    public void loadTableData()
    {

    }

    @Override
    public void updateTable() {
        coinShopItems.get(selectedItmeIndex).updateGarageCarItem(false);
    }

    @Override
    public void setSelectedItme(int index) {
        this.selectedItmeIndex = index;

    }
}
