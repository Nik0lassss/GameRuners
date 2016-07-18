package com.nicholaschirkevich.game.view_adapters;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.interfaces.UpdateCoinCountInterface;
import com.nicholaschirkevich.game.interfaces.UpdateGarageTable;
import com.nicholaschirkevich.game.menu.items.CarGarageItem;
import com.nicholaschirkevich.game.util.AssetsManager;

import java.util.ArrayList;

/**
 * Created by Nikolas on 12.03.2016.
 */
public class GarageAdapter implements UpdateGarageTable {
    private Table table;
    private ArrayList<Car> cars;
    private int selectedItmeIndex;
    private ArrayList<CarGarageItem> carGarageItems = new ArrayList<CarGarageItem>();
    private ActionResolver actionResolver;
    private UpdateCoinCountInterface updateCoinCountInterface;

    public GarageAdapter(Table table, ArrayList<Car> cars, ActionResolver actionResolver,UpdateCoinCountInterface updateCoinCountInterface) {
        this.actionResolver = actionResolver;
        this.table = table;
        this.cars = cars;
        this.updateCoinCountInterface = updateCoinCountInterface;
    }


    public void loadTableData() {
        int index = 0;
            for (int z = 0; z < cars.size(); z++) {
                Car car = cars.get(z);
                CarGarageItem carGarageItem = new CarGarageItem(car, index, this, AssetsManager.getUiSkin(), actionResolver,updateCoinCountInterface);
                carGarageItems.add(carGarageItem);
                // CarGarageItem item = new CarGarageItem(car);
                //item.addListener(new CarGarageItemClickListener(car.getID(),this));
                table.add(carGarageItem).row();
                index++;
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
