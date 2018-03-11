package com.example.srirang.nira_android;

import java.util.Date;
import java.util.Map;

/**
 * Created by Srirang on 3/11/2018.
 */

public class AddModal {

    public String coordinates;
    public String district;
    public String state;
    public WQIModal water_quality_index;
    public Double temperature;
    public Date time_of_sample;
    public String image;
    public Integer quality_index;

    public AddModal(){}

    public AddModal(String coordinates, String district, String state, WQIModal water_quality_index, Double temperature, Date time_of_sample, String image, Integer quality_index) {

        this.coordinates = coordinates;
        this.district = district;
        this.state = state;
        this.water_quality_index = water_quality_index;
        this.temperature = temperature;
        this.time_of_sample = time_of_sample;
        this.image = image;
        this.quality_index = quality_index;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public WQIModal getWater_quality_index() {
        return water_quality_index;
    }

    public void setWater_quality_index(WQIModal water_quality_index) {
        this.water_quality_index = water_quality_index;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Date getTime_of_sample() {
        return time_of_sample;
    }

    public void setTime_of_sample(Date time_of_sample) {
        this.time_of_sample = time_of_sample;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuality_index() {
        return quality_index;
    }

    public void setQuality_index(Integer quality_index) {
        this.quality_index = quality_index;
    }



}
