package com.uz.laboratory.statistical.dto.trip;

import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import javafx.collections.ObservableList;


public class InspectionTripEditEntityDto {
    private InspectionTrip inspectionTrip;
    private Integer tableViewIndex;
    private Long editedEntityId;
    private ObservableList sectorList;
    private ObservableList vagonLaboratoryList;

    public ObservableList getVagonLaboratoryList() {
        return vagonLaboratoryList;
    }

    public void setVagonLaboratoryList(ObservableList vagonLaboratoryList) {
        this.vagonLaboratoryList = vagonLaboratoryList;
    }

    public InspectionTrip getInspectionTrip() {
        return inspectionTrip;
    }

    public void setInspectionTrip(InspectionTrip inspectionTrip) {
        this.inspectionTrip = inspectionTrip;
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
}
