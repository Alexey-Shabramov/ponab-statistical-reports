package com.uz.laboratory.statistical.entity.location;

import com.uz.laboratory.statistical.entity.Identifier;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stage")
@DynamicUpdate(value = true)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Stage extends Identifier {
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
    private List<CommunicationDistance> communicationDistanceList;

    public List<CommunicationDistance> getCommunicationDistanceList() {
        return communicationDistanceList;
    }

    public void setCommunicationDistanceList(List<CommunicationDistance> communicationDistanceList) {
        this.communicationDistanceList = communicationDistanceList;
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
}
