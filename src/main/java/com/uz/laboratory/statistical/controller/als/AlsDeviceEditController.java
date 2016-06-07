package com.uz.laboratory.statistical.controller.als;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.DirectionsOfMovement;
import com.uz.laboratory.statistical.dict.TrackCircuitTypes;
import com.uz.laboratory.statistical.dto.als.AlsSystemEditEntityDto;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.service.als.TrackCircuitService;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.ComboBoxUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class AlsDeviceEditController implements Initializable {
    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public ComboBox stageOrStationComboBox;
    @FXML
    public ComboBox<TrackCircuitTypes> trackCircuitTypeComboBox;
    @FXML
    public ComboBox<DirectionsOfMovement> directionOfMovementComboBox;
    @FXML
    public TextField picketTextField;
    @FXML
    public TextField nameOfCircuitTextField;
    @FXML
    public Button resetEditButton;

    @Autowired
    private AlsSystemEditEntityDto alsSystemEditEntityDto;
    @Autowired
    private TrackCircuitService trackCircuitService;

    private List<Station> stationList = new ArrayList<>();
    private List<String> errorList = new ArrayList<>();

    private TrackCircuit trackCircuit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trackCircuit = (TrackCircuit) trackCircuitService.get(alsSystemEditEntityDto.getEditedEntityId());

        sectorComboBox.setConverter(ComboBoxUtil.sectorConverter);
        stageOrStationComboBox.setConverter(trackCircuit.isStationalCircuit() ? ComboBoxUtil.stationConverter : ComboBoxUtil.stageConverter);

        sectorComboBox.getItems().setAll(alsSystemEditEntityDto.getSectorList());
        sectorComboBox.getSelectionModel().select(trackCircuit.getSector());

        stageOrStationComboBox.getItems().setAll(trackCircuit.isStationalCircuit()
                ? convertStationList(sectorComboBox.getSelectionModel().getSelectedItem().getStageList())
                : sectorComboBox.getSelectionModel().getSelectedItem().getStageList());
        stageOrStationComboBox.getSelectionModel().select(trackCircuit.isStationalCircuit()
                ? trackCircuit.getStation()
                : trackCircuit.getStage());

        trackCircuitTypeComboBox.getItems().setAll(TrackCircuitTypes.values());
        trackCircuitTypeComboBox.getSelectionModel().select(trackCircuit.isStationalCircuit() ? TrackCircuitTypes.STATION : TrackCircuitTypes.STAGE);

        directionOfMovementComboBox.getItems().setAll(DirectionsOfMovement.values());
        directionOfMovementComboBox.getSelectionModel().select(trackCircuit.isEven() ? DirectionsOfMovement.EVEN : DirectionsOfMovement.UNEVEN);

        picketTextField.setText(trackCircuit.getPicket() != null ? trackCircuit.getPicket().toString() : null);
        nameOfCircuitTextField.setText(trackCircuit.getName());
    }

    @FXML
    public void sectorChangeListener(ActionEvent actionEvent) {
    }

    @FXML
    public void trackCircuitTypeComboBoxListener(ActionEvent actionEvent) {
    }

    @FXML
    public void saveAlsDeviceListener(ActionEvent actionEvent) {
        if (stageOrStationComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.ALS_DEVICE_STAGE_OR_STATION_NULL);
        }
        if (directionOfMovementComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.ALS_DEVICE_DIRECTION_OF_MOVEMENT_NULL);
        }
        if (sectorComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.ALS_DEVICE_SECTOR);
        }
        if (trackCircuitTypeComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.ALS_DEVICE_TYPE_NULL);
        }
        if (!StringUtils.isNotBlank(nameOfCircuitTextField.getText())) {
            errorList.add(Constants.ALS_DEVICE_NAME_NULL);
        }
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            trackCircuit.setSector(sectorComboBox.getSelectionModel().getSelectedItem());
            trackCircuit.setName(nameOfCircuitTextField.getText());
            trackCircuit.setStationalCircuit(trackCircuitTypeComboBox.getSelectionModel().getSelectedIndex() != 0);
            if (trackCircuitTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
                trackCircuit.setStage((Stage) stageOrStationComboBox.getSelectionModel().getSelectedItem());
            } else {
                trackCircuit.setStation((Station) stageOrStationComboBox.getSelectionModel().getSelectedItem());
            }
            trackCircuit.setEven(directionOfMovementComboBox.getSelectionModel().getSelectedIndex() == 0);
            trackCircuit.setPicket(picketTextField.getText() != null ? Double.valueOf(picketTextField.getText()) : null);
            trackCircuitService.save(trackCircuit);
            alsSystemEditEntityDto.setTrackCircuit(trackCircuit);
            cleanDtoSecondaryData();
            ((javafx.stage.Stage) sectorComboBox.getScene().getWindow()).close();
        }
    }

    @FXML
    public void resetEditButtonListener(ActionEvent actionEvent) {
        cleanAllDtoData();
        ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
    }

    private List<Station> convertStationList(List<Stage> stations) {
        for (Stage stage : sectorComboBox.getSelectionModel().getSelectedItem().getStageList()) {
            stationList.add(stage.getFirstStation());
            stationList.add(stage.getSecondStation());
        }
        return stationList;
    }

    private void cleanDtoSecondaryData() {
        alsSystemEditEntityDto.setEditedEntityId(null);
        alsSystemEditEntityDto.setRepeatList(null);
        alsSystemEditEntityDto.setSectorList(null);
    }

    private void cleanAllDtoData() {
        alsSystemEditEntityDto.setTrackCircuit(null);
        alsSystemEditEntityDto.setTableViewIndex(null);
        cleanDtoSecondaryData();
    }
}
