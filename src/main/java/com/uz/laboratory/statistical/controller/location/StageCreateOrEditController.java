package com.uz.laboratory.statistical.controller.location;

import com.uz.laboratory.statistical.bean.init.InitComboBoxesUtil;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.location.StageEditOrCreateDto;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.service.location.StageService;
import com.uz.laboratory.statistical.util.DtoUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.ComboBoxUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class StageCreateOrEditController implements Initializable {
    @FXML
    public ComboBox<Station> firstStationComboBox;
    @FXML
    public ComboBox<Station> secondStationComboBox;
    @FXML
    public ComboBox<CommunicationDistance> generalCommunicationDistanceComboBox;
    @FXML
    public ComboBox<CommunicationDistance> optionalCommunicationDistanceComboBox;
    @FXML
    public Button resetEditButton;

    @Autowired
    private StageEditOrCreateDto stageEditOrCreateDto;
    @Autowired
    private StageService stageService;

    private List<String> errorList = new ArrayList<>();

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBoxes();
        stage = stageEditOrCreateDto.getStage();
        if (stage != null) {
            firstStationComboBox.getSelectionModel().select(stage.getFirstStation());
            secondStationComboBox.getSelectionModel().select(stage.getSecondStation());
            if (!stage.getDistanceList().isEmpty()) {
                generalCommunicationDistanceComboBox.getSelectionModel().select(stage.getDistanceList().get(0));
                if (stage.getDistanceList().size() > 1) {
                    optionalCommunicationDistanceComboBox.getSelectionModel().select(stage.getDistanceList().get(1));
                }
            }
        }
    }

    private void initComboBoxes() {
        firstStationComboBox.setConverter(ComboBoxUtil.stationConverter);
        secondStationComboBox.setConverter(ComboBoxUtil.stationConverter);
        generalCommunicationDistanceComboBox.setConverter(ComboBoxUtil.communicationDistanceConverter);
        optionalCommunicationDistanceComboBox.setConverter(ComboBoxUtil.communicationDistanceConverter);
        firstStationComboBox.getItems().setAll(InitComboBoxesUtil.stationList);
        secondStationComboBox.getItems().setAll(InitComboBoxesUtil.stationList);
        generalCommunicationDistanceComboBox.getItems().setAll(InitComboBoxesUtil.communicationDistanceList);
        optionalCommunicationDistanceComboBox.getItems().setAll(InitComboBoxesUtil.communicationDistanceList);
    }

    @FXML
    public void saveStageListener(ActionEvent actionEvent) {
        if (secondStationComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.STAGE_EDIT_FIRST_STATION_NULL);
        }
        if (firstStationComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.STAGE_EDIT_SECOND_STATION_NULL);
        }
        if (generalCommunicationDistanceComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.STAGE_EDIT_COMMUNICATION_DISTANCE_NULL);
        }
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            if (stage == null || stage.getId() < 0) {
                stage = new Stage();
                stage.setDistanceList(new ArrayList<>());
                InitComboBoxesUtil.stageList.add(stage);
            }
            stage.setFirstStation(firstStationComboBox.getSelectionModel().getSelectedItem());
            stage.setSecondStation(secondStationComboBox.getSelectionModel().getSelectedItem());
            List<CommunicationDistance> communicationDistanceList = stage.getDistanceList();
            communicationDistanceList.clear();
            communicationDistanceList.add(generalCommunicationDistanceComboBox.getSelectionModel().getSelectedItem());
            if (optionalCommunicationDistanceComboBox.getSelectionModel().getSelectedItem() != null) {
                communicationDistanceList.add(optionalCommunicationDistanceComboBox.getSelectionModel().getSelectedItem());
            }
            stage.setDistanceList(communicationDistanceList);
            stage.setName(DtoUtil.stageNameBuilder(stage));
            stageService.save(stage);
            stageEditOrCreateDto.setStage(stage);
            stage = null;
            communicationDistanceList = null;
            ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
        }
    }

    @FXML
    public void resetEditButtonListener(ActionEvent actionEvent) {
        ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
    }
}
