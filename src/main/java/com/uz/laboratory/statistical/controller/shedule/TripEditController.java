package com.uz.laboratory.statistical.controller.shedule;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.SheduleTripsTypes;
import com.uz.laboratory.statistical.dto.trip.InspectionTripEditEntityDto;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.service.trip.InspectionTripService;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class TripEditController implements Initializable {
    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public ComboBox<VagonLaboratory> vagonLaboratoryComboBox;
    @FXML
    public ComboBox<SheduleTripsTypes> tripTypeComboBox;
    @FXML
    public DatePicker tripDatePicker;
    @FXML
    public Button resetEditButton;

    @Autowired
    private InspectionTripEditEntityDto inspectionTripEditEntityDto;
    @Autowired
    private InspectionTripService inspectionTripService;

    private List<String> errorList = new ArrayList<>();

    private InspectionTrip inspectionTrip;

    private StringConverter<Sector> sectorConverter = new StringConverter<Sector>() {
        @Override
        public String toString(Sector object) {
            return object.getTitle();
        }

        @Override
        public Sector fromString(String string) {
            return null;
        }
    };
    private StringConverter<VagonLaboratory> vagonLaboratoryConverter = new StringConverter<VagonLaboratory>() {
        @Override
        public String toString(VagonLaboratory object) {
            return object.getName();
        }

        @Override
        public VagonLaboratory fromString(String string) {
            return null;
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inspectionTrip = (InspectionTrip) inspectionTripService.get(inspectionTripEditEntityDto.getEditedEntityId());
        System.out.println(inspectionTrip.toString());

        sectorComboBox.setConverter(sectorConverter);
        vagonLaboratoryComboBox.setConverter(vagonLaboratoryConverter);

        sectorComboBox.getItems().setAll(inspectionTripEditEntityDto.getSectorList());
        sectorComboBox.getSelectionModel().select(inspectionTrip.getTripSector());

        vagonLaboratoryComboBox.getItems().setAll(inspectionTripEditEntityDto.getVagonLaboratoryList());
        vagonLaboratoryComboBox.getSelectionModel().select(inspectionTrip.getVagonLaboratory());

        tripTypeComboBox.getItems().setAll(SheduleTripsTypes.values());
        tripTypeComboBox.getSelectionModel().select(!inspectionTrip.isPlannedTrip() ? SheduleTripsTypes.INSPECTION : SheduleTripsTypes.PLANNED);

        tripDatePicker.setValue(LocalDateTime.ofInstant(Instant.ofEpochMilli(inspectionTrip.getDate().getTime()), ZoneId.systemDefault()).toLocalDate());
    }

    @FXML
    public void sectorChangeListener(ActionEvent actionEvent) {
    }

    @FXML
    public void saveTripListener(ActionEvent actionEvent) {
        if (sectorComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.TRIP_EDIT_SECTOR_IS_NULL);
        }
        if (tripDatePicker.getValue() == null) {
            errorList.add(Constants.TRIP_EDIT_DATE_IS_NULL);
        }
        if (vagonLaboratoryComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.TRIP_EDIT_VAGON_IS_NULL);
        }
        if (tripTypeComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.TRIP_EDIT_TYPE_IS_NULL);
        }
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            inspectionTrip.setTripSector(sectorComboBox.getSelectionModel().getSelectedItem());
            inspectionTrip.setDate(Date.from(tripDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            inspectionTrip.setVagonLaboratory(vagonLaboratoryComboBox.getSelectionModel().getSelectedItem());
            inspectionTrip.setPlannedTrip(tripTypeComboBox.getSelectionModel().getSelectedIndex() != 0 ? false : true);
            inspectionTripService.save(inspectionTrip);
            inspectionTripEditEntityDto.setInspectionTrip(inspectionTrip);
            cleanDtoSecondaryData();
            ((javafx.stage.Stage) sectorComboBox.getScene().getWindow()).close();
        }
    }

    @FXML
    public void resetEditButtonListener(ActionEvent actionEvent) {
        cleanAllDtoData();
        ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
    }

    private void cleanDtoSecondaryData() {
        inspectionTripEditEntityDto.setEditedEntityId(null);
        inspectionTripEditEntityDto.setVagonLaboratoryList(null);
        inspectionTripEditEntityDto.setSectorList(null);
    }

    private void cleanAllDtoData() {
        inspectionTripEditEntityDto.setInspectionTrip(null);
        inspectionTripEditEntityDto.setTableViewIndex(null);
        cleanDtoSecondaryData();
    }
}
