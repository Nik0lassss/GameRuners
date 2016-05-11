package com.nicholaschirkevich.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.entity.GearShift;
import com.nicholaschirkevich.game.helper.XmlHelper;
import com.nicholaschirkevich.game.model.AchiveView;
import com.nicholaschirkevich.game.model.GearView;

import java.util.ArrayList;

/**
 * Created by Nikolas on 11.03.2016.
 */
public class GameManager {


    private static ArrayList<GearShift> gearShifts = new ArrayList<GearShift>();
    private static ArrayList<CarsType> carsTypes = new ArrayList<CarsType>();
    private static Car currentCar = new Car();
    public static boolean pauseGame = false;
    private static float contactPointX = 0, contactPointY = 0;
    //private static float boosterSpeed =0;
    private static float lastSpeed = 0;
    private static float achives = 0;

    public static float getBestAchives() {
        return bestAchives;
    }

    private static float bestAchives = 0;
    private static boolean isCollision = false;

    public static void setCurrentSpeed(float currentSpeed) {
        CurrentSpeed = currentSpeed;
    }

    public static void setcurrentSpeed(float currentspeed) {
        currentSpeed = currentspeed;
    }

    private static String currentCarID = "CC1023";
    private static Integer coinCounter;
    private static Preferences preferences;

    public static float getCurrentSpeed() {
        return CurrentSpeed;
    }

    private static float CurrentSpeed = 0;


    public static boolean isStopGeneratePasserCars() {
        return stopGeneratePasserCars;
    }

    public static void setStopGeneratePasserCars(boolean stopGeneratePasserCars) {
        GameManager.stopGeneratePasserCars = stopGeneratePasserCars;
    }

    private static boolean stopGeneratePasserCars = false;

    private static int gear = 0;
    private static GearShift gearShift;
    private static float toSpeed = 0;
    private static float currentSpeed = 0;
    private static float dtTime = 0;
    private static float dtTimeAhive = 0;
    private static boolean isCollisionWithCar = false;

    private static Label label;
    private static Label timeLabel;
    private static Label stageChild;
    private static Label worldChild;

    private static World worldGameManager;
    private static Stage stageGameManager;

    public static float getAllTime() {
        return allTime;
    }

    public static void setAllTime(float allTime) {
        GameManager.allTime = allTime;
    }

    private static float allTime = 0;

    public static float getCollisionSpeed() {
        return collisionSpeed;
    }

    public static void setCollisionSpeed(float collisionSpeed) {
        GameManager.collisionSpeed = collisionSpeed;
    }

    private static float collisionSpeed = 0;


    public static void loadData() {
        gearShifts = XmlHelper.getShifts();
        carsTypes = XmlHelper.getCars();
        preferences = Gdx.app.getPreferences(Constants.PREFERENCES_KEY);
        loadPreferences();
        gearShift = GameManager.getGearShifts().get(GameManager.getCurrentCar().getCurveType() - 1);
        System.out.println(preferences.getString(Constants.PREFERENCES_KEY));

    }

    public static void initial(World world, Stage stage) {
        worldGameManager = world;
        stageGameManager = stage;
        setUpLabetDebug();
        setUpTimeLabetDebug();
        setUpWorldChildLabetDebug();
        setUpStageChildLabetDebug();
    }

    public static void resetSpeed() {
        gear = 0;
        isCollision = false;
    }

    public static void resetTime() {
        allTime = 0;
        dtTime = 0;
        dtTimeAhive = 0;
        contactPointX = 0;
        contactPointY = 0;
    }

    public static void resetContactPoint() {
        contactPointX = 0;
        contactPointY = 0;
    }

    public void resetAchives() {
        achives = 0;
    }

    private static void loadPreferences() {
        String loadId = preferences.getString(Constants.PREFERENCES_KEY_CAR_ID);
        if (loadId.equals("")) {
            preferences.putString(Constants.PREFERENCES_KEY_CAR_ID, currentCarID);
            preferences.flush();
        } else currentCarID = loadId;

        coinCounter = preferences.getInteger(Constants.PREFERENCES_KEY_COIN_COUNT_ID, 0);
        bestAchives = preferences.getInteger(Constants.PREFERENCES_KEY_ACHIVES_COUNT_ID, 0);

    }

    public static ArrayList<GearShift> getGearShifts() {
        return gearShifts;
    }

    public static ArrayList<CarsType> getCarsTypes() {
        return carsTypes;
    }

    public static Car getCurrentCar() {
        for (int i = 0; i < carsTypes.size(); i++) {
            ArrayList<Car> carArrayList = carsTypes.get(i).getCars();
            for (int n = 0; n < carArrayList.size(); n++) {
                Car car = carArrayList.get(n);
                if (car.getID().equals(currentCarID)) return car;
            }
        }

        return null;
    }

    public static void setCurrentCarID(String id) {
        currentCarID = id;
        preferences.putString(Constants.PREFERENCES_KEY_CAR_ID, currentCarID);
        preferences.flush();
    }

    public static void setCountCoint(Integer countCoint) {
        coinCounter = countCoint;
        preferences.putInteger(Constants.PREFERENCES_KEY_COIN_COUNT_ID, coinCounter);
        preferences.flush();
    }

    public static void setBestAchives()
    {
        if(achives>bestAchives)
        {
            bestAchives =achives;
            preferences.putInteger(Constants.PREFERENCES_KEY_ACHIVES_COUNT_ID,(int) bestAchives);
            preferences.flush();
        }
    }

