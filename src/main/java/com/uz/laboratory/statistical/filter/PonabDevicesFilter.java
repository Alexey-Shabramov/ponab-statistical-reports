package com.uz.laboratory.statistical.filter;


import com.uz.laboratory.statistical.dict.PonabSystems;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;

public class PonabDevicesFilter {
    private Sector sector;
    private Stage stage;
    private PonabSystems ponabSystem;
    private Boolean evenDirectionOfMovement;
    private Boolean speachInformer;
    private String option;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public PonabSystems getPonabSystem() {
        return ponabSystem;
    }

    public void setPonabSystem(PonabSystems ponabSystem) {
        this.ponabSystem = ponabSystem;
    }

    public Boolean getEvenDirectionOfMovement() {
        return evenDirectionOfMovement;
    }

    public void setEvenDirectionOfMovement(Boolean evenDirectionOfMovement) {
        this.evenDirectionOfMovement = evenDirectionOfMovement;
    }

    public Boolean getSpeachInformer() {
        return speachInformer;
    }

    public void setSpeachInformer(Boolean speachInformer) {
        this.speachInformer = speachInformer;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "PonabDevicesFilter{" +
                "sector=" + sector +
                ", stage=" + stage +
                ", ponabSystem=" + ponabSystem +
                ", evenDirectionOfMovement=" + evenDirectionOfMovement +
                ", speachInformer=" + speachInformer +
                ", option='" + option + '\'' +
                '}';
    }
}
