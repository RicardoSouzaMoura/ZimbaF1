package com.zimba.f1.feature.entity;

import java.io.Serializable;

public class RaceResultsEntity implements Serializable{

    private String raceName;
    private String seasonYear;
    private String date;
    private String time;
    private String circuitName;
    private String locality;
    private String country;

    private RacePositionEntity[] positions;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public RacePositionEntity[] getPositions() {
        return positions;
    }

    public void setPositions(RacePositionEntity[] positions) {
        this.positions = positions;
    }
}
