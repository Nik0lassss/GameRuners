package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nikolas on 12.02.2016.
 */
public class Road {
    //private Texture  roadsideLeft1, roadsideLeft2, roadsideRight1, roadsideRight2;
    // private TextureRegion road1, road2;
    private Animation roarAnimation;
    private Vector2 posRoad1, posRoad2, posRoadSideLeft1, posRoadSideLeft2, posRoadSideRight1, posRoadSideRight2;
    private Random rand;
    private Rectangle boundsRoad1, boundsRoad2, boundsRoadSideLeft1, boundsRoadSideLeft2, boundsRoadSideRight1, boundsRoadSideRight2, boundStartLine;
    private static int posX = -15;
    private Timer timer;
    private boolean isWorked = false;

    private StartLine startLine;
   // private RoadHole roadHole;

    public StartLine getStartLine() {
        return startLine;
    }

    public Road() {
        roarAnimation = AssetsManager.getAnimation(Constants.ROAD_STATIC_ASSETS_ID);
//        road1 = AssetsManager.getTextureRegion(Constants.ROAD_ASSETS_ID);
//        road2 = AssetsManager.getTextureRegion(Constants.ROAD_ASSETS_ID);


        startLine = new StartLine(67, 350);
        //roadHole = new RoadHole(Constants.ROAD_HOLE_X, Constants.ROAD_HOLE_Y);
//        roadsideLeft1 = new Texture("roadside_left.png");
//        roadsideRight1 = new Texture("roadside_right.png");
//        roadsideLeft2 = new Texture("roadside_left.png");
//        roadsideRight2 = new Texture("roadside_right.png");
        posRoad1 = new Vector2(posX, -300);
        posRoad2 = new Vector2(posX, roarAnimation.getKeyFrames()[0].getRegionHeight() - 300);


//        posRoadSideLeft1 = new Vector2(-100, -100);
//        posRoadSideRight1 = new Vector2(posRoad1.x + road1.getRegionWidth(), -100);
//        posRoadSideLeft2 = new Vector2(-100, roadsideLeft1.getHeight() - 100);
//        posRoadSideRight2 = new Vector2(posRoad1.x + road1.getRegionWidth(), roadsideRight1.getHeight() - 100);


        boundsRoad1 = new Rectangle(posRoad1.x, posRoad1.y, roarAnimation.getKeyFrames()[0].getRegionWidth(), roarAnimation.getKeyFrames()[0].getRegionHeight());
        boundsRoad2 = new Rectangle(posRoad2.x, posRoad2.y, roarAnimation.getKeyFrames()[0].getRegionWidth(), roarAnimation.getKeyFrames()[0].getRegionHeight());

//        boundsRoadSideLeft1 = new Rectangle(posRoadSideLeft1.x, posRoadSideLeft1.y, roadsideLeft1.getWidth(), roadsideLeft1.getHeight());
//        boundsRoadSideRight1 = new Rectangle(posRoadSideRight1.x, posRoadSideRight1.y, roadsideRight1.getWidth(), roadsideRight1.getHeight());
//        boundsRoadSideLeft2 = new Rectangle(posRoadSideLeft2.x, posRoadSideLeft2.y, roadsideLeft2.getWidth(), roadsideLeft2.getHeight());
//        boundsRoadSideRight2 = new Rectangle(posRoadSideRight2.x, posRoadSideRight2.y, roadsideRight2.getWidth(), roadsideRight2.getHeight());

    }


    public void setIsWorked(boolean isWorked) {
        this.isWorked = isWorked;
    }

    public boolean isWorked() {
        return isWorked;
    }

//    public TrafficLight getTrafficLight() {
//        return trafficLight;
//    }

//    public void setTrafficLight(TrafficLight trafficLight) {
//        this.trafficLight = trafficLight;
//    }

//    public RoadHole getRoadHole() {
//        return roadHole;
//    }

    class CrunchifyReminder extends TimerTask {


