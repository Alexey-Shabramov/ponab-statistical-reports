package com.uz.laboratory.statistical.controller.shedule;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.SheduleTripsTypes;
import com.uz.laboratory.statistical.dto.trip.InspectionTripEditEntityDto;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.service.trip.InspectionTripService;
import com.uz.laboratory.statistical.util.InitComboBoxesUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.ComboBoxUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class TripCreateOrEditController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBoxes();
        if (inspectionTripEditEntityDto.getEditedEntityId() != null) {
            inspectionTrip = (InspectionTrip) inspectionTripService.get(inspectionTripEditEntityDto.getEditedEntityId());
            prepareInspectionTripEdit();
        } else if (inspectionTripEditEntityDto.getInspectionTrip() != null) {
            inspectionTrip = inspectionTripEditEntityDto.getInspectionTrip();
            prepareInspectionTripEdit();
        }
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
            if (inspectionTrip == null || inspectionTrip.getId() < 0) {
                inspectionTrip = new InspectionTrip();
                InitComboBoxesUtil.inspectionTripList.add(inspectionTrip);
            }
            inspectionTrip.setTripSector(sectorComboBox.getSelectionModel().getSelectedItem());
            inspectionTrip.setDate(Date.from(tripDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            inspectionTrip.setVagonLaboratory(vagonLaboratoryComboBox.getSelectionModel().getSelectedItem());
            inspectionTrip.setPlannedTrip(tripTypeComboBox.getSelectionModel().getSelectedIndex() == 0);
            inspectionTripService.save(inspectionTrip);
            inspectionTripEditEntityDto.setInspectionTrip(inspectionTrip);
            cleanDtoSecondaryData();
            ((javafx.stage.Stage) sectorComboBox.getScene().getWindow()).close();
        }
    }

    private void initComboBoxes() {
        sectorComboBox.setConverter(ComboBoxUtil.sectorConverter);
        vagonLaboratoryComboBox.setConverter(ComboBoxUtil.vagonLaboratoryConverter);
        sectorComboBox.getItems().setAll(InitComboBoxesUtil.sectorList);
        vagonLaboratoryComboBox.getItems().addAll(InitComboBoxesUtil.vagonLaboratoryList);
        tripTypeComboBox.getItems().setAll(SheduleTripsTypes.values());
    }

    private void prepareInspectionTripEdit() {
        sectorComboBox.getSelectionModel().select(inspectionTrip.getTripSector());
        vagonLaboratoryComboBox.getSelectionModel().select(inspectionTrip.getVagonLaboratory());
        tripTypeComboBox.getSelectionModel().select(!inspectionTrip.isPlannedTrip() ? SheduleTripsTypes.INSPECTION : SheduleTripsTypes.PLANNED);
        tripDatePicker.setValue(LocalDateTime.ofInstant(Instant.ofEpochMilli(inspectionTrip.getDate().getTime()), ZoneId.systemDefault()).toLocalDate());
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
