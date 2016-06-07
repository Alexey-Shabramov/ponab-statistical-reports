package com.uz.laboratory.statistical.controller;

import com.uz.laboratory.statistical.dict.RemarkRepeat;
import com.uz.laboratory.statistical.dict.Systems;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class EditDataBaseController implements Initializable {
    @FXML
    public ComboBox<Systems> remarkChosenSystemsComboBox;
    @FXML
    public ComboBox<Stage> remarkStageComboBox;
    @FXML
    public ComboBox<Sector> remarkSectorComboBox;
    @FXML
    public ComboBox remarkDeviceTypeComboBox;
    @FXML
    public ComboBox<InspectionTrip> remarkInspectionComboBox;
    @FXML
    public ComboBox<RemarkRepeat> remarkRepeatComboBox;
    @FXML
    public TextArea remarkNoteTextArea;
    @FXML
    public DatePicker remarkDatePicker;

    @FXML
    public ComboBox<PonabSystem> ponabDeviceComboBox;
    @FXML
    public Button editPonabDeviceButton;
    @FXML
    public Button addPonabDeviceButton;
    @FXML
    public Button addAlsDeviceButton;
    @FXML
    public Button editAlsDeviceButton;
    @FXML
    public ComboBox<TrackCircuit> alsDeviceComboBox;
    @FXML
    public ComboBox<Station> stationComboBox;
    @FXML
    public Button editStationButton;
    @FXML
    public Button addStationButton;
    @FXML
    public Button addStageButton;
    @FXML
    public Button editStageEditButton;
    @FXML
    public ComboBox<Stage> stageComboBox;
    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public Button editSectorButton;
    @FXML
    public Button addSectorButton;
    @FXML
    public Button addVagonLaboratoryButton;
    @FXML
    public Button editVagonLaboratoryButton;
    @FXML
    public ComboBox<VagonLaboratory> vagonLaboratoryComboBox;
    @FXML
    public ComboBox<CommunicationDistance> communicationDistanceComboBox;
    @FXML
    public Button editCommunicationDistanceButton;
    @FXML
    public Button addCommunicationButtonButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void resetEditButton(ActionEvent actionEvent) {
    }

    @FXML
    public void addNewRemarkButton(ActionEvent actionEvent) {
    }

    @FXML
    public void remarkStageSelectionListener(ActionEvent actionEvent) {
    }

    @FXML
    public void remarkSectorOnChangeListener(ActionEvent actionEvent) {
    }

    @FXML
    public void remarkDeviceTypeComboBoxListener(ActionEvent actionEvent) {
    }

    private void initComboBoxes() {

    }
}
