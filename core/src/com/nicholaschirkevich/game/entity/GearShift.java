package com.nicholaschirkevich.game.entity;

import java.util.ArrayList;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class GearShift {
    private ArrayList<Integer> speeds;
    private ArrayList<Integer> times;

    public GearShift() {
        speeds = new ArrayList<Integer>();
        times = new ArrayList<Integer>();
    }


    public ArrayList<Integer> getSpeeds() {
        return speeds;
    }

    public ArrayList<Integer> getTimes() {
        return times;
    }

    public void setSpeeds(ArrayList<Integer> speeds) {
        this.speeds = speeds;
    }

    public void setTimes(ArrayList<Integer> times) {
        this.times = times;
    }
}
