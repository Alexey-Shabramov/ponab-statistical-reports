package com.uz.laboratory.statistical.controller.ponab;

import com.uz.laboratory.statistical.dict.*;
import com.uz.laboratory.statistical.dto.ponab.PonabSystemEditEntityDto;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.service.ponab.PonabSystemService;
import com.uz.laboratory.statistical.util.InitComboBoxesUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.ComboBoxUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBoxes();
        if (ponabSystemEditDto.getEditedEntityId() != null) {
            ponabSystem = (PonabSystem) ponabSystemService.get(ponabSystemEditDto.getEditedEntityId());
            preparePonabDeviceEdit();
        } else if (ponabSystemEditDto.getPonabSystem() != null) {
            ponabSystem = ponabSystemEditDto.getPonabSystem();
            preparePonabDeviceEdit();
        }
    }

    private void preparePonabDeviceEdit() {
        sectorComboBox.getSelectionModel().select(ponabSystem.getSector());
        stageComboBox.getSelectionModel().select(ponabSystem.getStage());
        systemTypeComboBox.getSelectionModel().select(ponabSystem.getTitle());
        directionOfMovementComboBox.getSelectionModel().select(ponabSystem.isEvenDirectionOfMovement() ? DirectionsOfMovement.EVEN : DirectionsOfMovement.UNEVEN);
        speachInformerComboBox.getSelectionModel().select(ponabSystem.isSpeachInformer() ? SpeachInformer.ENABLED : SpeachInformer.DISABLED);
        locationTextField.setText(ponabSystem.getLocation());
        optionComboBox.getSelectionModel().select(ponabSystem.getOption());

    }

    private void initComboBoxes() {
        sectorComboBox.setConverter(ComboBoxUtil.sectorConverter);
        stageComboBox.setConverter(ComboBoxUtil.stageConverter);
        sectorComboBox.getItems().setAll(InitComboBoxesUtil.sectorList);
        systemTypeComboBox.getItems().addAll(PonabSystems.values());
        directionOfMovementComboBox.getItems().addAll(DirectionsOfMovement.values());
        optionComboBox.getItems().addAll(PonabOptions.values());
        speachInformerComboBox.getItems().addAll(SpeachInformer.values());
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
            if (ponabSystem == null || ponabSystem.getId() < 0) {
                ponabSystem = new PonabSystem();
                InitComboBoxesUtil.ponabSystemList.add(ponabSystem);
            }
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
        ponabSystem = null;
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
