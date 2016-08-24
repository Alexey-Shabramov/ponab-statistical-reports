package com.uz.laboratory.statistical.util.quartz;

import com.uz.laboratory.statistical.controller.temperature.SensorController;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class ThermalRightSensorShedulerJob extends QuartzJobBean {

    private SensorController sensorController;

    public void setSensorController(SensorController sensorController) {
        this.sensorController = sensorController;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (sensorController != null) {
            sensorController.checkRightIronSensorTemperature();
        }
    }
}
