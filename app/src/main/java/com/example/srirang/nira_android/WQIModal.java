package com.example.srirang.nira_android;

/**
 * Created by Srirang on 3/11/2018.
 */

public class WQIModal {
    String alkalinity;
    String color;
    Double ph;
    DMSModal dissolved_metals_and_salts;
    Integer dissolved_oxygen;

    public WQIModal() {
    }

    public WQIModal(String alkalinity, String color, Double ph, DMSModal dissolved_metals_and_salts, Integer dissolved_oxygen) {
        this.alkalinity = alkalinity;
        this.color = color;
        this.ph = ph;
        this.dissolved_metals_and_salts = dissolved_metals_and_salts;
        this.dissolved_oxygen = dissolved_oxygen;
    }

    public String getAlkalinity() {
        return alkalinity;
    }

    public void setAlkalinity(String alkalinity) {
        this.alkalinity = alkalinity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public DMSModal getDissolved_metals_and_salts() {
        return dissolved_metals_and_salts;
    }

    public void setDissolved_metals_and_salts(DMSModal dissolved_metals_and_salts) {
        this.dissolved_metals_and_salts = dissolved_metals_and_salts;
    }

    public Integer getDissolved_oxygen() {
        return dissolved_oxygen;
    }

    public void setDissolved_oxygen(Integer dissolved_oxygen) {
        this.dissolved_oxygen = dissolved_oxygen;
    }
}
