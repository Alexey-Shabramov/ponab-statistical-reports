package com.uz.laboratory.statistical.controller.settings;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.TemperatureContainer;
import com.uz.laboratory.statistical.app.PonabStatisticalReports;
import com.uz.laboratory.statistical.bean.adapter.GenericAdapter;
import com.uz.laboratory.statistical.bean.init.InitComboBoxesUtil;
import com.uz.laboratory.statistical.bean.init.InitTemperatureDeviceSettingsUtil;
import com.uz.laboratory.statistical.bean.movement.EvenMovement;
import com.uz.laboratory.statistical.bean.movement.UnevenMovement;
import com.uz.laboratory.statistical.bean.sensor.GlobalSensorList;
import com.uz.laboratory.statistical.bean.sensor.GlobalSensorMap;
import com.uz.laboratory.statistical.bean.sensor.OutsideTemperatureSensors;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.entity.settings.ThermalSettings;
import com.uz.laboratory.statistical.service.thermal.ThermalSettingsService;
import com.uz.laboratory.statistical.service.util.utilImpl.HibernateUtilServiceImpl;
import com.uz.laboratory.statistical.util.GitUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jssc.SerialPortList;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class SettingsController implements Initializable {
    @FXML
    public Button updateDatabaseButton;
    @FXML
    public Button updateCurrentDatabaseButton;

    @FXML
    public Button updateAllButton;
    @FXML
    public TextArea loggerInformation;
    @FXML
    public TextField sensorId;
    @FXML
    public ComboBox checkSensorIdList;
    @FXML
    public ComboBox<String> evenLeftSensorIdList;
    @FXML
    public ComboBox<String> unevenLeftSensorIdList;
    @FXML
    public ComboBox<String> evenRightSensorIdList;
    @FXML
    public ComboBox<String> unevenRightSensorIdList;
    @FXML
    public ComboBox<String> temperatureLeftSensorIdList;
    @FXML
    public ComboBox<String> temperatureRightSensorIdList;
    @FXML
    public ComboBox<String> adapterPortList;
    @FXML
    public ComboBox<String> adapterList;
    @FXML
    public TextArea checkedSensorStatus;
    @FXML
    public ComboBox<String> unevenPortList;
    @FXML
    public ComboBox<String> evenPortList;
    @FXML
    public Button checkSensorByIdFromListButton;
    @FXML
    public Button checkSensorByIdButton;
    @Autowired
    public GlobalSensorList globalSensorList;
    @Autowired
    public GlobalSensorMap globalSensorMap;
    @Autowired
    private GenericAdapter defaultAdapter;
    @Autowired
    private InitComboBoxesUtil initComboBoxesUtil;
    @Autowired
    private InitTemperatureDeviceSettingsUtil initTemperatureDeviceSettingsUtil;
    @Autowired
    private GitUtil gitUtil;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private HibernateUtilServiceImpl hibernateUtilService;
    @Autowired
    private EvenMovement evenMovement;
    @Autowired
    private UnevenMovement unevenMovement;
    @Autowired
    private OutsideTemperatureSensors outsideTemperatureSensors;
    @Autowired

    private ThermalSettingsService thermalSettingsService;
    private Object syncObj = new Object();
    @FXML
    private Service<Void> updateAllData = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    updateAllButton.setDisable(true);
                    loggerInformation.appendText("Производиться обновление всей информации о датчиках и адаптерах... \n");
                    globalSensorMap.clear();
                    globalSensorList.clear();
                    initComboBoxesUtil.initComboBoxes();
                    initTemperatureDeviceSettingsUtil.initTemperatureDeviceAndComboBoxes();
                    updateAllButton.setDisable(false);
                    loggerInformation.appendText("Обновление успешно завершено. \n");
                    updateAllData.cancel();
                    return null;
                }
            };
        }
    };

    public SettingsController() {
        checkSensorIdList = new ComboBox();
        evenLeftSensorIdList = checkSensorIdList;
        evenRightSensorIdList = checkSensorIdList;
        unevenLeftSensorIdList = checkSensorIdList;
        unevenRightSensorIdList = checkSensorIdList;
        temperatureLeftSensorIdList = checkSensorIdList;
        temperatureRightSensorIdList = checkSensorIdList;
    }

    @FXML
    public void updateDatabaseButtonListener(ActionEvent actionEvent) {
        try {
            if (java.lang.Runtime.getRuntime().exec("ping www.github.com").waitFor() != 0) {
                AlertGuiUtil.createAlert(Constants.INTERNET_IS_NOT_AVAILABLE);
            } else {
                gitUtil.commitAndPush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateCurrentDatabaseButtonListener(ActionEvent actionEvent) {
        try {
            if (java.lang.Runtime.getRuntime().exec("ping www.github.com").waitFor() != 0) {
                AlertGuiUtil.createAlert(Constants.INTERNET_IS_NOT_AVAILABLE);
            } else {
                hibernateUtilService.shutdownDataBase();
                ((ConfigurableApplicationContext) applicationContext).close();
                gitUtil.pullAndMerge();
                PonabStatisticalReports.restartMainStage();
            }
        } catch (GitAPIException | IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggerInformation.textProperty().addListener((observable, oldValue, newValue) -> {
            loggerInformation.setScrollTop(Double.MAX_VALUE);
        });
        List totalSerialPortList = Arrays.asList(SerialPortList.getPortNames());
        try {
            if (defaultAdapter.getBaseAdapter() != null) {
                adapterList.getItems().addAll(defaultAdapter.getBaseAdapter().getAdapterName());
                adapterPortList.getItems().add(defaultAdapter.getBaseAdapter().getPortName());
            }
        } catch (OneWireException e) {
            e.printStackTrace();
        }
        evenPortList.getItems().setAll(totalSerialPortList);
        evenPortList.getSelectionModel().select(evenMovement.getEvenControlPort() != null ? evenMovement.getEvenControlPort().getPortName() : "");

        unevenPortList.getItems().setAll(totalSerialPortList);
        unevenPortList.getSelectionModel().select(unevenMovement.getUnevenControlPort() != null ? unevenMovement.getUnevenControlPort().getPortName() : "");

        checkSensorIdList.getItems().setAll(globalSensorList);

        evenLeftSensorIdList.getItems().setAll(globalSensorList);
        evenLeftSensorIdList.getSelectionModel().select((evenMovement.getLeftIronSensor() != null ? evenMovement.getLeftIronSensor().getSensorName() : ""));

        evenRightSensorIdList.getItems().setAll(globalSensorList);
        evenRightSensorIdList.getSelectionModel().select((evenMovement.getRightIronSensor() != null ? evenMovement.getRightIronSensor().getSensorName() : ""));

        unevenLeftSensorIdList.getItems().setAll(globalSensorList);
        unevenLeftSensorIdList.getSelectionModel().select((unevenMovement.getLeftIronSensor() != null ? unevenMovement.getLeftIronSensor().getSensorName() : ""));

        unevenRightSensorIdList.getItems().setAll(globalSensorList);
        unevenRightSensorIdList.getSelectionModel().select(unevenMovement.getRightIronSensor() != null ? unevenMovement.getRightIronSensor().getSensorName() : "");

        temperatureLeftSensorIdList.getItems().setAll(globalSensorList);
        temperatureLeftSensorIdList.getSelectionModel().select(outsideTemperatureSensors.getLeftOutsideTemperatureSensor() != null ? outsideTemperatureSensors.getLeftOutsideTemperatureSensor().getSensorName() : "");

        temperatureRightSensorIdList.getItems().setAll(globalSensorList);
        temperatureRightSensorIdList.getSelectionModel().select(outsideTemperatureSensors.getRightOutsideTemperatureSensor() != null ? outsideTemperatureSensors.getRightOutsideTemperatureSensor().getSensorName() : "");
    }

    private void initComboBoxes() {

    }

    @FXML
    public void checkSensorStatus(ActionEvent actionEvent) {
        if (StringUtils.isNotBlank((CharSequence) checkSensorIdList.getSelectionModel().getSelectedItem())) {
            TaggedDevice taggedDevice = (TaggedDevice) globalSensorMap.get(checkSensorIdList.getSelectionModel().getSelectedItem().toString());
            DSPortAdapter l_adapter = null;
            TemperatureContainer l_container = null;
            synchronized (syncObj) {
                if (defaultAdapter.getBaseAdapter() == null)
                    return;
                l_adapter = defaultAdapter.getBaseAdapter();
                l_container = (TemperatureContainer) taggedDevice.getDeviceContainer();
            }
            try {
                l_adapter.beginExclusive(true);
                byte[] state = l_container.readDevice();
                l_container.doTemperatureConvert(state);
                if (StringUtils.isNotEmpty(checkedSensorStatus.getText())) {
                    checkedSensorStatus.setText("");
                }
                checkedSensorStatus.setText("Статус: Доступен;  Проверка: Исправен;  Температура: " + Double.toString(l_container.getTemperature(state)));
                loggerInformation.appendText("Проверен датчик: " + checkSensorIdList.getSelectionModel().getSelectedItem().toString() + ";  Температура: " + Double.toString(l_container.getTemperature(state)) + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error reading device!" + e.toString());
                loggerInformation.appendText("Error reading device! ! \n");
                checkedSensorStatus.setText("Ошибка при чтении с адаптера!");
            } finally {
                l_adapter.endExclusive();
            }
        } else {
            loggerInformation.appendText("Вы ввели пустое значение! \n");
            checkedSensorStatus.setText("Вы ввели пустое значение!");
        }
    }

    @FXML
    public void checkSensorByIdTextfieldButton(ActionEvent actionEvent) {
        if (StringUtils.isNotBlank(sensorId.getText())
                && globalSensorMap.containsKey(sensorId.getText())) {
            DSPortAdapter l_adapter = null;
            TemperatureContainer l_container = null;
            synchronized (syncObj) {
                if (defaultAdapter.getBaseAdapter() == null)
                    return;
                l_adapter = defaultAdapter.getBaseAdapter();
                l_container = (TemperatureContainer) ((TaggedDevice) globalSensorMap.get(checkSensorIdList.getSelectionModel().getSelectedItem().toString())).getDeviceContainer();
            }
            try {
                l_adapter.beginExclusive(true);
                byte[] state = l_container.readDevice();
                l_container.doTemperatureConvert(state);
                if (StringUtils.isNotEmpty(checkedSensorStatus.getText())) {
                    checkedSensorStatus.setText("");
                }
                checkedSensorStatus.setText(Constants.SENSOR_CHECK_MESSAGE + Double.toString(l_container.getTemperature(state)));
                loggerInformation.appendText("Проверен датчик: " + sensorId.getText() + "Температура: " + Double.toString(l_container.getTemperature(state)));
            } catch (Exception e) {
                System.out.println(Constants.ERROR_READING_DEVICE + e.toString());
                loggerInformation.appendText(Constants.ERROR_READING_DEVICE);
            } finally {
                l_adapter.endExclusive();
            }
        } else {
            loggerInformation.appendText(Constants.WRONG_SENSOR_ID_INSERTED);
            checkedSensorStatus.setText(Constants.WRONG_SENSOR_ID_INSERTED);
        }
    }

    @FXML
    public void saveSensorsIdByMovement(ActionEvent actionEvent) {
        ThermalSettings thermalSettings = (ThermalSettings) thermalSettingsService.get(1);
        if (thermalSettings == null) {
            thermalSettings = new ThermalSettings();
        }
        if (StringUtils.isNotEmpty(evenLeftSensorIdList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setEvenLeftIronSensorAddress(evenLeftSensorIdList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty(evenRightSensorIdList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setEvenRightIronSensorAddress(evenRightSensorIdList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty(unevenLeftSensorIdList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setUnevenLeftIronSensorAddress(unevenLeftSensorIdList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty(unevenRightSensorIdList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setUnevenRightIronSensorAddress(unevenRightSensorIdList.getSelectionModel().getSelectedItem());
        }
        thermalSettingsService.save(thermalSettings);
        loggerInformation.appendText("Адреса датчиков по направлению движения сохранены. \n");
    }

    @FXML
    public void saveSensorIdByOuterTemperature(ActionEvent actionEvent) {
        ThermalSettings thermalSettings = (ThermalSettings) thermalSettingsService.get(1);
        if (thermalSettings == null) {
            thermalSettings = new ThermalSettings();
        }
        if (StringUtils.isNotEmpty(temperatureLeftSensorIdList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setTemperatureLeftSensorAddress(temperatureLeftSensorIdList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty(temperatureRightSensorIdList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setTemperatureRightSensorAddress(temperatureRightSensorIdList.getSelectionModel().getSelectedItem());
        }
        loggerInformation.appendText(Constants.TEMPERATURE_SENSORS_ID_SAVED);
    }

    @FXML
    public void saveControlPortsNumbers(ActionEvent actionEvent) {
        ThermalSettings thermalSettings = (ThermalSettings) thermalSettingsService.get(1);
        if (thermalSettings == null) {
            thermalSettings = new ThermalSettings();
        }
        if (StringUtils.isNotEmpty(evenPortList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setEvenControlPortAddress(evenPortList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty(unevenPortList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setUnevenControlPortAddress(unevenPortList.getSelectionModel().getSelectedItem());
        }
        thermalSettingsService.save(thermalSettings);
        loggerInformation.appendText(Constants.PORTS_ID_SAVED);
    }

    @FXML
    public void updateAll(ActionEvent actionEvent) {
        updateAllData.restart();
    }

    @FXML
    public void сleanLogger(ActionEvent actionEvent) {
        loggerInformation.setText("");
    }

    @FXML
    public void saveAdapterAndPort(ActionEvent actionEvent) {
        ThermalSettings thermalSettings = (ThermalSettings) thermalSettingsService.get(1);
        if (thermalSettings == null) {
            thermalSettings = new ThermalSettings();
        }
        if (StringUtils.isNotEmpty(adapterList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setAdapterAddress(adapterList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty(adapterPortList.getSelectionModel().getSelectedItem())) {
            thermalSettings.setAdapterPort(adapterPortList.getSelectionModel().getSelectedItem());
        }
        loggerInformation.appendText("Адреса адаптера и портов сохранены. \n");
        thermalSettingsService.save(thermalSettings);
    }
}
