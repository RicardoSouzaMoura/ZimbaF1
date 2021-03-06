package com.zimba.f1.feature.entity;

import java.io.Serializable;

public class RacePositionEntity implements Serializable {

    private String position;
    private String driverName;
    private String constructorName;
    private String constructorNumber;
    private String points;
    private String time;
    private String constructorCountry;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getConstructorName() {
        return constructorName;
    }

    public void setConstructorName(String constructorName) {
        this.constructorName = constructorName;
    }

    public String getConstructorNumber() {
        return constructorNumber;
    }

    public void setConstructorNumber(String constructorNumber) {
        this.constructorNumber = constructorNumber;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getConstructorCountry() {
        return constructorCountry;
    }

    public void setConstructorCountry(String constructorCountry) {
        this.constructorCountry = constructorCountry;
    }
}
