package com.uz.laboratory.statistical.controller.remark;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.als.AlsRemarkEditEntityDto;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.service.als.TrackCircuitService;
import com.uz.laboratory.statistical.service.remark.AlsRemarkService;
import com.uz.laboratory.statistical.service.trip.InspectionTripService;
import com.uz.laboratory.statistical.util.DtoUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;
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
public class AlsRemarkEditModalController implements Initializable {
    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public ComboBox<Stage> stageComboBox;
    @FXML
    public ComboBox<TrackCircuit> alsSystemsComboBox;
    @FXML
    public ComboBox repeatComboBox;
    @FXML
    public ComboBox<InspectionTrip> inspectionComboBox;

    @FXML
    public TextArea noteTextArea;
    @FXML
    public DatePicker alsRemarkDatePicker;

    private AlsRemark alsRemark;

    @Autowired
    private AlsRemarkEditEntityDto alsRemarkEditEntityDto;
    @Autowired
    private AlsRemarkService alsRemarkService;
    @Autowired
    private TrackCircuitService alsSystemService;
    @Autowired
    private InspectionTripService inspectionTripService;

    private List<String> errorList = new ArrayList<>();
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
    private StringConverter<InspectionTrip> inspectionTripConverter = new StringConverter<InspectionTrip>() {
        @Override
        public String toString(InspectionTrip object) {
            return DtoUtil.inspectionTripConverterTitleBuilder(object);
        }

        @Override
        public InspectionTrip fromString(String string) {
            return null;
        }
    };
    private StringConverter<TrackCircuit> alsSystemConverter = new StringConverter<TrackCircuit>() {
        @Override
        public String toString(TrackCircuit object) {
            return DtoUtil.alsSystemConverterTitleBuilder(object);
        }

        @Override
        public TrackCircuit fromString(String string) {
            return null;
        }
    };
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alsRemark = (AlsRemark) alsRemarkService.get(alsRemarkEditEntityDto.getEditedEntityId());
        alsRemarkDatePicker.setValue(LocalDateTime.ofInstant(Instant.ofEpochMilli(alsRemark.getCreationDate().getTime()), ZoneId.systemDefault()).toLocalDate());

        sectorComboBox.setConverter(sectorConverter);
        stageComboBox.setConverter(stageConverter);
        inspectionComboBox.setConverter(inspectionTripConverter);
        alsSystemsComboBox.setConverter(alsSystemConverter);
        sectorComboBox.getItems().setAll(alsRemarkEditEntityDto.getSectorList());
        sectorComboBox.getSelectionModel().select(alsRemark.getInspectionTrip().getTripSector());

        stageComboBox.getItems().setAll(sectorComboBox.getSelectionModel().getSelectedItem().getStageList());
        stageComboBox.getSelectionModel().select(alsRemark.getTrackCircuit().getStage());

        inspectionComboBox.getItems().setAll(inspectionTripService.getInspectionTripsBySector(alsRemark.getInspectionTrip().getTripSector()));
        inspectionComboBox.getSelectionModel().select(alsRemark.getInspectionTrip());

        alsSystemsComboBox.getItems().setAll(alsSystemService.getAlsSystemsByStage(alsRemark.getTrackCircuit().getStage()));
        alsSystemsComboBox.getSelectionModel().select(alsRemark.getTrackCircuit());

        noteTextArea.setText(alsRemarkEditEntityDto.getNote());

        repeatComboBox.getItems().setAll(alsRemarkEditEntityDto.getRepeatList());
        if (alsRemark.isRepeatable()) {
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
        alsSystemsComboBox.setValue(null);
        alsSystemsComboBox.getItems().setAll(alsSystemService.getAlsSystemsByStage(alsRemark.getTrackCircuit().getStage()));
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
        if (alsSystemsComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.EDIT_SYSTEM_IS_NOT_SET);
        }
        if (inspectionComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.EDIT_INSPECTION_NULL);
        }
        if (alsRemarkDatePicker.getValue() == null) {
            errorList.add(Constants.EDIT_REMARK_FORMATION_DATE_NULL);
        }
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            alsRemark.setTrackCircuit(alsSystemsComboBox.getSelectionModel().getSelectedItem());
            alsRemark.setCreationDate(Date.from(alsRemarkDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            alsRemark.setNote(noteTextArea.getText());
            alsRemark.setInspectionTrip(inspectionComboBox.getSelectionModel().getSelectedItem());
            if (repeatComboBox.getSelectionModel().getSelectedIndex() == 0) {
                alsRemark.setRepeatable(true);
            } else if (repeatComboBox.getSelectionModel().getSelectedIndex() == 1) {
                alsRemark.setRepeatable(false);
            }
            alsRemarkService.save(alsRemark);
            alsRemarkEditEntityDto.setAlsRemark(alsRemark);
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
        alsRemarkEditEntityDto.setNote(null);
        alsRemarkEditEntityDto.setEditedEntityId(null);
        alsRemarkEditEntityDto.setRepeatList(null);
        alsRemarkEditEntityDto.setSectorList(null);
    }

    private void cleanAllDtoData() {
        alsRemarkEditEntityDto.setAlsRemark(null);
        alsRemarkEditEntityDto.setTableViewIndex(null);
        cleanDtoSecondaryData();
    }
}