        public void run() {
            isWorked = true;
            System.out.println("isWorked " + isWorked);
            timer.cancel();
        }
    }

    public void update(Camera camera, float dt) {


        if (camera.position.y - (camera.viewportHeight / 2) > posRoad1.y + roarAnimation.getKeyFrames()[0].getRegionHeight()) {
            posRoad1.add(0, roarAnimation.getKeyFrames()[0].getRegionHeight() * 2);


        }
        if (camera.position.y - (camera.viewportHeight / 2) > posRoad2.y + roarAnimation.getKeyFrames()[0].getRegionHeight()) {
            posRoad2.add(0, roarAnimation.getKeyFrames()[0].getRegionHeight() * 2);

        }

//        if (camera.position.y - (camera.viewportHeight / 2) > posRoadSideLeft1.y + roadsideLeft1.getHeight()) {
//
//            posRoadSideLeft1.add(0, roadsideLeft1.getHeight() * 2);
//        }
//        if (camera.position.y - (camera.viewportHeight / 2) > posRoadSideLeft2.y + roadsideLeft2.getHeight()) {
//
//            posRoadSideLeft2.add(0, roadsideLeft2.getHeight() * 2);
//        }
//        if (camera.position.y - (camera.viewportHeight / 2) > posRoadSideRight1.y + roadsideRight1.getHeight()) {
//
//            posRoadSideRight1.add(0, roadsideRight1.getHeight() * 2);
//        }
//        if (camera.position.y - (camera.viewportHeight / 2) > posRoadSideRight2.y + roadsideRight2.getHeight()) {
//
//            posRoadSideRight2.add(0, roadsideRight2.getHeight() * 2);
//        }

        posRoad1.set(-15, posRoad1.y + (-GameManager.getCurrentSpeed() ) * dt);
        posRoad2.set(-15, posRoad2.y + (-GameManager.getCurrentSpeed()) * dt);


        startLine.getPosition().set(67, startLine.getPosition().y + (-GameManager.getCurrentSpeed()) * dt);
        //roadHole.getPosition().set(Constants.ROAD_HOLE_X, roadHole.getPosition().y + (-GameManager.getCurrentSpeed()) * dt);

    }


    public TextureRegion getRoad1() {
        return roarAnimation.getKeyFrames()[0];
    }

    public TextureRegion getRoad2() {
        return roarAnimation.getKeyFrames()[0];
    }

    public Vector2 getPosRoad1() {
        return posRoad1;
    }

    public Vector2 getPosRoad2() {
        return posRoad2;
    }

    public Rectangle getBoundsRoad1() {
        return boundsRoad1;
    }

    public Rectangle getBoundsRoad2() {
        return boundsRoad2;
    }

//    public Texture getRoadsideLeft1() {
//        return roadsideLeft1;
//    }
//
//    public Texture getRoadsideLeft2() {
//        return roadsideLeft2;
//    }
//
//    public Texture getRoadsideRight1() {
//        return roadsideRight1;
//    }
//
//    public Texture getRoadsideRight2() {
//        return roadsideRight2;
//    }

    public Vector2 getPosRoadSideLeft1() {
        return posRoadSideLeft1;
    }

    public Vector2 getPosRoadSideLeft2() {
        return posRoadSideLeft2;
    }

    public Vector2 getPosRoadSideRight1() {
        return posRoadSideRight1;
    }

    public Vector2 getPosRoadSideRight2() {
        return posRoadSideRight2;
    }

    public Rectangle getBoundsRoadSideLeft1() {
        return boundsRoadSideLeft1;
    }

    public Rectangle getBoundsRoadSideRight1() {
        return boundsRoadSideRight1;
    }

    public Rectangle getBoundsRoadSideLeft2() {
        return boundsRoadSideLeft2;
    }

    public Rectangle getBoundsRoadSideRight2() {
        return boundsRoadSideRight2;
    }


    public Rectangle getBoundStartLine() {
        return boundStartLine;
    }

}
