package com.nicholaschirkevich.game.util;

import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.enums.BushType;
import com.nicholaschirkevich.game.enums.OtherCarType;
import com.nicholaschirkevich.game.enums.SideObjectType;
import com.nicholaschirkevich.game.model.side_objects.SideObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javafx.geometry.Side;

/**
 * Created by Nikolas on 02.03.2016.
 */
public class RandomUtil {
    public static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }

    private static ArrayList<Integer> lastValue = new ArrayList<Integer>();
    private static Boolean lastValueBoolean ;

    public static boolean getNoRandomBoolean() {
        if(lastValueBoolean==null){
            lastValueBoolean =new Random().nextBoolean();
        return lastValueBoolean;
        }
        else {
            return !lastValueBoolean;
        }
    }

    public static void resetLasValue() {
        lastValue = new ArrayList<Integer>();
        lastValueBoolean = null;
    }

    public static BushType getRandomBushType() {
        RandomEnum<BushType> randomEnum = new RandomEnum<BushType>(BushType.class);
        return randomEnum.random();
    }

    public static SideObjectType getRandomSideObject()
    {
        RandomEnum<SideObjectType> randomEnum = new RandomEnum<SideObjectType>(SideObjectType.class);
        return randomEnum.random();
    }

    public static OtherCarType getRandomOtherCarType() {
        RandomEnum<OtherCarType> randomEnum = new RandomEnum<OtherCarType>(OtherCarType.class);
        return randomEnum.random();
    }


    public static int getRandBocksCount() {
        Random rand = new Random();
        return rand.nextInt((5 - 3) + 1) + 3;
    }

    ;

    public static int getRand(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    ;

    public static ArrayList<Car> generateRandPossabilityCarHashMap(ArrayList<CarsType> cars, List<String> myCarIdList) {
        ArrayList<Car> carsRandromPossability = new ArrayList<Car>();
        for (CarsType carsType : cars) {
            for (Car car : carsType.getCars()) {
                if (!myCarIdList.contains(car.getID()) && car.getIsForRealMoney())
                    for (int i = 0; i < car.getPossability()*10; i++)
                        carsRandromPossability.add(car);
            }

        }
        return carsRandromPossability;

    }

    public static int getNoRand(int min, int max) {
        Random rand = new Random();
        Integer val = rand.nextInt((max - min) + 1) + min;
        if (!lastValue.contains(val)) {
            lastValue.add(val);
            return val;
        } else {
            while (lastValue.contains(val)) {
                val = rand.nextInt((max - min) + 1) + min;
            }
            return val;
        }
    }

    ;

    /**
     * @param <E>
     * @see [Stack Overflow](http://stackoverflow.com/a/1973018)
     */

    private static class RandomEnum<E extends Enum> {

        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }
}
