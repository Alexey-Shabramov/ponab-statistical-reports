package com.uz.laboratory.statistical.entity.remark;


import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;

public class PonabRemark extends AbstractRemark {
    private PonabSystem ponabSystem;
    private Stage stage;
    private String location;
    private boolean even;
    private String note;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public PonabSystem getPonabSystem() {
        return ponabSystem;
    }

    public void setPonabSystem(PonabSystem ponabSystem) {
        this.ponabSystem = ponabSystem;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isEven() {
        return even;
    }

    public void setEven(boolean even) {
        this.even = even;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
