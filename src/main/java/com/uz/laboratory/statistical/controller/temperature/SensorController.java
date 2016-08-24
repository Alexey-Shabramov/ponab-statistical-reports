package com.uz.laboratory.statistical.controller.temperature;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.TemperatureContainer;
import com.uz.laboratory.statistical.bean.adapter.GenericAdapter;
import com.uz.laboratory.statistical.bean.movement.EvenMovement;
import com.uz.laboratory.statistical.bean.movement.UnevenMovement;
import com.uz.laboratory.statistical.bean.option.TemperatureOption;
import com.uz.laboratory.statistical.bean.sensor.GlobalSensorMap;
import com.uz.laboratory.statistical.bean.sensor.OutsideTemperatureSensors;
import com.uz.laboratory.statistical.dict.Constants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class SensorController implements Initializable {
    final static Logger logger = Logger.getLogger(SensorController.class);

    @FXML
    public TextField leftIronTemperature;
    @FXML
    public TextField rightIronTemperature;
    @FXML
    public TextField firstOutsideTemperatureSensorStatus;
    @FXML
    public TextField secondOutsideTemperatureSensorStatus;
    @FXML
    public TextField thirdIronSensorStatus;
    @FXML
    public TextField fourhtIronSensorStatus;
    @FXML
    public TextField firstIronSensorStatus;
    @FXML
    public TextField wirePortStatus;
    @FXML
    public TextField secondIronSensorStatus;
    @FXML
    public TextField outsideTemperatureValue;
    @FXML
    public LineChart<Number, Number> leftIronAreaChart;
    @FXML
    public LineChart<Number, Number> rightIronAreaChart;

    @Autowired
    private TemperatureOption temperatureOption;
    @Autowired
    private EvenMovement evenMovement;
    @Autowired
    private UnevenMovement unevenMovement;
    @Autowired
    private GenericAdapter genericAdapter;
    @Autowired
    private GlobalSensorMap globalSensorMap;
    @Autowired
    private OutsideTemperatureSensors outsideTemperatureSensors;

    private Object firstSyncObj = new Object();
    private Object secondSyncObj = new Object();
    private Object thirdSyncObj = new Object();

    public SensorController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (genericAdapter.getBaseAdapter() != null
                    && genericAdapter.getBaseAdapter().adapterDetected()) {
                wirePortStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
            }
        } catch (OneWireException e) {
            e.printStackTrace();
        }
        leftIronTemperature.setAlignment(Pos.CENTER);
        rightIronTemperature.setAlignment(Pos.CENTER);
        firstOutsideTemperatureSensorStatus.setAlignment(Pos.CENTER);
        secondOutsideTemperatureSensorStatus.setAlignment(Pos.CENTER);
    }

    public void checkLeftIronSensorTemperature() {
        try {
            if (genericAdapter.getBaseAdapter() != null
                    && genericAdapter.getBaseAdapter().adapterDetected()) {
                wirePortStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                String leftSensor;
                if (temperatureOption.isEvenDirectionOfMovement()) {
                    leftSensor = evenMovement.getLeftIronSensor().getSensorId();
                } else {
                    leftSensor = unevenMovement.getLeftIronSensor().getSensorId();
                }
                logger.info("Адрес сенсора равен:" + leftSensor);
                if (StringUtils.isNotBlank(leftSensor)
                        && globalSensorMap.containsKey(leftSensor)) {
                    logger.info(Constants.SENSOR_IS_NOT_BLANK);
                    DSPortAdapter l_adapter = null;
                    TemperatureContainer l_container = null;
                    synchronized (firstSyncObj) {
                        l_adapter = genericAdapter.getBaseAdapter();
                        l_container = (TemperatureContainer) ((TaggedDevice) globalSensorMap.get(leftSensor)).getDeviceContainer();
                    }
                    try {
                        l_adapter.beginExclusive(true);
                        byte[] state = l_container.readDevice();
                        l_container.doTemperatureConvert(state);
                        temperatureOption.setLeftTemperature(l_container.getTemperature(state));
                        leftIronTemperature.setText(Double.toString(l_container.getTemperature(state)));
                        if (temperatureOption.isEvenDirectionOfMovement()) {
                            firstIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                            thirdIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                        } else {
                            thirdIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                            firstIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                        }
                        System.out.println(Constants.TEMPERATURE_IS + Double.toString(l_container.getTemperature(state)));
                    } catch (Exception e) {
                        logger.info(Constants.ERROR_READING_DEVICE + e.toString());
                        System.out.println(Constants.ERROR_READING_DEVICE + e.toString());
                        if (temperatureOption.isEvenDirectionOfMovement()) {
                            firstIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                        } else {
                            thirdIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                        }
                    } finally {
                        l_adapter.endExclusive();
                    }
                } else {
                    if (temperatureOption.isEvenDirectionOfMovement()) {
                        firstIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                    } else {
                        thirdIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                    }
                    System.out.println(Constants.SENSORS_NOT_FOUND);
                    logger.info(Constants.SENSORS_NOT_FOUND);
                }
            } else {
                wirePortStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                logger.info(Constants.ADAPTER_IS_NOT_WORKING);
            }
        } catch (OneWireException e) {
            e.printStackTrace();
        }
    }

    public void checkRightIronSensorTemperature() {
        String rightSensor;
        if (temperatureOption.isEvenDirectionOfMovement()) {
            rightSensor = evenMovement.getRightIronSensor().getSensorId();
        } else {
            rightSensor = unevenMovement.getRightIronSensor().getSensorId();
        }
        if (StringUtils.isNotBlank(rightSensor)
                && globalSensorMap.containsKey(rightSensor)) {
            TaggedDevice taggedDevice = (TaggedDevice) globalSensorMap.get(rightSensor);
            DSPortAdapter l_adapter = null;
            TemperatureContainer l_container = null;
            synchronized (secondSyncObj) {
                l_adapter = genericAdapter.getBaseAdapter();
                l_container = (TemperatureContainer) taggedDevice.getDeviceContainer();
            }
            try {
                l_adapter.beginExclusive(true);
                byte[] state = l_container.readDevice();
                l_container.doTemperatureConvert(state);
                temperatureOption.setRightTemperature(l_container.getTemperature(state));
                rightIronTemperature.setText(Double.toString(l_container.getTemperature(state)));
                if (temperatureOption.isEvenDirectionOfMovement()) {
                    secondIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                    fourhtIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                } else {
                    fourhtIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                    secondIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                }
            } catch (OneWireIOException e) {
                if (temperatureOption.isEvenDirectionOfMovement()) {
                    secondIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                } else {
                    fourhtIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                }
                System.out.println(Constants.ERROR_READING_DEVICE + e.toString());
            } catch (OneWireException e) {
                e.printStackTrace();
            } finally {
                l_adapter.endExclusive();
            }
        } else {
            if (temperatureOption.isEvenDirectionOfMovement()) {
                secondIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
            } else {
                fourhtIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
            }
        }
    }

    public void checkLeftOutsideSensorTemperature() {
        String leftSensor = outsideTemperatureSensors.getLeftOutsideTemperatureSensor().getSensorId();
        if (StringUtils.isNotBlank(leftSensor)
                && globalSensorMap.containsKey(leftSensor)) {
            TaggedDevice taggedDevice = (TaggedDevice) globalSensorMap.get(leftSensor);
            DSPortAdapter l_adapter = null;
            TemperatureContainer l_container = null;
            synchronized (thirdSyncObj) {
                l_adapter = genericAdapter.getBaseAdapter();
                l_container = (TemperatureContainer) taggedDevice.getDeviceContainer();
            }
            try {
                l_adapter.beginExclusive(true);
                byte[] state = l_container.readDevice();
                l_container.doTemperatureConvert(state);
                firstOutsideTemperatureSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                temperatureOption.setOuterTemperature(l_container.getTemperature(state));
                outsideTemperatureValue.setText(Double.toString(l_container.getTemperature(state)));
            } catch (OneWireIOException e) {
                firstOutsideTemperatureSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                System.out.println(Constants.ERROR_READING_DEVICE + e.toString());
            } catch (OneWireException e) {
                e.printStackTrace();
            } finally {
                l_adapter.endExclusive();
            }
        } else {
            firstOutsideTemperatureSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
        }
    }
}
