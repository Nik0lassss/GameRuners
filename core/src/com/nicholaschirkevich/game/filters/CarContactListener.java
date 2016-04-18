package com.nicholaschirkevich.game.filters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.nicholaschirkevich.game.enums.CollisionPasserCarType;
import com.nicholaschirkevich.game.interfaces.ListenerAddLadle;
import com.nicholaschirkevich.game.model.LadleOnCar;
import com.nicholaschirkevich.game.model.MyCar;
import com.nicholaschirkevich.game.model.PasserCar;
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
        //print the contact point and the normal

        if (contact.getFixtureA().getFilterData().categoryBits == LadleOnCar.LADLE_MASK && contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY ||
                (contact.getFixtureB().getFilterData().categoryBits == LadleOnCar.LADLE_MASK && contact.getFixtureA().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY)) {
//            PasserCarDataType passerCarData = new PasserCarDataType();
//            MyCarDataType myCarData = new MyCarDataType();
//            if (BodyUtils.bodyIsMyCar(contact.getFixtureA().getBody())) {
//                myCarDataType = (MyCarDataType) contact.getFixtureA().getBody().getUserData();
//            } else if (BodyUtils.bodyIsMyCar(contact.getFixtureB().getBody())) {
//                myCarDataType = (MyCarDataType) contact.getFixtureB().getBody().getUserData();
//            }
//            if (BodyUtils.bodyIsPasserCar(contact.getFixtureA().getBody())) {
//                passerCarDataType = (PasserCarDataType) contact.getFixtureA().getBody().getUserData();
//            } else if (BodyUtils.bodyIsPasserCar(contact.getFixtureB().getBody())) {
//                passerCarDataType = (PasserCarDataType) contact.getFixtureB().getBody().getUserData();
//            }
//            passerCarDataType.setIsLadleCollision(true);
//            listenerAddLadleInterface.removeLadle();
            //System.out.println(" LadleOnCar.LADLE_MASK ");
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
            // passerCarDataType.setIsGodMode(true);
            passerCarDataType.setX(worldManifold.getPoints()[0].x);
            passerCarDataType.setY(worldManifold.getPoints()[0].y);
        }
//        Rectangle intersaction = new Rectangle();
//        if (passerCarDataType != null) {
//            Intersector.intersectRectangles(passerCarDataType.getBounds(), myCarDataType.getBounds(), intersaction);
//            if (intersaction.x > myCarDataType.getBounds().x) {
//                System.out.println("Intersects with right side");
//            } else if (intersaction.x + intersaction.width < myCarDataType.getBounds().x + myCarDataType.getBounds().width) {
//                System.out.println("Intersects with left side    ");
//            } else
//                //Intersects with right side
//                if (intersaction.y > myCarDataType.getBounds().y) {
//                    System.out.println("Intersects with top side   ");
//                } else
//                    //Intersects with top side
//
//                    //Intersects with left side
//                    if (intersaction.y + intersaction.height < myCarDataType.getBounds().y + myCarDataType.getBounds().height) {
//                        System.out.println("Intersects with with bottom side     ");
//                    }
//        }
        //Intersects with bottom side
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

            } else if(!myCarDataType.isFly()){
                GameManager.setContactPointX(worldManifold.getPoints()[0].x);
                GameManager.setContactPointY(worldManifold.getPoints()[0].y);

            }
        GameManager.setIsCollisionWithCar(true);

//        System.out.println("worldManifold.getPoints()[0].x " + worldManifold.getPoints()[0].x);
//        System.out.println(" worldManifold.getPoints()[0].y" + worldManifold.getPoints()[0].y);
    }

    @Override
    public void endContact(Contact contact) {
        PasserCarDataType passerCarDataType = null;
        MyCarDataType myCarDataType = null;
        if (contact.getFixtureA().getFilterData().categoryBits == Constants.MY_CAR_FILTER_ENTITY && contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY ||
                (contact.getFixtureB().getFilterData().categoryBits == Constants.PASSER_CAR_FILTER_ENTITY && contact.getFixtureA().getFilterData().categoryBits == Constants.MY_CAR_FILTER_ENTITY)) {
            System.out.println("endContact car");
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
        // System.out.println("endContact");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        //System.out.println("preSolve");
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
                //passerCarDataType.setIsGodMode(false);
            }
        }
    }
}
