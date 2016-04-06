package com.nicholaschirkevich.game.entity;

/**
 * Created by Nikolas on 11.03.2016.
 */
public class Car {
    private String name;
    private String carNameText;
    private String mapType;
    private Integer speed;
    private Integer weight;
    private Integer curveType;
    private Integer brakeDuration;
    private Integer rareStatus;
    private Integer position;
    private Boolean isForRealMoney;
    private Float possability;
    private String ID;
    private String Status;
    private Float Price;
    private String carNameImage;
    private BrakeLines brakeLines;
    private LeftRocketPosition leftRocketPosition;
    private RightRocketPosition rightRocketPosition;
    private NormalTextures normalTextures;
    private BrokenTextures brokenTextures;

    public Car(String name, String carNameText, String mapType, Integer speed, Integer weight, Integer curveType, Integer brakeDuration, Integer rareStatus, Integer position, Boolean isForRealMoney, Float possability, String ID, String status, Float price, String carNameImage, BrakeLines brakeLines, LeftRocketPosition leftRocketPosition, RightRocketPosition rightRocketPosition, NormalTextures normalTextures, BrokenTextures brokenTextures) {
        this.name = name;
        this.carNameText = carNameText;
        this.mapType = mapType;
        this.speed = speed;
        this.weight = weight;
        this.curveType = curveType;
        this.brakeDuration = brakeDuration;
        this.rareStatus = rareStatus;
        this.position = position;
        this.isForRealMoney = isForRealMoney;
        this.possability = possability;
        this.ID = ID;
        Status = status;
        Price = price;
        this.carNameImage = carNameImage;
        this.brakeLines = brakeLines;
        this.leftRocketPosition = leftRocketPosition;
        this.rightRocketPosition = rightRocketPosition;
        this.normalTextures = normalTextures;
        this.brokenTextures = brokenTextures;
    }

    public Car() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNameText() {
        return carNameText;
    }

    public void setCarNameText(String carNameText) {
        this.carNameText = carNameText;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void setSpeed(String speed) {
        Integer speedInt = Integer.valueOf(speed);
        this.speed = speedInt;
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setWeight(String weight) {
        this.weight = Integer.valueOf(weight);
    }

    public Integer getCurveType() {
        return curveType;
    }

    public void setCurveType(Integer curveType) {
        this.curveType = curveType;
    }
    public void setCurveType(String curveType) {
        this.curveType = Integer.valueOf(curveType);
    }
    public Integer getBrakeDuration() {
        return brakeDuration;
    }

    public void setBrakeDuration(Integer brakeDuration) {
        this.brakeDuration = brakeDuration;
    }

    public void setBrakeDuration(String brakeDuration) {
        this.brakeDuration = Integer.valueOf(brakeDuration);
    }
    public Integer getRareStatus() {
        return rareStatus;
    }

    public void setRareStatus(Integer rareStatus) {
        this.rareStatus = rareStatus;
    }

    public void setRareStatus(String rareStatus) {
        this.rareStatus =rareStatus==null?null:Integer.valueOf(rareStatus);
    }


    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setPosition(String position) {
        this.position = Integer.valueOf(position);
    }

    public Boolean getIsForRealMoney() {
        return isForRealMoney;
    }

    public void setIsForRealMoney(Boolean isForRealMoney) {
        this.isForRealMoney = isForRealMoney;
    }
    public void setIsForRealMoney(String isForRealMoney) {
        Integer value = Integer.valueOf(isForRealMoney);
        if(value.equals(0)) this.isForRealMoney = false;
        else if(value.equals(1)) this.isForRealMoney = true;
    }

    public Float getPossability() {
        return possability;
    }

    public void setPossability(Float possability) {
        this.possability = possability;
    }

    public void setPossability(String possability) {
        this.possability =possability==null?null:Float.valueOf(possability);
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public void setPrice(String price) {
        Price =Float.valueOf(price);
    }
    public String getCarNameImage() {
        return carNameImage;
    }

    public void setCarNameImage(String carNameImage) {
        this.carNameImage = carNameImage;
    }

    public BrakeLines getBrakeLines() {
        return brakeLines;
    }

    public void setBrakeLines(BrakeLines brakeLines) {
        this.brakeLines = brakeLines;
    }

    public LeftRocketPosition getLeftRocketPosition() {
        return leftRocketPosition;
    }

    public void setLeftRocketPosition(LeftRocketPosition leftRocketPosition) {
        this.leftRocketPosition = leftRocketPosition;
    }

    public RightRocketPosition getRightRocketPosition() {
        return rightRocketPosition;
    }

    public void setRightRocketPosition(RightRocketPosition rightRocketPosition) {
        this.rightRocketPosition = rightRocketPosition;
    }

    public NormalTextures getNormalTextures() {
        return normalTextures;
    }

    public void setNormalTextures(NormalTextures normalTextures) {
        this.normalTextures = normalTextures;
    }

    public BrokenTextures getBrokenTextures() {
        return brokenTextures;
    }

    public void setBrokenTextures(BrokenTextures brokenTextures) {
        this.brokenTextures = brokenTextures;
    }
}
