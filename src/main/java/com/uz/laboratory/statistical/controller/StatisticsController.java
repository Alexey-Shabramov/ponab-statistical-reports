package com.uz.laboratory.statistical.controller;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.DirectionsOfMovement;
import com.uz.laboratory.statistical.dict.Systems;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.filter.StatisticsFilter;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.service.trip.VagonLaboratoryService;
import com.uz.laboratory.statistical.util.AlertGuiUtil;
import com.uz.laboratory.statistical.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.collections.impl.list.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class StatisticsController implements Initializable {
    @FXML
    public TableView statisticsTableView;

    @FXML
    public ComboBox<Stage> stageComboBox;

    @FXML
    public ComboBox<VagonLaboratory> vagonLaboratoryComboBox;

    @FXML
    public Button search;

    @FXML
    public ComboBox<Sector> sectorComboBox;

    @FXML
    public ComboBox<Systems> deviceTypeComboBox;

    @FXML
    public ComboBox<DirectionsOfMovement> directionOfMovementComboBox;

    @FXML
    public TextField dateTextField;

    @FXML
    public Button cleanTableView;

    @FXML
    public Button printAllTableViewDataFromExcel;

    @FXML
    public ComboBox<Integer> dayComboBox;

    @FXML
    public ComboBox<String> monthComboBox;

    @FXML
    public ComboBox<Integer> yearsComboBox;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private VagonLaboratoryService vagonLaboratoryService;

    private List<String> errorList = new ArrayList<>();

    private boolean cleaningValue = false;


    private StringConverter<Stage> stageConverter = new StringConverter<Stage>() {
        @Override
        public String toString(Stage object) {
            return object.getName();
        }

        @Override
        public Stage fromString(String string) {
            return null;
        }
    };

    private StringConverter<Sector> sectorConverter = new StringConverter<Sector>() {
        @Override
        public String toString(Sector object) {
            return object.getTitle();
        }

        @Override
        public Sector fromString(String string) {
            return null;
        }
    };

    private StringConverter<VagonLaboratory> vagonLaboratoryConverter = new StringConverter<VagonLaboratory>() {
        @Override
        public String toString(VagonLaboratory object) {
            return object.getName();
        }

        @Override
        public VagonLaboratory fromString(String string) {
            return null;
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stageComboBox.setConverter(stageConverter);
        sectorComboBox.setConverter(sectorConverter);
        vagonLaboratoryComboBox.setConverter(vagonLaboratoryConverter);

        directionOfMovementComboBox.getItems().setAll(DirectionsOfMovement.values());
        deviceTypeComboBox.getItems().setAll(Systems.values());

        vagonLaboratoryComboBox.setItems(FXCollections.observableArrayList(vagonLaboratoryService.listAll()));
        sectorComboBox.setItems(FXCollections.observableArrayList(sectorService.listAll()));

        yearsComboBox.setItems(FXCollections.observableArrayList(Interval.fromTo(2013, 2045)));
    }

    @FXML
    public void searchRemarksByFilter(ActionEvent actionEvent) {
        StatisticsFilter statisticsFilter = new StatisticsFilter();
        statisticsFilter.setSector(sectorComboBox.getSelectionModel().getSelectedItem());
        statisticsFilter.setStage(stageComboBox.getSelectionModel().getSelectedItem());
        statisticsFilter.setVagonLaboratory(vagonLaboratoryComboBox.getSelectionModel().getSelectedItem());
        if (directionOfMovementComboBox.getSelectionModel().getSelectedItem() != null) {
            statisticsFilter.setDirectionOfMovement(directionOfMovementComboBox.getSelectionModel().getSelectedItem().toString());
        }
        if (deviceTypeComboBox.getSelectionModel().getSelectedItem() != null) {
            statisticsFilter.setDeviceType(deviceTypeComboBox.getSelectionModel().getSelectedItem().toString());
        }
    }

    @FXML
    public void currentSectorSelected(ActionEvent actionEvent) {
        stageComboBox.setItems(FXCollections.observableArrayList(sectorComboBox.getSelectionModel().getSelectedItem().getStageList()));
    }

    @FXML
    public void cleanTableViewData(ActionEvent actionEvent) {

    }

    @FXML
    public void printAllTableViewData(ActionEvent actionEvent) {

    }

    @FXML
    public void monthSelectedAction(ActionEvent actionEvent) {
        System.out.println("Месяц выбран!");
        dayComboBox.getItems().clear();
        if (yearsComboBox.getSelectionModel().getSelectedItem() == null
                || !StringUtils.isNotEmpty(yearsComboBox.getSelectionModel().getSelectedItem().toString())) {
            errorList.add(Constants.YEAR_VALUES_IS_NOT_SET);
        }
        if (cleaningValue) {
            if (monthComboBox.getSelectionModel().getSelectedItem() == null
                    || !StringUtils.isNotEmpty(monthComboBox.getSelectionModel().getSelectedItem())) {
                errorList.add(Constants.MONTH_VALUES_IS_NOT_SET);
                cleaningValue = false;
            }
        }
        if (!errorList.isEmpty()) {
            createAlert(AlertGuiUtil.prepareAlertMessage(errorList));
            errorList.clear();
        } else {
            try {
                dayComboBox.setItems(FXCollections.observableArrayList(DateUtil.getDayListByMonthYear(yearsComboBox.getSelectionModel().getSelectedItem(), DateUtil.getMonthNumberByTitle(monthComboBox.getSelectionModel().getSelectedItem()))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void yearSelectedAction(ActionEvent actionEvent) {
        System.out.println("Год выбран!");
        monthComboBox.getItems().clear();
        if (yearsComboBox.getSelectionModel().getSelectedItem() == null
                || !StringUtils.isNotEmpty(yearsComboBox.getSelectionModel().getSelectedItem().toString())) {
            createAlert(Constants.YEAR_VALUES_IS_NOT_SET);
        } else {
            monthComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(new DateFormatSymbols().getMonths())));
        }
    }

    private void createAlert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ПРОИЗОШЛА ОШИБКА!");
        alert.setHeaderText("Данное сообщение появляеться при наличии неточностей во введенной информации.");
        alert.setContentText(error);
        alert.showAndWait();
        alert = null;
    }
}
