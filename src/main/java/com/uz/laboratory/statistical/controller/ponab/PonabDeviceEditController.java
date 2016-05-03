package com.uz.laboratory.statistical.controller.ponab;

import com.uz.laboratory.statistical.dict.*;
import com.uz.laboratory.statistical.dto.ponab.PonabSystemEditEntityDto;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.service.ponab.PonabSystemService;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class PonabDeviceEditController implements Initializable {
    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public ComboBox<Stage> stageComboBox;
    @FXML
    public ComboBox systemTypeComboBox;
    @FXML
    public ComboBox<DirectionsOfMovement> directionOfMovementComboBox;
    @FXML
    public ComboBox optionComboBox;
    @FXML
    public ComboBox<SpeachInformer> speachInformerComboBox;
    @FXML
    public TextField locationTextField;
    @FXML
    public Button resetEditButton;

    @Autowired
    private PonabSystemEditEntityDto ponabSystemEditDto;

    @Autowired
    private PonabSystemService ponabSystemService;

    private List<String> errorList = new ArrayList<>();

    private PonabSystem ponabSystem;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ponabSystem = (PonabSystem) ponabSystemService.get(ponabSystemEditDto.getEditedEntityId());
        sectorComboBox.setConverter(sectorConverter);
        stageComboBox.setConverter(stageConverter);

        sectorComboBox.getItems().setAll(ponabSystemEditDto.getSectorList());
        sectorComboBox.getSelectionModel().select(ponabSystem.getSector());

        stageComboBox.getItems().setAll(sectorComboBox.getSelectionModel().getSelectedItem().getStageList());
        stageComboBox.getSelectionModel().select(ponabSystem.getStage());

        systemTypeComboBox.getItems().addAll(PonabSystems.values());
        systemTypeComboBox.getSelectionModel().select(ponabSystem.getTitle());

        directionOfMovementComboBox.getItems().addAll(DirectionsOfMovement.values());
        directionOfMovementComboBox.getSelectionModel().select(ponabSystem.isEvenDirectionOfMovement() ? DirectionsOfMovement.EVEN : DirectionsOfMovement.UNEVEN);

        optionComboBox.getItems().addAll(PonabOptions.values());
        optionComboBox.getSelectionModel().select(ponabSystem.getOption());

        speachInformerComboBox.getItems().addAll(SpeachInformer.values());
        speachInformerComboBox.getSelectionModel().select(ponabSystem.isSpeachInformer() ? SpeachInformer.ENABLED : SpeachInformer.DISABLED);
        locationTextField.setText(ponabSystem.getLocation());
    }

    @FXML
    public void savePonabDeviceListener(ActionEvent actionEvent) {
        if (stageComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.PONAB_DEVICE_STAGE_NULL);
        }
        if (directionOfMovementComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.PONAB_DEVICE_DIRECTION_OF_MOVEMENT_NULL);
        }
        if (systemTypeComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.PONAB_DEVICE_SYSTEM_TYPE_NULL);
        }
        if (optionComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.PONAB_DEVICE_OPTION_NULL);
        }
        if (speachInformerComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.PONAB_DEVICE_SPEACH_INFORMER_NULL);
        }
        if (!StringUtils.isNotEmpty(locationTextField.getText())) {
            errorList.add(Constants.PONAB_DEVICE_LOCATION_NULL);
        }
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            ponabSystem.setStage(stageComboBox.getSelectionModel().getSelectedItem());
            ponabSystem.setSpeachInformer(speachInformerComboBox.getSelectionModel().getSelectedIndex() == 0);
            ponabSystem.setLocation(locationTextField.getText());
            ponabSystem.setEvenDirectionOfMovement(directionOfMovementComboBox.getSelectionModel().getSelectedIndex() == 0);
            ponabSystem.setTitle(systemTypeComboBox.getSelectionModel().getSelectedItem().toString());
            ponabSystem.setSector(sectorComboBox.getSelectionModel().getSelectedItem());
            ponabSystem.setOption(optionComboBox.getSelectionModel().getSelectedItem().toString());
            ponabSystemService.save(ponabSystem);
            ponabSystemEditDto.setPonabSystem(ponabSystem);
            cleanDtoSecondaryData();
            ((javafx.stage.Stage) sectorComboBox.getScene().getWindow()).close();
        }
    }

    @FXML
    public void sectorChangeListener(ActionEvent actionEvent) {
        stageComboBox.setValue(null);
        stageComboBox.getItems().setAll(sectorComboBox.getSelectionModel().getSelectedItem().getStageList());
    }

    @FXML
    public void resetEditButtonListener(ActionEvent actionEvent) {
        cleanAllDtoData();
        ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
    }

    private void cleanDtoSecondaryData() {
        ponabSystemEditDto.setEditedEntityId(null);
        ponabSystemEditDto.setRepeatList(null);
        ponabSystemEditDto.setSectorList(null);
    }

    private void cleanAllDtoData() {
        ponabSystemEditDto.setPonabSystem(null);
        ponabSystemEditDto.setTableViewIndex(null);
        cleanDtoSecondaryData();
    }

}