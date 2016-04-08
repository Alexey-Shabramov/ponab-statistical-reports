package com.uz.laboratory.statistical.entity.remark;


import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "ponab_remark")
@DynamicUpdate(value = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "inspectionTrip", column = @Column(name = "id_inspection")),
        @AttributeOverride(name = "creationDate", column = @Column(name = "creation_date"))
})
public class PonabRemark extends AbstractRemark {
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponab_system")
    private PonabSystem ponabSystem;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stage")
    private Stage stage;

    @Column(name = "location")
    private String location;

    @Column(name = "even")
    private boolean even;

    @Column(name = "note")
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
