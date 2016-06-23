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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_first_station")
    private Station firstStation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_last_station")
    private Station lastStation;

    @OneToMany(fetch = FetchType.LAZY)
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

    public Station getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(Station firstStation) {
        this.firstStation = firstStation;
    }

    public Station getLastStation() {
        return lastStation;
    }

    public void setLastStation(Station lastStation) {
        this.lastStation = lastStation;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "id='" + getId() + '\'' +
                "title='" + title + '\'' +
                ", firstStation=" + firstStation +
                ", lastStation=" + lastStation +
                ", stageList=" + stageList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sector sector = (Sector) o;

        if (!title.equals(sector.title)) return false;
        if (!firstStation.equals(sector.firstStation)) return false;
        if (!lastStation.equals(sector.lastStation)) return false;
        return stageList.equals(sector.stageList);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + firstStation.hashCode();
        result = 31 * result + lastStation.hashCode();
        result = 31 * result + stageList.hashCode();
        return result;
    }
}
