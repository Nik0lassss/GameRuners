package com.nicholaschirkevich.game.filters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.nicholaschirkevich.game.interfaces.DirtListener;
import com.nicholaschirkevich.game.interfaces.ListenerAddBoost;
import com.nicholaschirkevich.game.interfaces.ListenerAddLadle;
import com.nicholaschirkevich.game.interfaces.OnSetCollisionCars;
import com.nicholaschirkevich.game.interfaces.PauseAfterCollision;
import com.nicholaschirkevich.game.interfaces.SetGodMode;
import com.nicholaschirkevich.game.interfaces.ZoomCarListener;
import com.nicholaschirkevich.game.model.Booster;
import com.nicholaschirkevich.game.model.Coin;
import com.nicholaschirkevich.game.model.Dirt;
import com.nicholaschirkevich.game.model.FlySpringboard;
import com.nicholaschirkevich.game.model.Ladle;
import com.nicholaschirkevich.game.model.LadleOnCar;
import com.nicholaschirkevich.game.model.MyCar;
import com.nicholaschirkevich.game.model.PasserCar;
import com.nicholaschirkevich.game.model.Skull;
import com.nicholaschirkevich.game.model.Springboard;
import com.nicholaschirkevich.game.model.ThronsOnCarRight;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.states.State;
import com.nicholaschirkevich.game.userdata.BoosterDataType;
import com.nicholaschirkevich.game.userdata.CoinDataType;
import com.nicholaschirkevich.game.userdata.LadleOnRoadDataType;
import com.nicholaschirkevich.game.userdata.MyCarDataType;
import com.nicholaschirkevich.game.userdata.PasserCarDataType;
import com.nicholaschirkevich.game.userdata.SkullOnRoadDataType;
import com.nicholaschirkevich.game.util.BodyUtils;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 14.02.2016.
 */
public class CarFilter implements ContactFilter {

    GameStateManager gsm;
    GameState gameState;
    PauseAfterCollision pauseAfterCollision;
    public static int i = 0;
    private ListenerAddBoost listenerAddBoostInterface;
    private ListenerAddLadle listenerAddLadleInterface;
    private ZoomCarListener zoomCarListenerInterface;
    private SetGodMode setGodModeInterface;
    private DirtListener dirtListenerInterface;
    private OnSetCollisionCars onSetCollisionCarsInterface;

    public CarFilter(GameStateManager gameStateManager, State state, PauseAfterCollision pauseAfterCollision, ListenerAddBoost listenerAddBoost, ListenerAddLadle listenerAddLadle, SetGodMode setGodMode, ZoomCarListener zoomCarListener, DirtListener dirtListener, OnSetCollisionCars onSetCollisionCars) {
        this.gsm = gameStateManager;
        this.gameState = (GameState) state;
        this.pauseAfterCollision = pauseAfterCollision;
        this.listenerAddBoostInterface = listenerAddBoost;
        this.listenerAddLadleInterface = listenerAddLadle;
        this.setGodModeInterface = setGodMode;
        this.zoomCarListenerInterface = zoomCarListener;
        this.dirtListenerInterface = dirtListener;
        this.onSetCollisionCarsInterface = onSetCollisionCars;
    }

    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

        Filter filterA = fixtureA.getFilterData();

        Filter filterB = fixtureB.getFilterData();

//        if ((filterA.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY && filterB.categoryBits == LadleOnCar.LADLE_MASK) ||
//                (filterB.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY && filterA.categoryBits == LadleOnCar.LADLE_MASK)) {
//
//
//            PasserCarDataType passerCarDataType = new PasserCarDataType();
//            if (BodyUtils.bodyIsPasserCar(fixtureA.getBody())) {
//                passerCarDataType = (PasserCarDataType) fixtureA.getBody().getUserData();
//            } else if (BodyUtils.bodyIsPasserCar(fixtureB.getBody())) {
//                passerCarDataType = (PasserCarDataType) fixtureB.getBody().getUserData();
//            }
//
//
//            passerCarDataType.setIsLadleCollision(true);
//            listenerAddLadleInterface.removeLadle();
//
//
//            System.out.println("Collision with passer car");
//            System.out.println("filterA.categoryBits" + filterA.categoryBits);
//            System.out.println("filterB.categoryBits" + filterB.categoryBits);
//
//            return false;
//        }

        if ((filterA.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY && filterB.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY) ||
                (filterB.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY && filterA.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY)) {


            PasserCarDataType passerCarDataType1 = new PasserCarDataType();
            PasserCarDataType passerCarDataType2 = new PasserCarDataType();

            passerCarDataType1 = (PasserCarDataType) fixtureA.getBody().getUserData();

            passerCarDataType2 = (PasserCarDataType) fixtureB.getBody().getUserData();

            passerCarDataType1.setIsCollisionWhithPasser(true);
            passerCarDataType2.setIsCollisionWhithPasser(true);


            return false;
        }

