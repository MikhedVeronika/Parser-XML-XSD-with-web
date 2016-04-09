package com.epam.xmlparser.entity;

import com.epam.xmlparser.characteristics.GrowingTips;
import com.epam.xmlparser.characteristics.VisualParameters;
import com.sun.org.apache.xpath.internal.operations.Mult;

public abstract class Flower {

    private String name;
    private String soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private String multiplying;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this instanceof OneYearFlower) {
            sb.append("OneYearFlower[");
        } else if (this instanceof MultiYearFlower) {
            sb.append("MultiYearFlower[");
        }
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", name='").append(this.getName()).append('\'');
        sb.append(", soil='").append(this.getSoil()).append('\'');
        sb.append(", origin='").append(this.getOrigin()).append('\'');
        sb.append(", leaf-color=").append(this.getVisualParameters().getLeafColor());
        sb.append(", flower-color=").append(this.getVisualParameters().getFlowerColor());
        sb.append(", average-size-cm=").append(this.getVisualParameters().getAverageSizeCm());
        sb.append(", multiplying=").append(this.getMultiplying());
        if (this instanceof OneYearFlower) {
            sb.append(", flowering-days-count=").append(((OneYearFlower)this).getFloweringDaysCount()).append('\'');
        } else if (this instanceof MultiYearFlower) {
            sb.append(", age-years=").append(((MultiYearFlower)this).getAgeYears()).append('\'');
        }
        sb.append("]\n");
        return sb.toString();
    }
}
