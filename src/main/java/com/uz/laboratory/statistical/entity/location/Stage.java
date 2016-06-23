package com.uz.laboratory.statistical.entity.location;

import com.uz.laboratory.statistical.entity.Identifier;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stage")
@DynamicUpdate(value = true)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Stage extends Identifier implements Comparable<Stage> {
    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_first_station")
    private Station firstStation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_second_station")
    private Station secondStation;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_communication_distance")
    private List<CommunicationDistance> distanceList;

    public Stage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(Station firstStation) {
        this.firstStation = firstStation;
    }

    public Station getSecondStation() {
        return secondStation;
    }

    public void setSecondStation(Station secondStation) {
        this.secondStation = secondStation;
    }

    public List<CommunicationDistance> getDistanceList() {
        return distanceList;
    }

    public void setDistanceList(List<CommunicationDistance> distanceList) {
        this.distanceList = distanceList;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", first_station=" + firstStation +
                ", second_station=" + secondStation +
                ", distances_list=" + distanceList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stage stage = (Stage) o;

        if (!name.equals(stage.name)) return false;
        if (!firstStation.equals(stage.firstStation)) return false;
        if (!secondStation.equals(stage.secondStation)) return false;
        return distanceList.equals(stage.distanceList);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + firstStation.hashCode();
        result = 31 * result + secondStation.hashCode();
        result = 31 * result + distanceList.hashCode();
        return result;
    }

    @Override
    public int compareTo(Stage o) {
        return 0;
    }
}
