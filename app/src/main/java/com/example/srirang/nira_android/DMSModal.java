package com.example.srirang.nira_android;

/**
 * Created by Srirang on 3/11/2018.
 */

public class DMSModal {
    Double sodium;
    Double chloride;
    Double potassium;
    Double calcium;
    Double manganese;
    Double magnessium;

    public DMSModal(){}

    public DMSModal(Double sodium, Double chloride, Double potassium, Double calcium, Double manganese, Double magnessium, Double lead, Double mercury, Double arsenic) {

        this.sodium = sodium;
        this.chloride = chloride;
        this.potassium = potassium;
        this.calcium = calcium;
        this.manganese = manganese;
        this.magnessium = magnessium;
        this.lead = lead;
        this.mercury = mercury;
        this.arsenic = arsenic;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getChloride() {
        return chloride;
    }

    public void setChloride(Double chloride) {
        this.chloride = chloride;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getManganese() {
        return manganese;
    }

    public void setManganese(Double manganese) {
        this.manganese = manganese;
    }

    public Double getMagnessium() {
        return magnessium;
    }

    public void setMagnessium(Double magnessium) {
        this.magnessium = magnessium;
    }

    public Double getLead() {
        return lead;
    }

    public void setLead(Double lead) {
        this.lead = lead;
    }

    public Double getMercury() {
        return mercury;
    }

    public void setMercury(Double mercury) {
        this.mercury = mercury;
    }

    public Double getArsenic() {
        return arsenic;
    }

    public void setArsenic(Double arsenic) {
        this.arsenic = arsenic;
    }

    Double lead;
    Double mercury;
    Double arsenic;
}
