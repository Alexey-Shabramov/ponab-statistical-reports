package com.uz.laboratory.statistical.dto.ponab;


import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import javafx.collections.ObservableList;

public class PonabSystemEditEntityDto {
    private PonabSystem ponabSystem;
    private Integer tableViewIndex;
    private Long editedEntityId;
    private ObservableList sectorList;
    private ObservableList repeatList;

    public PonabSystem getPonabSystem() {
        return ponabSystem;
    }

    public void setPonabSystem(PonabSystem ponabSystem) {
        this.ponabSystem = ponabSystem;
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
