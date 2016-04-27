package com.uz.laboratory.statistical.dto.tableView;


import javafx.beans.property.SimpleStringProperty;

public class StatisticsRemarkTableDto {
    private final SimpleStringProperty remarkId;
    private final SimpleStringProperty objectColumn;
    private final SimpleStringProperty noteColumn;
    private final SimpleStringProperty stageColumn;
    private final SimpleStringProperty dateColumn;
    private final SimpleStringProperty vagonColumn;
    private final SimpleStringProperty repeatColumn;

    public StatisticsRemarkTableDto(Long remarkId, String objectColumn, String noteColumn, String stageColumn, String dateColumn, String vagonColumn, String repeatColumn) {
        this.remarkId = new SimpleStringProperty(remarkId.toString());
        this.objectColumn = new SimpleStringProperty(objectColumn);
        this.noteColumn = new SimpleStringProperty(noteColumn);
        this.stageColumn = new SimpleStringProperty(stageColumn);
        this.dateColumn = new SimpleStringProperty(dateColumn);
        this.vagonColumn = new SimpleStringProperty(vagonColumn);
        this.repeatColumn = new SimpleStringProperty(repeatColumn);
    }

    public String getRemarkId() {
        return remarkId.get();
    }

    public void setRemarkId(String remarkId) {
        this.remarkId.set(remarkId);
    }

    public SimpleStringProperty remarkIdProperty() {
        return remarkId;
    }

    public String getObjectColumn() {
        return objectColumn.get();
    }

    public void setObjectColumn(String objectColumn) {
        this.objectColumn.set(objectColumn);
    }

    public SimpleStringProperty objectColumnProperty() {
        return objectColumn;
    }

    public String getNoteColumn() {
        return noteColumn.get();
    }

    public void setNoteColumn(String noteColumn) {
        this.noteColumn.set(noteColumn);
    }

    public SimpleStringProperty noteColumnProperty() {
        return noteColumn;
    }

    public String getStageColumn() {
        return stageColumn.get();
    }

    public void setStageColumn(String stageColumn) {
        this.stageColumn.set(stageColumn);
    }

    public SimpleStringProperty stageColumnProperty() {
        return stageColumn;
    }

    public String getDateColumn() {
        return dateColumn.get();
    }

    public void setDateColumn(String dateColumn) {
        this.dateColumn.set(dateColumn);
    }

    public SimpleStringProperty dateColumnProperty() {
        return dateColumn;
    }

    public String getVagonColumn() {
        return vagonColumn.get();
    }

    public void setVagonColumn(String vagonColumn) {
        this.vagonColumn.set(vagonColumn);
    }

    public SimpleStringProperty vagonColumnProperty() {
        return vagonColumn;
    }

    public String getRepeatColumn() {
        return repeatColumn.get();
    }

    public void setRepeatColumn(String repeatColumn) {
        this.repeatColumn.set(repeatColumn);
    }

    public SimpleStringProperty repeatColumnProperty() {
        return repeatColumn;
    }
}
