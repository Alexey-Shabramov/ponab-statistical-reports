package com.uz.laboratory.statistical.controller.location;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.location.SectorEditOrCreateDto;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.util.DtoUtil;
import com.uz.laboratory.statistical.util.InitComboBoxesUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.ComboBoxUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class SectorCreateOrEditController implements Initializable {
    @FXML
    public Button resetEditButton;
    @FXML
    public ComboBox<Station> firstStationComboBox;
    @FXML
    public ComboBox<Station> lastStationComboBox;
    @FXML
    public CheckComboBox<Stage> stageCheckComboBox;

    @Autowired
    private SectorEditOrCreateDto sectorEditOrCreateDto;
    @Autowired
    private SectorService sectorService;

    private List<String> errorList = new ArrayList<>();

    private Sector sector;

    public SectorCreateOrEditController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBoxes();
        sector = sectorEditOrCreateDto.getSector();
        if (sector != null) {
            firstStationComboBox.getSelectionModel().select(sector.getFirstStation());
            lastStationComboBox.getSelectionModel().select(sector.getLastStation());
            if (!sector.getStageList().isEmpty()) {
                stageCheckComboBox.getCheckModel().checkIndices(ComboBoxUtil.convertIndexesForStageCheckComboBox(sector.getStageList()));
            }
        }
    }

    private void initComboBoxes() {
        firstStationComboBox.setConverter(ComboBoxUtil.stationConverter);
        lastStationComboBox.setConverter(ComboBoxUtil.stationConverter);
        stageCheckComboBox.setConverter(ComboBoxUtil.stageConverter);
        firstStationComboBox.getItems().setAll(InitComboBoxesUtil.stationList);
        lastStationComboBox.getItems().setAll(InitComboBoxesUtil.stationList);
        stageCheckComboBox.getItems().setAll(ComboBoxUtil.addAllStagesByIdToComboBox(InitComboBoxesUtil.stageList));
    }

    @FXML
    public void saveSectorListener(ActionEvent actionEvent) {
        if (firstStationComboBox.getSelectionModel().getSelectedItem() == null)
            errorList.add(Constants.SECTOR_EDIT_FIRST_STATION_NULL);
        if (lastStationComboBox.getSelectionModel().getSelectedItem() == null)
            errorList.add(Constants.SECTOR_EDIT_LAST_STATION_NULL);
        if (stageCheckComboBox.getCheckModel().getCheckedItems().isEmpty())
            errorList.add(Constants.SECTOR_EDIT_STAGE_LIST_NULL);
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            if (sector == null || sector.getId() < 0) {
                sector = new Sector();
                sector.setStageList(new ArrayList<>());
                InitComboBoxesUtil.sectorList.add(sector);
            }
            sector.setFirstStation(firstStationComboBox.getSelectionModel().getSelectedItem());
            sector.setLastStation(lastStationComboBox.getSelectionModel().getSelectedItem());
            List<Stage> stageList = sector.getStageList();
            stageList.clear();
            stageList.addAll(stageCheckComboBox.getCheckModel().getCheckedItems());
            sector.setStageList(stageList);
            sector.setTitle(DtoUtil.sectorNameBuilder(sector));
            sectorService.save(sector);
            sectorEditOrCreateDto.setSector(sector);
            sector = null;
            stageList = null;
            ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
        }
    }

    @FXML
    public void resetEditButtonListener(ActionEvent actionEvent) {
        ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
    }
}
