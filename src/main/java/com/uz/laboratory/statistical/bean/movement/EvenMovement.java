package com.uz.laboratory.statistical.bean.movement;

import com.uz.laboratory.statistical.bean.sensor.Sensor;
import jssc.SerialPort;

public class EvenMovement {
    private volatile Sensor leftIronSensor;
    private volatile Sensor rightIronSensor;
    private volatile SerialPort evenControlPort;

    public EvenMovement() {
        leftIronSensor = new Sensor();
        rightIronSensor = new Sensor();
    }

    public Sensor getLeftIronSensor() {
        return leftIronSensor;
    }

    public void setLeftIronSensor(Sensor leftIronSensor) {
        this.leftIronSensor = leftIronSensor;
    }

    public Sensor getRightIronSensor() {
        return rightIronSensor;
    }

    public void setRightIronSensor(Sensor rightIronSensor) {
        this.rightIronSensor = rightIronSensor;
    }

    public SerialPort getEvenControlPort() {
        return evenControlPort;
    }

    public void setEvenControlPort(SerialPort evenControlPort) {
        this.evenControlPort = evenControlPort;
    }

    @Override
    public String toString() {
        return "EvenMovement{" +
                "leftIronSensor=" + leftIronSensor +
                ", rightIronSensor=" + rightIronSensor +
                ", evenControlPort=" + evenControlPort +
                '}';
    }
}
