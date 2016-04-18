package com.uz.laboratory.statistical.entity.location;


import com.uz.laboratory.statistical.entity.Identifier;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sector")
@DynamicUpdate(value = true)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Sector extends Identifier {
    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stage")
    private List<Stage> stageList;

    public List<Stage> getStageList() {
        return stageList;
    }

    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "id='" + getId() + '\'' +
                "title='" + title + '\'' +
                ", stages=" + stageList +
                '}';
    }
}