        if ((filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterB.categoryBits == Springboard.SPRING_BOARD_MASK) ||
                (filterB.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterA.categoryBits == Springboard.SPRING_BOARD_MASK)) {
            System.out.println("springboard collision");
            zoomCarListenerInterface.onZoomCar();


            return false;
        }
        if ((filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterB.categoryBits == FlySpringboard.SPRING_BOARD_MASK) ||
                (filterB.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterA.categoryBits == FlySpringboard.SPRING_BOARD_MASK)) {
            System.out.println("springboard collision");
            zoomCarListenerInterface.onZoomCar();


            return false;
        }

        if ((filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterB.categoryBits == Dirt.DIRT_MASK) ||
                (filterB.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterA.categoryBits == Dirt.DIRT_MASK)) {
            dirtListenerInterface.onDirt();

            return false;
        }

        if ((filterA.categoryBits == LadleOnCar.LADLE_MASK && filterB.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY) ||
                (filterB.categoryBits == LadleOnCar.LADLE_MASK && filterA.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY)) {
            PasserCarDataType passerCarDataType = new PasserCarDataType();
            if (BodyUtils.bodyIsPasserCar(fixtureA.getBody())) {
                passerCarDataType = (PasserCarDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsPasserCar(fixtureB.getBody())) {
                passerCarDataType = (PasserCarDataType) fixtureB.getBody().getUserData();
            }
            passerCarDataType.setIsLadleCollision(true);
            listenerAddLadleInterface.removeLadle();

        }

        if ((filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterB.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY) ||
                (filterB.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY && filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY)) {

            PasserCarDataType passerCarDataType = new PasserCarDataType();
            MyCarDataType myCarDataType = new MyCarDataType();
            if (BodyUtils.bodyIsMyCar(fixtureA.getBody())) {
                myCarDataType = (MyCarDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsMyCar(fixtureB.getBody())) {
                myCarDataType = (MyCarDataType) fixtureB.getBody().getUserData();
            }
            if (BodyUtils.bodyIsPasserCar(fixtureA.getBody())) {
                passerCarDataType = (PasserCarDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsPasserCar(fixtureB.getBody())) {
                passerCarDataType = (PasserCarDataType) fixtureB.getBody().getUserData();
            }
//
            if (myCarDataType.isHaveLadle()) {
                passerCarDataType.setIsLadleCollision(true);
                listenerAddLadleInterface.removeLadle();
            } else if (myCarDataType.isGodMode()) {
                passerCarDataType.setMyCarBounds(myCarDataType.getBounds());
                System.out.println("Collide isGodMode");
                passerCarDataType.setIsGodMode(true);
            } else if (myCarDataType.isFly()) {
                return false;
            } else {
                onSetCollisionCarsInterface.onCollision();
                passerCarDataType.setIsContact(true);
                // passerCarDataType.setIsBlow(true);
                //myCarDataType.setIsBlow(true);
                Gdx.input.vibrate(500);
                return true;
            }

//

            return true;
//
        }

        if ((filterA.categoryBits == ThronsOnCarRight.THORN && filterB.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY) ||
                (filterB.categoryBits == PasserCar.PASSER_CAR_FILTER_ENTITY && filterA.categoryBits == ThronsOnCarRight.THORN)) {

            PasserCarDataType passerCarDataType = new PasserCarDataType();
//
            if (BodyUtils.bodyIsPasserCar(fixtureA.getBody())) {
                passerCarDataType = (PasserCarDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsPasserCar(fixtureB.getBody())) {
                passerCarDataType = (PasserCarDataType) fixtureB.getBody().getUserData();
            }
            passerCarDataType.setIsCollisionThrons(true);
//
//
            System.out.println("Collide Thorns");

            return false;
        }

        // System.out.println("Constact filter");
        /*&& ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("p"))||
                (fixtureB.getUserData().equals("player") && fixtureA.getUserData().equals("p")))*/
        if ((filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterB.categoryBits == Coin.COIN_MASK) ||
                (filterB.categoryBits == Coin.COIN_MASK && filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY)) {
//            String stringA =(String) fixtureA.getBody().getUserData();
//            String stringB =(String)fixtureB.getBody().getUserData();
            CoinDataType coinDataType = new CoinDataType();
            if (BodyUtils.bodyIsCoin(fixtureA.getBody())) {
                coinDataType = (CoinDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsCoin(fixtureB.getBody())) {
                coinDataType = (CoinDataType) fixtureB.getBody().getUserData();
            }

            coinDataType.setIsRecievedByMycar(true);
            GameManager.addCoint();
            gameState.updateCoinCount(GameManager.getCoinCounter());
//            System.out.println(stringA);
//            System.out.println(stringB);
//            gameState.getMyCar().setIsBlow(true);
//            pauseAfterCollision.pauseAfterCollision();
//            gsm.set(new GameState(gsm, false, false));

            //Log.e("don't","+");
            return false;
        }
        if ((filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterB.categoryBits == Skull.SKULL_MASK) ||
                (filterB.categoryBits == Skull.SKULL_MASK && filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY)) {
//            String stringA =(String) fixtureA.getBody().getUserData();
//            String stringB =(String)fixtureB.getBody().getUserData();
            SkullOnRoadDataType skullDataType = new SkullOnRoadDataType();
            MyCarDataType myCarDataType = new MyCarDataType();
            if (BodyUtils.bodyIsSkull(fixtureA.getBody())) {
                skullDataType = (SkullOnRoadDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsSkull(fixtureB.getBody())) {
                skullDataType = (SkullOnRoadDataType) fixtureB.getBody().getUserData();
            }

            if (BodyUtils.bodyIsMyCar(fixtureA.getBody())) {
                myCarDataType = (MyCarDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsMyCar(fixtureB.getBody())) {
                myCarDataType = (MyCarDataType) fixtureB.getBody().getUserData();
            }
            skullDataType.setIsRecievedByMycar(true);
            myCarDataType.setIsGodMode(true);
            setGodModeInterface.setGodMode();
            System.out.println("listenerAddBoostInterface.onAddBoost() 222;");
            // System.out.println("listenerAddBoostInterface.onAddBoost();");
//            GameManager.addCoint();
//            gameState.updateCoinCount(GameManager.getCoinCounter());
//            System.out.println(stringA);
//            System.out.println(stringB);
//            gameState.getMyCar().setIsBlow(true);
//            pauseAfterCollision.pauseAfterCollision();
//            gsm.set(new GameState(gsm, false, false));

            //Log.e("don't","+");
            return false;
        }
        if ((filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterB.categoryBits == Booster.BUSTER_MASK) ||
                (filterB.categoryBits == Booster.BUSTER_MASK && filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY)) {
//            String stringA =(String) fixtureA.getBody().getUserData();
//            String stringB =(String)fixtureB.getBody().getUserData();
            BoosterDataType boosterDataType = new BoosterDataType();
            if (BodyUtils.bodyIsBooster(fixtureA.getBody())) {
                boosterDataType = (BoosterDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsBooster(fixtureB.getBody())) {
                boosterDataType = (BoosterDataType) fixtureB.getBody().getUserData();
            }
            System.out.println("listenerAddBoostInterface.onAddBoost();");
            boosterDataType.setIsRecievedByMycar(true);
            listenerAddBoostInterface.onAddBoost();
//            GameManager.addCoint();
//            gameState.updateCoinCount(GameManager.getCoinCounter());
//            System.out.println(stringA);
//            System.out.println(stringB);
//            gameState.getMyCar().setIsBlow(true);
//            pauseAfterCollision.pauseAfterCollision();
//            gsm.set(new GameState(gsm, false, false));

            //Log.e("don't","+");
            return false;
        }

        if ((filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY && filterB.categoryBits == Ladle.LADLE_MASK) ||
                (filterB.categoryBits == Ladle.LADLE_MASK && filterA.categoryBits == MyCar.MY_CAR_FILTER_ENTITY)) {
//            String stringA =(String) fixtureA.getBody().getUserData();
//            String stringB =(String)fixtureB.getBody().getUserData();
            LadleOnRoadDataType coinDataType = new LadleOnRoadDataType();
            MyCarDataType myCarDataType = new MyCarDataType();
            if (BodyUtils.bodyIsLadle(fixtureA.getBody())) {
                coinDataType = (LadleOnRoadDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsLadle(fixtureB.getBody())) {
                coinDataType = (LadleOnRoadDataType) fixtureB.getBody().getUserData();
            }

            if (BodyUtils.bodyIsMyCar(fixtureA.getBody())) {
                myCarDataType = (MyCarDataType) fixtureA.getBody().getUserData();
            } else if (BodyUtils.bodyIsMyCar(fixtureB.getBody())) {
                myCarDataType = (MyCarDataType) fixtureB.getBody().getUserData();
            }
            //System.out.println("listenerAddBoostInterface.onAddBoost();");
            coinDataType.setIsRecievedByMycar(true);
            System.out.println("Ladle collisiton");
            listenerAddLadleInterface.onAddLadle();
            myCarDataType.setIsHaveLadle(true);
//            GameManager.addCoint();
//            gameState.updateCoinCount(GameManager.getCoinCounter());
//            System.out.println(stringA);
//            System.out.println(stringB);
//            gameState.getMyCar().setIsBlow(true);
//            pauseAfterCollision.pauseAfterCollision();
//            gsm.set(new GameState(gsm, false, false));

            //Log.e("don't","+");
            return false;
        }


        return true;


    }
}
