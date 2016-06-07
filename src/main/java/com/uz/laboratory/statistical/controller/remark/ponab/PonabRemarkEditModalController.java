package com.uz.laboratory.statistical.controller.remark.ponab;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.ponab.PonabRemarkEditEntityDto;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.service.ponab.PonabSystemService;
import com.uz.laboratory.statistical.service.remark.PonabRemarkService;
import com.uz.laboratory.statistical.service.trip.InspectionTripService;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.ComboBoxUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class PonabRemarkEditModalController implements Initializable {

    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public ComboBox<Stage> stageComboBox;
    @FXML
    public ComboBox<PonabSystem> ponabSystemsComboBox;
    @FXML
    public ComboBox repeatComboBox;
    @FXML
    public ComboBox<InspectionTrip> inspectionComboBox;
    @FXML
    public TextArea noteTextArea;
    @FXML
    public DatePicker ponabRemarkDatePicker;

    private PonabRemark ponabRemark;

    private List<String> errorList = new ArrayList<>();

    @Autowired
    private PonabRemarkEditEntityDto ponabRemarkEditEntityDto;

    @Autowired
    private PonabRemarkService ponabRemarkService;
    @Autowired
    private PonabSystemService ponabSystemService;
    @Autowired
    private InspectionTripService inspectionTripService;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ponabRemark = (PonabRemark) ponabRemarkService.get(ponabRemarkEditEntityDto.getEditedEntityId());

        ponabRemarkDatePicker.setValue(LocalDateTime.ofInstant(Instant.ofEpochMilli(ponabRemark.getCreationDate().getTime()), ZoneId.systemDefault()).toLocalDate());

        sectorComboBox.setConverter(ComboBoxUtil.sectorConverter);
        stageComboBox.setConverter(ComboBoxUtil.stageConverter);
        inspectionComboBox.setConverter(ComboBoxUtil.inspectionTripConverter);
        ponabSystemsComboBox.setConverter(ComboBoxUtil.ponabSystemConverter);
        sectorComboBox.getItems().setAll(ponabRemarkEditEntityDto.getSectorList());
        sectorComboBox.getSelectionModel().select(ponabRemark.getPonabSystem().getSector());

        stageComboBox.getItems().setAll(sectorComboBox.getSelectionModel().getSelectedItem().getStageList());
        stageComboBox.getSelectionModel().select(ponabRemark.getPonabSystem().getStage());

        inspectionComboBox.getItems().setAll(inspectionTripService.getInspectionTripsBySector(ponabRemark.getInspectionTrip().getTripSector()));
        inspectionComboBox.getSelectionModel().select(ponabRemark.getInspectionTrip());

        ponabSystemsComboBox.getItems().setAll(ponabSystemService.getPonabSystemsByStage(ponabRemark.getPonabSystem().getStage()));
        ponabSystemsComboBox.getSelectionModel().select(ponabRemark.getPonabSystem());

        noteTextArea.setText(ponabRemarkEditEntityDto.getNote());

        repeatComboBox.getItems().setAll(ponabRemarkEditEntityDto.getRepeatList());
        if (ponabRemark.isRepeatable()) {
            repeatComboBox.getSelectionModel().select("+");
        } else {
            repeatComboBox.getSelectionModel().select("-");
        }
    }

    @FXML
    public void sectorOnChangeListener(ActionEvent actionEvent) {
        stageComboBox.setValue(null);
        stageComboBox.getItems().setAll(sectorComboBox.getSelectionModel().getSelectedItem().getStageList());

        inspectionComboBox.setValue(null);
        inspectionComboBox.getItems().setAll(inspectionTripService.getInspectionTripsBySector(sectorComboBox.getSelectionModel().getSelectedItem()));
    }

    @FXML
    public void stageSelectionListener(ActionEvent actionEvent) {
        ponabSystemsComboBox.setValue(null);
        ponabSystemsComboBox.getItems().setAll(ponabSystemService.getPonabSystemsByStage(ponabRemark.getPonabSystem().getStage()));

    }

    @FXML
    public void saveRemarkButton(ActionEvent actionEvent) {
        if (sectorComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.EDIT_REMARK_NULL_SECTOR);
        }
        if (stageComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.EDIT_REMARK_NULL_STAGE);
        }
        if (repeatComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.EDIT_REPEAT_NULL);
        }
        if (ponabSystemsComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.EDIT_SYSTEM_IS_NOT_SET);
        }
        if (inspectionComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.EDIT_INSPECTION_NULL);
        }
        if (ponabRemarkDatePicker.getValue() == null) {
            errorList.add(Constants.EDIT_REMARK_FORMATION_DATE_NULL);
        }
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            ponabRemark.setPonabSystem(ponabSystemsComboBox.getSelectionModel().getSelectedItem());
            ponabRemark.setCreationDate(Date.from(ponabRemarkDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            ponabRemark.setNote(noteTextArea.getText());
            ponabRemark.setInspectionTrip(inspectionComboBox.getSelectionModel().getSelectedItem());
            if (repeatComboBox.getSelectionModel().getSelectedIndex() == 0) {
                ponabRemark.setRepeatable(true);
            } else if (repeatComboBox.getSelectionModel().getSelectedIndex() == 1) {
                ponabRemark.setRepeatable(false);
            }
            ponabRemarkService.save(ponabRemark);
            ponabRemarkEditEntityDto.setPonabRemark(ponabRemark);
            cleanDtoSecondaryData();
            ((javafx.stage.Stage) sectorComboBox.getScene().getWindow()).close();
        }
    }

    @FXML
    public void resetEditButton(ActionEvent actionEvent) {
        cleanAllDtoData();
        ((javafx.stage.Stage) sectorComboBox.getScene().getWindow()).close();
    }

    private void cleanDtoSecondaryData() {
        ponabRemarkEditEntityDto.setNote(null);
        ponabRemarkEditEntityDto.setEditedEntityId(null);
        ponabRemarkEditEntityDto.setRepeatList(null);
        ponabRemarkEditEntityDto.setSectorList(null);
    }

    private void cleanAllDtoData() {
        ponabRemarkEditEntityDto.setPonabRemark(null);
        ponabRemarkEditEntityDto.setTableViewIndex(null);
        cleanDtoSecondaryData();
    }
}
