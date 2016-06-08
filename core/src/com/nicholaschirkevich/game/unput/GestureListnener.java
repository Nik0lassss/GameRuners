package com.nicholaschirkevich.game.unput;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.interfaces.CarTurnInterface;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 07.06.2016.
 */
public class GestureListnener implements GestureDetector.GestureListener {
    private CarTurnInterface carTurnInterface;

    public GestureListnener(CarTurnInterface carTurnInterface) {
        this.carTurnInterface = carTurnInterface;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if (GameManager.isTouchControl()) {
            carTurnInterface.turn();
        }
        System.out.println("Touch");
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (!GameManager.isTouchControl()) {
            if (Math.abs(velocityX) > 60) {
//                System.out.println("velocityX: " + velocityX);
//                System.out.println("velocityY: " + velocityY);
                carTurnInterface.turn();
            }
        }
//
        System.out.println("velocityX: " + velocityX);
        System.out.println("velocityY: " + velocityY);
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

}
