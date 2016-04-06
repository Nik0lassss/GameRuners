package com.nicholaschirkevich.game.entity;

/**
 * Created by Nikolas on 11.03.2016.
 */
public class BrakeLines {
    Integer leftLineStart;
    Integer rightLineStart;
    Integer weightOfLine;

    public BrakeLines() {
    }

    public Integer getLeftLineStart() {
        return leftLineStart;
    }

    public void setLeftLineStart(Integer leftLineStart) {
        this.leftLineStart = leftLineStart;
    }

    public void setLeftLineStart(String leftLineStart) {
        this.leftLineStart = Integer.valueOf(leftLineStart);
    }

    public Integer getRightLineStart() {
        return rightLineStart;
    }

    public void setRightLineStart(Integer rightLineStart) {
        this.rightLineStart = rightLineStart;
    }

    public void setRightLineStart(String rightLineStart) {
        this.rightLineStart = Integer.valueOf(rightLineStart);
    }

    public Integer getWeightOfLine() {
        return weightOfLine;
    }

    public void setWeightOfLine(Integer weightOfLine) {
        this.weightOfLine = weightOfLine;
    }
    public void setWeightOfLine(String weightOfLine) {
        this.weightOfLine = Integer.valueOf(weightOfLine);
    }
}
