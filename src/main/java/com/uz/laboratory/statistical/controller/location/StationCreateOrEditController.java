package com.uz.laboratory.statistical.controller.location;

import com.uz.laboratory.statistical.bean.init.InitComboBoxesUtil;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.location.StationEditOrCreateDto;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.service.location.StationService;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class StationCreateOrEditController implements Initializable {
    @FXML
    public Button resetEditButton;
    @FXML
    public TextField stationNameTextField;
    @Autowired
    private StationService stationService;

    private Station station;

    @Autowired
    private StationEditOrCreateDto stationEditOrCreateDto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        station = stationEditOrCreateDto.getStation();
        if (station != null) {
            stationNameTextField.setText(station.getName());
        }
    }

    @FXML
    public void saveStationListener(ActionEvent actionEvent) {
        if (!org.apache.commons.lang3.StringUtils.isNotBlank(stationNameTextField.getText())) {
            AlertGuiUtil.createAlert(Constants.STATION_MODAL_NAME_NULL);
        } else {
            if (station == null || station.getId() < 0) {
                station = new Station();
                InitComboBoxesUtil.stationList.add(station);
            }
            station.setName(stationNameTextField.getText());
            stationService.save(station);
            stationEditOrCreateDto.setStation(station);
            station = null;
            ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
        }
    }

    @FXML
    public void resetEditButtonListener(ActionEvent actionEvent) {
        ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
    }
}
