package com.uz.laboratory.statistical.dto;


import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import javafx.collections.ObservableList;

public class PonabRemarkEditEntityDto {

    private PonabRemark ponabRemark;

    private Integer tableViewIndex;
    private Long editedEntityId;
    private ObservableList sectorList;
    private ObservableList repeatList;
    private String note;

    public Integer getTableViewIndex() {
        return tableViewIndex;
    }

    public void setTableViewIndex(Integer tableViewIndex) {
        this.tableViewIndex = tableViewIndex;
    }

    public PonabRemark getPonabRemark() {
        return ponabRemark;
    }

    public void setPonabRemark(PonabRemark ponabRemark) {
        this.ponabRemark = ponabRemark;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
