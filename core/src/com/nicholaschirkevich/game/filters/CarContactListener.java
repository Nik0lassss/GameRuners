package com.nicholaschirkevich.game.filters;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.nicholaschirkevich.game.interfaces.ListenerAddLadle;
import com.nicholaschirkevich.game.model.boosters.LadleOnCar;
import com.nicholaschirkevich.game.userdata.MyCarDataType;
import com.nicholaschirkevich.game.userdata.PasserCarDataType;
import com.nicholaschirkevich.game.util.BodyUtils;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;


/**
 * Created by Nikolas on 19.03.2016.
 */
public class CarContactListener implements ContactListener {
    private ListenerAddLadle listenerAddLadleInterface;

    public CarContactListener(ListenerAddLadle listenerAddLadle) {
        this.listenerAddLadleInterface = listenerAddLadle;
    }

    @Override
    public void beginContact(Contact contact) {

        WorldManifold worldManifold = null;
        worldManifold = contact.getWorldManifold();
        PasserCarDataType passerCarDataType = null;
        MyCarDataType myCarDataType = null;
        Body passerCarBody = null;
        Body myCarBody = null;


        if (contact.getFixtureA().getFilterData().categoryBits == LadleOnCar.LADLE_MASK && contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY ||
                (contact.getFixtureB().getFilterData().categoryBits == LadleOnCar.LADLE_MASK && contact.getFixtureA().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY)) {

        }

        if (contact.getFixtureA().getFilterData().categoryBits == Constants.MY_CAR_FILTER_ENTITY && contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY ||
                (contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY && contact.getFixtureA().getFilterData().categoryBits == Constants.MY_CAR_FILTER_ENTITY))

            passerCarDataType = new PasserCarDataType();
        myCarDataType = new MyCarDataType();
        if (BodyUtils.bodyIsMyCar(contact.getFixtureA().getBody())) {
            myCarDataType = (MyCarDataType) contact.getFixtureA().getBody().getUserData();
            myCarBody = contact.getFixtureA().getBody();
        } else if (BodyUtils.bodyIsMyCar(contact.getFixtureB().getBody())) {
            myCarDataType = (MyCarDataType) contact.getFixtureB().getBody().getUserData();
            myCarBody = contact.getFixtureB().getBody();
        }
        if (BodyUtils.bodyIsPasserCar(contact.getFixtureA().getBody())) {
            passerCarDataType = (PasserCarDataType) contact.getFixtureA().getBody().getUserData();
            passerCarBody = contact.getFixtureA().getBody();
        } else if (BodyUtils.bodyIsPasserCar(contact.getFixtureB().getBody())) {
            passerCarDataType = (PasserCarDataType) contact.getFixtureB().getBody().getUserData();
            passerCarBody = contact.getFixtureB().getBody();
        }
        if (passerCarDataType != null) {

            passerCarDataType.setX(worldManifold.getPoints()[0].x);
            passerCarDataType.setY(worldManifold.getPoints()[0].y);
        }

        if(passerCarDataType != null && passerCarBody != null && myCarBody != null && passerCarBody.getPosition().y==0 && myCarBody.getPosition().y==0)
        {
            passerCarDataType.setBefore(true);
        }
       else  if (passerCarDataType != null && passerCarBody != null && myCarBody != null && passerCarBody.getPosition().y -70< myCarBody.getPosition().y)
        {
            passerCarDataType.setBefore(false);
        }
        else if(passerCarDataType != null && passerCarBody != null && myCarBody != null && passerCarBody.getPosition().y > myCarBody.getPosition().y){

            passerCarDataType.setBefore(true);
        }

            if (passerCarBody != null && myCarBody != null && passerCarBody.getPosition().y < myCarBody.getPosition().y && !myCarDataType.isFly()) {
                GameManager.setContactPointX(worldManifold.getPoints()[0].x);
                GameManager.setContactPointY(worldManifold.getPoints()[0].y - 10);

            } else if(!myCarDataType.isFly() && passerCarBody != null && myCarBody != null){
                GameManager.setContactPointX(worldManifold.getPoints()[0].x);
                GameManager.setContactPointY(worldManifold.getPoints()[0].y);

            }
        GameManager.setIsCollisionWithCar(true);

    }

    @Override
    public void endContact(Contact contact) {
        PasserCarDataType passerCarDataType = null;
        MyCarDataType myCarDataType = null;
        if (contact.getFixtureA().getFilterData().categoryBits == Constants.MY_CAR_FILTER_ENTITY && contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY ||
                (contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY && contact.getFixtureA().getFilterData().categoryBits == Constants.MY_CAR_FILTER_ENTITY)) {

            passerCarDataType = new PasserCarDataType();
            myCarDataType = new MyCarDataType();
            if (BodyUtils.bodyIsMyCar(contact.getFixtureA().getBody())) {
                myCarDataType = (MyCarDataType) contact.getFixtureA().getBody().getUserData();
            } else if (BodyUtils.bodyIsMyCar(contact.getFixtureB().getBody())) {
                myCarDataType = (MyCarDataType) contact.getFixtureB().getBody().getUserData();
            }
            if (BodyUtils.bodyIsPasserCar(contact.getFixtureA().getBody())) {
                passerCarDataType = (PasserCarDataType) contact.getFixtureA().getBody().getUserData();
            } else if (BodyUtils.bodyIsPasserCar(contact.getFixtureB().getBody())) {
                passerCarDataType = (PasserCarDataType) contact.getFixtureB().getBody().getUserData();
            }
            if (passerCarDataType != null) {
                passerCarDataType.setIsGodMode(false);

            }
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        PasserCarDataType passerCarDataType = null;
        MyCarDataType myCarDataType = null;
        if (contact.getFixtureA().getFilterData().categoryBits == Constants.MY_CAR_FILTER_ENTITY && contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY ||
                (contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY && contact.getFixtureA().getFilterData().categoryBits == Constants.MY_CAR_FILTER_ENTITY)) {
            //System.out.println("postSolve");
            passerCarDataType = new PasserCarDataType();
            myCarDataType = new MyCarDataType();
            if (BodyUtils.bodyIsMyCar(contact.getFixtureA().getBody())) {
                myCarDataType = (MyCarDataType) contact.getFixtureA().getBody().getUserData();
            } else if (BodyUtils.bodyIsMyCar(contact.getFixtureB().getBody())) {
                myCarDataType = (MyCarDataType) contact.getFixtureB().getBody().getUserData();
            }
            if (BodyUtils.bodyIsPasserCar(contact.getFixtureA().getBody())) {
                passerCarDataType = (PasserCarDataType) contact.getFixtureA().getBody().getUserData();
            } else if (BodyUtils.bodyIsPasserCar(contact.getFixtureB().getBody())) {
                passerCarDataType = (PasserCarDataType) contact.getFixtureB().getBody().getUserData();
            }
            if (passerCarDataType != null) {

            }
        }
    }
}
