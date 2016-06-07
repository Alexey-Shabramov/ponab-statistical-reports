package com.uz.laboratory.statistical.controller.shedule;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.SheduleTripsTypes;
import com.uz.laboratory.statistical.dto.trip.InspectionTripDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ResourceBundle;

@Controller
public class TripViewController implements Initializable {
    @FXML
    public TreeView tripTreeView;
    @FXML
    public Button tripViewCloseButton;

    @Autowired
    private InspectionTripDto inspectionTripDto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> treeRootItem = new TreeItem<>(Constants.TRIP_VIEW_TRIP_ID + inspectionTripDto.getId());

        TreeItem<String> sectorRootItem = new TreeItem<>(Constants.TRIP_VIEW_SECTOR_TITLE);
        TreeItem<String> sectorItem = new TreeItem<>(inspectionTripDto.getTripSector().getTitle());
        sectorRootItem.getChildren().addAll(sectorItem);

        TreeItem<String> vagonRootItem = new TreeItem<>(Constants.TRIP_VIEW_VAGON_TITLE);
        TreeItem<String> vagonItem = new TreeItem<>(inspectionTripDto.getVagonLaboratory().getName());
        vagonRootItem.getChildren().addAll(vagonItem);

        TreeItem<String> tripDateRootItem = new TreeItem<>(Constants.TRIP_VIEW_DATE);
        TreeItem<String> dateItem = new TreeItem<>(inspectionTripDto.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(new DateTimeFormatterBuilder().appendPattern("dd.MM.yyyy").toFormatter()));
        tripDateRootItem.getChildren().addAll(dateItem);

        TreeItem<String> tripTypeRootItem = new TreeItem<>(Constants.TRIP_VIEW_TYPE);
        TreeItem<String> tripTypeItem = new TreeItem<>(inspectionTripDto.getPlannedTrip() ? SheduleTripsTypes.PLANNED.toString() : SheduleTripsTypes.INSPECTION.toString());
        tripTypeRootItem.getChildren().addAll(tripTypeItem);

        treeRootItem.getChildren().addAll(sectorRootItem, vagonRootItem, tripDateRootItem, tripTypeRootItem);

        treeRootItem.setExpanded(true);
        sectorRootItem.setExpanded(true);
        vagonRootItem.setExpanded(true);
        tripDateRootItem.setExpanded(true);
        tripTypeRootItem.setExpanded(true);

        tripTreeView.setRoot(treeRootItem);
    }

    @FXML
    public void closeButtonListener(ActionEvent actionEvent) {
        ((javafx.stage.Stage) tripViewCloseButton.getScene().getWindow()).close();
    }
}
