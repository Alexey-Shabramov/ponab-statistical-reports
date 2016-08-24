package com.uz.laboratory.statistical.bean.init;


import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.OneWireContainer;
import com.uz.laboratory.statistical.bean.adapter.DefaultPortList;
import com.uz.laboratory.statistical.bean.adapter.GenericAdapter;
import com.uz.laboratory.statistical.bean.movement.EvenMovement;
import com.uz.laboratory.statistical.bean.movement.UnevenMovement;
import com.uz.laboratory.statistical.bean.sensor.GlobalSensorList;
import com.uz.laboratory.statistical.bean.sensor.GlobalSensorMap;
import com.uz.laboratory.statistical.bean.sensor.OutsideTemperatureSensors;
import com.uz.laboratory.statistical.entity.settings.ThermalSettings;
import com.uz.laboratory.statistical.service.thermal.ThermalSettingsService;
import jssc.SerialPort;
import jssc.SerialPortList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Enumeration;

public class InitTemperatureDeviceSettingsUtil {
    final static Logger logger = Logger.getLogger(InitTemperatureDeviceSettingsUtil.class);

    private OneWireContainer oneWireContainer;
    @Autowired
    private GlobalSensorMap globalSensorMap;
    @Autowired
    private GlobalSensorList globalSensorList;
    @Autowired
    private ThermalSettingsService thermalSettingsService;
    @Autowired
    private EvenMovement evenMovement;
    @Autowired
    private UnevenMovement unevenMovement;
    @Autowired
    private OutsideTemperatureSensors outsideTemperatureSensors;
    @Autowired
    private GenericAdapter genericAdapter;
    @Autowired
    private DefaultPortList defaultPortList;

    private ThermalSettings thermalSettings;

    public InitTemperatureDeviceSettingsUtil() {
    }

    @PostConstruct
    public void initTemperatureDeviceAndComboBoxes() {
        try {
            logger.info("Начало загрузки...");
            loadAndSetSettings();
            logger.info("Полученные настройки: " + (thermalSettings == null ? null : thermalSettings.toString()));
            collectAllFoundedDevicesAndPorts();
            System.out.println(genericAdapter.getBaseAdapter());
            for (Enumeration<? extends OneWireContainer> owd_enum = genericAdapter.getBaseAdapter().getAllDeviceContainers(); owd_enum.hasMoreElements(); ) {
                oneWireContainer = owd_enum.nextElement();
                globalSensorList.add(oneWireContainer.getAddressAsString());
                globalSensorMap.put(oneWireContainer.getAddressAsString(), new TaggedDevice(genericAdapter.getBaseAdapter(), oneWireContainer.getAddressAsString()));
                logger.info("Device Found: " + oneWireContainer.getAddressAsString());
                System.out.println("Device Found: " + oneWireContainer.getAddressAsString());
            }
        } catch (OneWireException e) {
            e.printStackTrace();
            logger.error("Ошибка при загрузке ", e);
        }
    }

    private void loadAndSetSettings() {
        thermalSettings = (ThermalSettings) thermalSettingsService.get(1);
        if (thermalSettings != null) {
            evenMovement.getLeftIronSensor().setSensorId(thermalSettings.getEvenLeftIronSensorAddress());
            evenMovement.getRightIronSensor().setSensorId(thermalSettings.getEvenRightIronSensorAddress());
            evenMovement.setEvenControlPort(new SerialPort(thermalSettings.getEvenControlPortAddress()));
            unevenMovement.getLeftIronSensor().setSensorId(thermalSettings.getUnevenLeftIronSensorAddress());
            unevenMovement.getRightIronSensor().setSensorId(thermalSettings.getUnevenRightIronSensorAddress());
            unevenMovement.setUnevenControlPort(new SerialPort(thermalSettings.getUnevenControlPortAddress()));
            outsideTemperatureSensors.getLeftOutsideTemperatureSensor().setSensorId(thermalSettings.getTemperatureLeftSensorAddress());
            outsideTemperatureSensors.getRightOutsideTemperatureSensor().setSensorId(thermalSettings.getTemperatureRightSensorAddress());
        }
    }

    private void collectAllFoundedDevicesAndPorts() throws OneWireException {
        logger.info("Установка базового адаптера.");
        if (OneWireAccessProvider.getDefaultAdapter() != null) {
            genericAdapter.setBaseAdapter(OneWireAccessProvider.getDefaultAdapter());
        }
        logger.info("Установка базового списка портов.");
        Collections.addAll(defaultPortList, SerialPortList.getPortNames());
    }
}
