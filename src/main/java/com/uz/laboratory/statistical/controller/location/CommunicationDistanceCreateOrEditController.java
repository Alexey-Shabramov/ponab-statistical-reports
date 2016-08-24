package com.uz.laboratory.statistical.controller.location;


import com.uz.laboratory.statistical.bean.init.InitComboBoxesUtil;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.location.CommunicationDistanceEditOrCreateDto;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.service.location.CommunicationDistanceService;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CommunicationDistanceCreateOrEditController implements Initializable {
    @FXML
    public ComboBox communicationDitanceNumberComboBox;
    @FXML
    public Button resetEditButton;

    @Autowired
    private CommunicationDistanceService communicationDistanceService;
    @Autowired
    private CommunicationDistanceEditOrCreateDto communicationDistanceEditOrCreateDto;

    private CommunicationDistance communicationDistance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        communicationDitanceNumberComboBox.getItems().setAll(IntStream.range(1, 120).boxed().collect(Collectors.toList()));
        communicationDistance = communicationDistanceEditOrCreateDto.getCommunicationDistance();
        if (communicationDistance != null) {
            communicationDitanceNumberComboBox.getSelectionModel().select(communicationDistance.getNumber());
        }
    }

    public void saveCommunicationDistanceListener(ActionEvent actionEvent) {
        if (communicationDitanceNumberComboBox.getSelectionModel().getSelectedItem() == null) {
            AlertGuiUtil.createAlert(Constants.DISTANCE_MODAL_NAME_NULL);
        } else {
            if (communicationDistance == null || communicationDistance.getId() < 0) {
                communicationDistance = new CommunicationDistance();
                InitComboBoxesUtil.communicationDistanceList.add(communicationDistance);
            }
            communicationDistance.setNumber(Byte.valueOf(communicationDitanceNumberComboBox.getSelectionModel().getSelectedItem().toString()));
            communicationDistanceService.save(communicationDistance);
            communicationDistanceEditOrCreateDto.setCommunicationDistance(communicationDistance);
            communicationDistance = null;
            ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
        }
    }

    public void resetEditButtonListener(ActionEvent actionEvent) {
        ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
    }
}
