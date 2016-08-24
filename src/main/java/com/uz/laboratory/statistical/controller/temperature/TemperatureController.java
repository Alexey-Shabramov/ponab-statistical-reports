package com.uz.laboratory.statistical.controller.temperature;

import com.uz.laboratory.statistical.bean.movement.EvenMovement;
import com.uz.laboratory.statistical.bean.movement.UnevenMovement;
import com.uz.laboratory.statistical.bean.option.TemperatureOption;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.PonabOptions;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.NumericTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import jssc.SerialPortException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class TemperatureController implements Initializable {
    @FXML
    public Button startWarming;

    @FXML
    public NumericTextField setLeftIronTemperature;

    @FXML
    public NumericTextField setRightIronTemperature;

    @FXML
    public ComboBox<PonabOptions> systemOptionList;

    @FXML
    public RadioButton unevenDirectionOfMovement;

    @FXML
    public RadioButton evenDirectionOfMovement;

    @FXML
    public NumericTextField warmingValuesStatus;

    @Autowired
    private TemperatureOption temperatureOption;

    @Autowired
    private EvenMovement evenMovement;
    @Autowired
    private UnevenMovement unevenMovement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        systemOptionList.getItems().setAll(PonabOptions.values());
    }

    @FXML
    public void evenRadioAction(ActionEvent actionEvent) {
        unevenDirectionOfMovement.setSelected(false);
        temperatureOption.setEvenDirectionOfMovement(true);
        temperatureOption.setControlPortAddressByMovment(evenMovement.getEvenControlPort());
    }

    @FXML
    public void unevenRadioAction(ActionEvent actionEvent) {
        evenDirectionOfMovement.setSelected(false);
        temperatureOption.setEvenDirectionOfMovement(false);
        temperatureOption.setControlPortAddressByMovment(unevenMovement.getUnevenControlPort());
    }

    @FXML
    public void beginWarming(ActionEvent actionEvent) throws Exception {
        if (!temperatureOption.isWarmingOn()) {
            List<String> errorList = new ArrayList<>();
            if (!StringUtils.isNotEmpty(setLeftIronTemperature.getText())
                    && !StringUtils.isNotEmpty(setRightIronTemperature.getText())) {
                errorList.add(Constants.ALERT_WARMING_VALUES_IS_EMPTY);
            }
            if (temperatureOption.getControlPortAddressByMovment() == null) {
                errorList.add(Constants.ALERT_CURRENT_SETTINGS_CONTROL_PORT_NOT_WORKING);
            }
            if (!evenDirectionOfMovement.isSelected() && !unevenDirectionOfMovement.isSelected()) {
                errorList.add(Constants.ALERT_MOVEMENT_IS_NOT_SET);
            }
            if (!StringUtils.isNotEmpty(systemOptionList.getSelectionModel().getSelectedItem().toString())) {
                errorList.add(Constants.OPTION_IS_NOT_SET);
            }
            if (!errorList.isEmpty()) {
                AlertGuiUtil.prepareAlertMessage(errorList);
            } else {
                temperatureOption.setWarmingOn(true);
                if (evenDirectionOfMovement.isSelected()) {
                    temperatureOption.setEvenDirectionOfMovement(true);
                    temperatureOption.setControlPortAddressByMovment(evenMovement.getEvenControlPort());
                } else if (unevenDirectionOfMovement.isSelected()) {
                    temperatureOption.setEvenDirectionOfMovement(false);
                    temperatureOption.setControlPortAddressByMovment(unevenMovement.getUnevenControlPort());
                }
                try {
                    if (!temperatureOption.getControlPortAddressByMovment().isOpened()) {
                        temperatureOption.getControlPortAddressByMovment().openPort();
                    }
                    temperatureOption.getControlPortAddressByMovment().setDTR(true);
                    temperatureOption.getControlPortAddressByMovment().setRTS(true);
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
                if (!setLeftIronTemperature.getText().isEmpty()) {
                    temperatureOption.setInstallLeftTemperature(Double.valueOf(setLeftIronTemperature.getText()));
                }
                if (!setRightIronTemperature.getText().isEmpty()) {
                    temperatureOption.setInstallRightTemperature(Double.valueOf(setRightIronTemperature.getText()));
                }
                temperatureOption.setSystemOption(systemOptionList.getSelectionModel().getSelectedItem().toString());
                startWarming.setText("Выключить нагрев");
            }
        } else {
            List<String> errorList = new ArrayList<>();
            if (temperatureOption.getControlPortAddressByMovment() == null) {
                errorList.add(Constants.ALERT_CURRENT_SETTINGS_CONTROL_PORT_NOT_WORKING);
            }
            if (!errorList.isEmpty()) {
                AlertGuiUtil.prepareAlertMessage(errorList);
            } else {
                temperatureOption.setWarmingOn(false);
                try {
                    temperatureOption.getControlPortAddressByMovment().setRTS(false);
                    temperatureOption.getControlPortAddressByMovment().setDTR(false);
                    temperatureOption.getControlPortAddressByMovment().closePort();
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
                temperatureOption.setInstallLeftTemperature(null);
                temperatureOption.setInstallRightTemperature(null);
                startWarming.setText("Включить нагрев");
            }
        }
    }
}
