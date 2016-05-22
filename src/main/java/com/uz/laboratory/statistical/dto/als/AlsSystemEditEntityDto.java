package com.uz.laboratory.statistical.dto.als;


import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import javafx.collections.ObservableList;

public class AlsSystemEditEntityDto {
    private TrackCircuit trackCircuit;
    private Integer tableViewIndex;
    private Long editedEntityId;
    private ObservableList sectorList;
    private ObservableList repeatList;

    public TrackCircuit getTrackCircuit() {
        return trackCircuit;
    }

    public void setTrackCircuit(TrackCircuit trackCircuit) {
        this.trackCircuit = trackCircuit;
    }

    public Integer getTableViewIndex() {
        return tableViewIndex;
    }

    public void setTableViewIndex(Integer tableViewIndex) {
        this.tableViewIndex = tableViewIndex;
    }

    public Long getEditedEntityId() {
        return editedEntityId;
    }

    public void setEditedEntityId(Long editedEntityId) {
        this.editedEntityId = editedEntityId;
    }

    public ObservableList getSectorList() {
        return sectorList;
    }

    public void setSectorList(ObservableList sectorList) {
        this.sectorList = sectorList;
    }

    public ObservableList getRepeatList() {
        return repeatList;
    }

    public void setRepeatList(ObservableList repeatList) {
        this.repeatList = repeatList;
    }
}
