package com.uz.laboratory.statistical.dto.ponab;


import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;

public class PonabDeviceDto {
    private Long id;
    private Sector sector;
    private Stage stage;
    private String title;
    private String option;
    private String location;
    private boolean speachInformer;
    private boolean evenDirectionOfMovement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isSpeachInformer() {
        return speachInformer;
    }

    public void setSpeachInformer(boolean speachInformer) {
        this.speachInformer = speachInformer;
    }

    public boolean isEvenDirectionOfMovement() {
        return evenDirectionOfMovement;
    }

    public void setEvenDirectionOfMovement(boolean evenDirectionOfMovement) {
        this.evenDirectionOfMovement = evenDirectionOfMovement;
    }

    @Override
    public String toString() {
        return "PonabDeviceDto{" +
                "id=" + id +
                ", sector=" + sector +
                ", stage=" + stage +
                ", title='" + title + '\'' +
                ", option='" + option + '\'' +
                ", location='" + location + '\'' +
                ", speachInformer=" + speachInformer +
                ", evenDirectionOfMovement=" + evenDirectionOfMovement +
                '}';
    }
}
