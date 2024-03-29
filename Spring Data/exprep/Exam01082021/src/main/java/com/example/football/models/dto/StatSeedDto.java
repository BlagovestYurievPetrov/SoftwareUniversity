package com.example.football.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatSeedDto {
    @XmlElement(name = "passing")
    private Float passing;
    @XmlElement(name = "shooting")
    private Float shooting;
    @XmlElement(name = "endurance")
    private Float endurance;

    @Min(0)
    @NotNull
    public Float getPassing() {
        return passing;
    }

    public void setPassing(Float passing) {
        this.passing = passing;
    }
    @Min(0)
    @NotNull
    public Float getShooting() {
        return shooting;
    }

    public void setShooting(Float shooting) {
        this.shooting = shooting;
    }
    @Min(0)
    @NotNull
    public Float getEndurance() {
        return endurance;
    }

    public void setEndurance(Float endurance) {
        this.endurance = endurance;
    }
}
