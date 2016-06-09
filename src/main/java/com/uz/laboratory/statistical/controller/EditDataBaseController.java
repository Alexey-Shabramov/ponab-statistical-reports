package com.uz.laboratory.statistical.controller;

import com.uz.laboratory.statistical.dict.*;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.service.als.TrackCircuitService;
import com.uz.laboratory.statistical.service.ponab.PonabSystemService;
import com.uz.laboratory.statistical.service.remark.AlsRemarkService;
import com.uz.laboratory.statistical.service.remark.PonabRemarkService;
import com.uz.laboratory.statistical.service.trip.InspectionTripService;
import com.uz.laboratory.statistical.util.InitComboBoxesUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.ComboBoxUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class EditDataBaseController implements Initializable {
    @FXML
    public ComboBox<Systems> remarkDeviceTypeComboBox;
    @FXML
    public ComboBox remarkStageOrStationComboBox;
    @FXML
    public ComboBox<Sector> remarkSectorComboBox;
    @FXML
    public ComboBox remarkChosenSystemsComboBox;
    @FXML
    public ComboBox<InspectionTrip> remarkInspectionComboBox;
    @FXML
    public ComboBox<RemarkRepeat> remarkRepeatComboBox;
    @FXML
    public TextArea remarkNoteTextArea;
    @FXML
    public DatePicker remarkDatePicker;

    @FXML
    public ComboBox<PonabSystem> ponabDeviceComboBox;
    @FXML
    public Button editPonabDeviceButton;
    @FXML
    public Button addPonabDeviceButton;
    @FXML
    public Button addAlsDeviceButton;
    @FXML
    public Button editAlsDeviceButton;
    @FXML
    public ComboBox<TrackCircuit> alsDeviceComboBox;
    @FXML
    public ComboBox<Station> stationComboBox;
    @FXML
    public Button editStationButton;
    @FXML
    public Button addStationButton;
    @FXML
    public Button addStageButton;
    @FXML
    public Button editStageEditButton;
    @FXML
    public ComboBox<Stage> stageComboBox;
    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public Button editSectorButton;
    @FXML
    public Button addSectorButton;
    @FXML
    public Button addVagonLaboratoryButton;
    @FXML
    public Button editVagonLaboratoryButton;
    @FXML
    public ComboBox<VagonLaboratory> vagonLaboratoryComboBox;
    @FXML
    public ComboBox<CommunicationDistance> communicationDistanceComboBox;
    @FXML
    public Button editCommunicationDistanceButton;
    @FXML
    public Button addCommunicationButtonButton;
    @FXML
    public ComboBox alsOrPomabDeviceComboBox;
    @FXML
    public ComboBox<DirectionsOfMovement> remarkDirectionOfMovement;

    private List<String> errorList = new ArrayList<>();

    @Autowired
    private InitComboBoxesUtil initComboBoxesUtil;
    @Autowired
    private InspectionTripService inspectionTripService;
    @Autowired
    private PonabSystemService ponabSystemService;
    @Autowired
    private TrackCircuitService trackCircuitService;
    @Autowired
    private PonabRemarkService ponabRemarkService;
    @Autowired
    private AlsRemarkService alsRemarkService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initRemarkComboBoxes();
    }

    @FXML
    public void resetEditButton(ActionEvent actionEvent) {
    }

    @FXML
    public void addNewRemarkButton(ActionEvent actionEvent) {
        if (remarkDeviceTypeComboBox.getSelectionModel().getSelectedItem() != null) {
            if (remarkInspectionComboBox.getSelectionModel().getSelectedItem() == null)
                errorList.add(Constants.REMARK_CREATION_TRIP_NULL);
            if (remarkDatePicker.getValue() == null) errorList.add(Constants.REMARK_CREATION_DATE_NULL);
            if (remarkRepeatComboBox.getSelectionModel().getSelectedItem() == null)
                errorList.add(Constants.REMARK_CREATION_REPEAT_NULL);
            if (alsOrPomabDeviceComboBox.getSelectionModel().getSelectedItem() == null)
                errorList.add(Constants.REMARK_CREATION_SYSTEM_NULL);
            if (remarkDirectionOfMovement.getSelectionModel().getSelectedItem() == null)
                errorList.add(Constants.REMARK_CREATION_DIRECTION_OF_MOVEMENT_NULL);
            if (remarkDeviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
                checkAndCreateNewAlsRemark();
            } else {
                checkAndCreateNewPonabRemark();
            }
        }
    }

    @FXML
    public void remarkStageOrStationComboBoxListener(ActionEvent actionEvent) {
        alsOrPomabDeviceComboBox.setValue(null);
        alsOrPomabDeviceComboBox.setConverter(null);
        if (remarkStageOrStationComboBox.getSelectionModel().getSelectedItem() != null) {
            if (remarkDeviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
                /**
                 * В АЛС
                 */
                alsOrPomabDeviceComboBox.setConverter(ComboBoxUtil.alsSystemConverter);
                if (remarkChosenSystemsComboBox.getSelectionModel().getSelectedIndex() == 0) {
                    alsOrPomabDeviceComboBox.getItems().setAll(trackCircuitService.getAlsSystemsByStage((Stage) remarkStageOrStationComboBox.getSelectionModel().getSelectedItem()));
                } else {
                    alsOrPomabDeviceComboBox.getItems().setAll(trackCircuitService.getAlsSystemsByStation((Station) remarkStageOrStationComboBox.getSelectionModel().getSelectedItem()));
                }
            } else {
                /**
                 * В ПАВПБ
                 */
                alsOrPomabDeviceComboBox.getItems().clear();
                alsOrPomabDeviceComboBox.setConverter(ComboBoxUtil.ponabSystemConverter);
                alsOrPomabDeviceComboBox.getItems().setAll(ponabSystemService.getPonabSystemsByStage((Stage) remarkStageOrStationComboBox.getSelectionModel().getSelectedItem()));
            }
            alsOrPomabDeviceComboBox.setDisable(alsOrPomabDeviceComboBox.getItems().isEmpty() ? true : false);
        }
    }

    @FXML
    public void remarkSectorOnChangeListener(ActionEvent actionEvent) {
        remarkStageOrStationComboBox.setValue(null);
        if (remarkSectorComboBox.getSelectionModel().getSelectedItem() != null) {
            remarkStageOrStationComboBox.setDisable(false);
            if (remarkDeviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
                if (remarkChosenSystemsComboBox.getSelectionModel().getSelectedIndex() == 0) {
                    remarkStageOrStationComboBox.setConverter(ComboBoxUtil.stageConverter);
                    remarkStageOrStationComboBox.getItems().setAll(remarkSectorComboBox.getSelectionModel().getSelectedItem().getStageList());
                } else {
                    remarkStageOrStationComboBox.setConverter(ComboBoxUtil.stationConverter);
                    remarkStageOrStationComboBox.getItems().setAll(ComboBoxUtil.getStationListBySector(remarkSectorComboBox.getSelectionModel().getSelectedItem()));
                }
            } else {
                remarkStageOrStationComboBox.setConverter(ComboBoxUtil.stageConverter);
                remarkStageOrStationComboBox.getItems().setAll(remarkSectorComboBox.getSelectionModel().getSelectedItem().getStageList());
            }
            remarkInspectionComboBox.setValue(null);
            remarkInspectionComboBox.getItems().setAll(inspectionTripService.getInspectionTripsBySector(remarkSectorComboBox.getSelectionModel().getSelectedItem()));
            remarkInspectionComboBox.setDisable(remarkInspectionComboBox.getItems().isEmpty() ? true : false);

            remarkDirectionOfMovement.setValue(null);
            remarkDirectionOfMovement.getItems().setAll(DirectionsOfMovement.values());
            remarkDirectionOfMovement.setDisable(false);

            alsOrPomabDeviceComboBox.setDisable(true);
            alsOrPomabDeviceComboBox.setValue(null);

            remarkNoteTextArea.setDisable(false);
            remarkRepeatComboBox.setDisable(false);
            remarkDatePicker.setDisable(false);
        }
    }

    @FXML
    public void remarkDeviceTypeComboBoxListener(ActionEvent actionEvent) {
        remarkSectorComboBox.setDisable(true);
        remarkSectorComboBox.setValue(null);
        remarkDirectionOfMovement.setValue(null);
        remarkDirectionOfMovement.setDisable(true);
        remarkStageOrStationComboBox.setDisable(true);
        remarkStageOrStationComboBox.setValue(null);
        alsOrPomabDeviceComboBox.setValue(null);
        alsOrPomabDeviceComboBox.setDisable(true);
        remarkRepeatComboBox.setDisable(true);
        remarkRepeatComboBox.setValue(null);
        remarkDatePicker.setDisable(true);
        remarkDatePicker.setValue(null);
        remarkNoteTextArea.setDisable(true);
        remarkNoteTextArea.setText(null);
        remarkRepeatComboBox.setDisable(true);
        remarkRepeatComboBox.setValue(null);
        remarkInspectionComboBox.setDisable(true);
        remarkInspectionComboBox.setValue(null);
        if (remarkDeviceTypeComboBox.getSelectionModel().getSelectedItem() != null) {
            remarkChosenSystemsComboBox.setValue(null);
            if (remarkDeviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
                remarkChosenSystemsComboBox.getItems().setAll(TrackCircuitTypes.values());
            } else {
                remarkChosenSystemsComboBox.getItems().setAll(PonabSystems.values());
            }
            remarkChosenSystemsComboBox.setDisable(false);
        }
    }

    @FXML
    public void remarkChosenSystemsComboBoxListener(ActionEvent actionEvent) {
        remarkSectorComboBox.setValue(null);
        remarkSectorComboBox.setDisable(false);
        remarkInspectionComboBox.setValue(null);
        remarkInspectionComboBox.setDisable(true);
        alsOrPomabDeviceComboBox.setValue(null);
        alsOrPomabDeviceComboBox.setDisable(true);
        if (remarkChosenSystemsComboBox.getSelectionModel().getSelectedItem() != null) {
            remarkSectorComboBox.getItems().setAll(remarkDeviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0
                    ? ComboBoxUtil.getSectorListByAlsDevice(InitComboBoxesUtil.trackCircuitList, remarkChosenSystemsComboBox.getSelectionModel().getSelectedIndex() != 0)
                    : ComboBoxUtil.getSectorListByPonabDevice(InitComboBoxesUtil.ponabSystemList, remarkChosenSystemsComboBox.getSelectionModel().getSelectedItem().toString()));
        }
        remarkStageOrStationComboBox.setValue(null);
        remarkStageOrStationComboBox.setDisable(true);
    }

    private void initRemarkComboBoxes() {
        remarkDeviceTypeComboBox.getItems().setAll(Systems.values());
        remarkSectorComboBox.setConverter(ComboBoxUtil.sectorConverter);
        remarkSectorComboBox.setDisable(true);
        alsOrPomabDeviceComboBox.setDisable(true);
        remarkStageOrStationComboBox.setDisable(true);
        remarkChosenSystemsComboBox.setDisable(true);
        remarkDirectionOfMovement.setDisable(true);
        remarkInspectionComboBox.setConverter(ComboBoxUtil.inspectionTripConverter);
        remarkInspectionComboBox.setDisable(true);
        remarkRepeatComboBox.getItems().setAll(RemarkRepeat.values());
        remarkRepeatComboBox.setDisable(true);
        remarkNoteTextArea.setDisable(true);
        remarkDatePicker.setDisable(true);
    }

    private void checkAndCreateNewPonabRemark() {
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            PonabRemark ponabRemark = new PonabRemark();
            ponabRemark.setInspectionTrip(remarkInspectionComboBox.getSelectionModel().getSelectedItem());
            ponabRemark.setCreationDate(Date.from(remarkDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            ponabRemark.setRepeatable(remarkRepeatComboBox.getSelectionModel().getSelectedIndex() == 0);
            ponabRemark.setPonabSystem((PonabSystem) alsOrPomabDeviceComboBox.getSelectionModel().getSelectedItem());
            ponabRemark.setEven(remarkDirectionOfMovement.getSelectionModel().getSelectedIndex() == 0);
            ponabRemark.setNote(remarkNoteTextArea.getText());
            ponabRemarkService.save(ponabRemark);
            AlertGuiUtil.createSuccessAlert(Constants.REMARK_PONAB_CREATION_SUCCESS);
        }
    }

    private void checkAndCreateNewAlsRemark() {
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            AlsRemark alsRemark = new AlsRemark();
            alsRemark.setInspectionTrip(remarkInspectionComboBox.getSelectionModel().getSelectedItem());
            alsRemark.setCreationDate(Date.from(remarkDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            alsRemark.setRepeatable(remarkRepeatComboBox.getSelectionModel().getSelectedIndex() == 0);
            alsRemark.setTrackCircuit((TrackCircuit) alsOrPomabDeviceComboBox.getSelectionModel().getSelectedItem());
            alsRemark.setEven(remarkDirectionOfMovement.getSelectionModel().getSelectedIndex() == 0);
            alsRemark.setNote(remarkNoteTextArea.getText());
            alsRemarkService.save(alsRemark);
            AlertGuiUtil.createSuccessAlert(Constants.REMARK_ALS_CREATION_SUCCESS);
        }
    }
}