    public static void updateGear(float dt) {
//        System.out.println("gearShift.getSpeeds().get(gear) " + gearShift.getSpeeds().get(gear));
//        System.out.println("currentSpeed " + currentSpeed);
//        System.out.println("CurrentSpeed " + CurrentSpeed);
//        System.out.println("dtTime " + dtTime);
//        System.out.println("dtTimeAhive " + dtTimeAhive);
//        System.out.println("allTime " + allTime);
//        System.out.println("isCollision " + isCollision);
//        System.out.println("toSpeed " + toSpeed);
//        System.out.println("-----------------------------");

        dtTime += dt;
        dtTimeAhive += dt;
        allTime += dt;
        toSpeed = gearShift.getSpeeds().get(gear);
        // System.out.println("isCollision " + isCollision);
        if (gear == 0) currentSpeed = toSpeed;
        if (currentSpeed < toSpeed) currentSpeed = currentSpeed + 1f;
        if (isCollision) currentSpeed = collisionSpeed;
        GameManager.setCurrentSpeed(currentSpeed);
        label.setText(String.valueOf(currentSpeed));
        timeLabel.setText(String.valueOf(allTime));
        stageChild.setText("stageChild " + String.valueOf(stageGameManager.getActors().size));
        worldChild.setText("worldChild " + String.valueOf(worldGameManager.getBodyCount()));
        // System.out.println("Gear "+gear);
        if (dtTime > gearShift.getTimes().get(gear)) {

            if (gear + 1 < gearShift.getTimes().size()) {
                gear++;
                //stageGameManager.addActor(GearView.getView(gear));
            }

            GameManager.setCurrentSpeed(currentSpeed);
            dtTime = 0;
        }
        if (dtTimeAhive > gearShift.getTimes().get(gear) + 1) {

            if (gear + 1 < gearShift.getTimes().size()) {
//                Actor actor = AchiveView.getView(gear);
//                if (actor != null)
//                    stageGameManager.addActor(AchiveView.getView(gear));
            }

            GameManager.setCurrentSpeed(currentSpeed);
            dtTimeAhive = 0;
        }
    }

    public static void setUpLabetDebug() {
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        label = new Label("0", uiSkin);
        label.setBounds(GameRuners.WIDTH / 2 - 60, GameRuners.HEIGHT / 2 - 60, label.getWidth(), label.getHeight());
        stageGameManager.addActor(label);
    }

    public static void setUpTimeLabetDebug() {
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        timeLabel = new Label("0", uiSkin);
        timeLabel.setBounds(GameRuners.WIDTH / 2 - 60, GameRuners.HEIGHT / 2 - 80, label.getWidth(), label.getHeight());
        stageGameManager.addActor(timeLabel);
    }

    public static void setDefaultSpeed() {
        isCollision = false;
//        gear=0;
        currentSpeed = gearShift.getSpeeds().get(gear);
        CurrentSpeed = gearShift.getSpeeds().get(gear);
        toSpeed = gearShift.getSpeeds().get(gear);

    }

    public static void setUpWorldChildLabetDebug() {
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        worldChild = new Label("0", uiSkin);
        worldChild.setBounds(GameRuners.WIDTH / 2 - 100, GameRuners.HEIGHT / 2 - 100, label.getWidth(), label.getHeight());
        stageGameManager.addActor(worldChild);
    }

    public static void setUpStageChildLabetDebug() {
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        stageChild = new Label("0", uiSkin);
        stageChild.setBounds(GameRuners.WIDTH / 2 - 100, GameRuners.HEIGHT / 2 - 120, label.getWidth(), label.getHeight());
        stageGameManager.addActor(stageChild);
    }


    public static Integer getCoinCounter() {
        return coinCounter;
    }

    public static Integer addCoint() {
        coinCounter++;
        setCountCoint(coinCounter);
        return coinCounter;
    }


    public static float getContactPointX() {
        return contactPointX;
    }

    public static void setContactPointX(float contactPointX) {
        if (GameManager.isCollision() && GameManager.getContactPointX() == 0)
            GameManager.contactPointX = contactPointX;
    }

    public static float getContactPointY() {
        return contactPointY;
    }

    public static void setContactPointY(float contactPointY) {
        if (GameManager.isCollision() && GameManager.getContactPointY() == 0)
            GameManager.contactPointY = contactPointY;
    }


    public static void setBoosterSpeed() {
        setLastSpeed(getCurrentSpeed());
        currentSpeed = getGearShifts().get(getCurrentCar().getCurveType() - 1).getSpeeds().get(Constants.MAX_GEAR);
    }

    public static void setLow30Speed() {
        setLastSpeed(getCurrentSpeed());

        if (gear != 1) currentSpeed -= currentSpeed * 0.03f;
    }

    public static float getLastSpeed() {
        return lastSpeed;
    }

    public static void setLastSpeed() {
        currentSpeed = lastSpeed;
    }

    public static void setLastSpeed(float lastSpeed) {
        GameManager.lastSpeed = lastSpeed;
    }

    public static boolean isCollisionWithCar() {
        return isCollisionWithCar;
    }

    public static void setIsCollisionWithCar(boolean isCollisionWithCar) {
        GameManager.isCollisionWithCar = isCollisionWithCar;
    }

    public static int getGear() {
        return gear;
    }


    public static boolean isCollision() {
        return isCollision;
    }

    public static void setIsCollision(boolean isCollision) {
        GameManager.isCollision = isCollision;
        System.out.println("GameManager.isCollision " + GameManager.isCollision);
    }

    public static float getAchives() {
        return achives;
    }

    public static void setAchives(float achives) {
        GameManager.achives = achives;
    }
}
