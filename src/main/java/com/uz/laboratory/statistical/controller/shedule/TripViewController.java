package com.uz.laboratory.statistical.controller.shedule;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class TripViewController implements Initializable {
    @FXML
    public TreeView tripTreeView;
    @FXML
    public Button tripViewCloseButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void closeButtonListener(ActionEvent actionEvent) {
    }
}
