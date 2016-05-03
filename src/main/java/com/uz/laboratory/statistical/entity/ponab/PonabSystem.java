package com.uz.laboratory.statistical.entity.ponab;

import com.uz.laboratory.statistical.entity.Identifier;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ponab_system")
@DynamicUpdate(value = true)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class PonabSystem extends Identifier {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sector")
    private Sector sector;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stage")
    private Stage stage;

    @Column(name = "title")
    private String title;

    @Column(name = "option")
    private String option;

    @Column(name = "location")
    private String location;

    @Column(name = "speach_informer")
    private boolean speachInformer;

    @Column(name = "even_direction")
    private boolean evenDirectionOfMovement;

    @OneToMany(mappedBy = "ponabSystem", cascade = CascadeType.REMOVE)
    private List<PonabRemark> ponabRemarks;

    public List<PonabRemark> getPonabRemarks() {
        return ponabRemarks;
    }

    public void setPonabRemarks(List<PonabRemark> ponabRemarks) {
        this.ponabRemarks = ponabRemarks;
    }

    public boolean isEvenDirectionOfMovement() {
        return evenDirectionOfMovement;
    }

    public void setEvenDirectionOfMovement(boolean evenDirectionOfMovement) {
        this.evenDirectionOfMovement = evenDirectionOfMovement;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public boolean isSpeachInformer() {
        return speachInformer;
    }

    public void setSpeachInformer(boolean speachInformer) {
        this.speachInformer = speachInformer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
}
