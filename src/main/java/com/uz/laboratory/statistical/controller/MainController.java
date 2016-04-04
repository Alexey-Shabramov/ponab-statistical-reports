package com.uz.laboratory.statistical.controller;

import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.service.location.CommunicationDistanceService;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainController implements Initializable {

    @Autowired
    private CommunicationDistanceService communicationDistanceService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CommunicationDistance communicationDistance = new CommunicationDistance();
        byte baba = 4;
        communicationDistance.setNumber(baba);
        communicationDistanceService.save(communicationDistance);
        System.out.println(communicationDistanceService.get(1));
    }
}
