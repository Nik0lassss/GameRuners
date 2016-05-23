package com.nicholaschirkevich.game.view_adapters;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.interfaces.UpdateGarageTable;
import com.nicholaschirkevich.game.menu.items.CarGarageItem;
import com.nicholaschirkevich.game.util.AssetsManager;

import java.util.ArrayList;

/**
 * Created by Nikolas on 12.03.2016.
 */
public class GarageAdapter implements UpdateGarageTable {
    private Table table;
    private ArrayList<CarsType> carsTypes;
    private int selectedItmeIndex;
    private ArrayList<CarGarageItem> carGarageItems = new ArrayList<CarGarageItem>();


    public GarageAdapter(Table table, ArrayList<CarsType> carsTypes) {
        this.table = table;
        this.carsTypes = carsTypes;

    }

    public void loadTableData() {
        int index = 0;
        for (int i = 0; i < carsTypes.size(); i++) {
            CarsType carsType = carsTypes.get(i);
            for (int z = 0; z < carsType.getCars().size(); z++) {
                Car car = carsType.getCars().get(z);
                CarGarageItem carGarageItem = new CarGarageItem(car,index, this, AssetsManager.getUiSkin());
                carGarageItems.add(carGarageItem);
                // CarGarageItem item = new CarGarageItem(car);
                //item.addListener(new CarGarageItemClickListener(car.getID(),this));
                table.add(carGarageItem).row();
                index++;
            }
        }
    }

    @Override
    public void updateTable() {
        carGarageItems.get(selectedItmeIndex).updateGarageCarItem(false);
    }

    @Override
    public void setSelectedItme(int index) {
        this.selectedItmeIndex = index;

    }
}
