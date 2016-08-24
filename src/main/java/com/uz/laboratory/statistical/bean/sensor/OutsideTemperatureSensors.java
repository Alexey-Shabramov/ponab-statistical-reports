package com.uz.laboratory.statistical.bean.sensor;

public class OutsideTemperatureSensors {
    private volatile Sensor leftOutsideTemperatureSensor;
    private volatile Sensor rightOutsideTemperatureSensor;

    public OutsideTemperatureSensors() {
        leftOutsideTemperatureSensor = new Sensor();
        rightOutsideTemperatureSensor = new Sensor();
    }

    public Sensor getLeftOutsideTemperatureSensor() {
        return leftOutsideTemperatureSensor;
    }

    public void setLeftOutsideTemperatureSensor(Sensor leftOutsideTemperatureSensor) {
        this.leftOutsideTemperatureSensor = leftOutsideTemperatureSensor;
    }

    public Sensor getRightOutsideTemperatureSensor() {
        return rightOutsideTemperatureSensor;
    }

    public void setRightOutsideTemperatureSensor(Sensor rightOutsideTemperatureSensor) {
        this.rightOutsideTemperatureSensor = rightOutsideTemperatureSensor;
    }
}
