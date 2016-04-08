package com.uz.laboratory.statistical.controller;


import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.service.location.SectorService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class StatisticsController implements Initializable {
    @FXML
    public TableView statisticsTableView;

    @FXML
    public ComboBox stage;

    @FXML
    public ComboBox vagonLaboratory;

    @FXML
    public Button search;

    @FXML
    public ComboBox sector;

    @FXML
    public ComboBox deviceType;

    @FXML
    public ComboBox directionOfMovement;

    @FXML
    public TextField dateTextField;

    @FXML
    public Button cleanTableView;

    @FXML
    public Button printAllTableViewDataFromExcel;

    @Autowired
    private SectorService sectorService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sector.setItems(FXCollections.observableArrayList(sectorService.listAll()));
    }

    @FXML
    public void searchRemarksByFilter(ActionEvent actionEvent) {

    }

    @FXML
    public void currentSectorSelected(ActionEvent actionEvent) {
        System.out.println("Выбрал вариант из дропбокса");
        stage.setItems(FXCollections.observableArrayList(((Sector) sector.getSelectionModel().getSelectedItem()).getStageList()));
    }

    public String getStringField(Object o) {
        return ((Stage) o).getName();
    }

    @FXML
    public void cleanTableViewData(ActionEvent actionEvent) {

    }

    @FXML
    public void printAllTableViewData(ActionEvent actionEvent) {

    }
}
