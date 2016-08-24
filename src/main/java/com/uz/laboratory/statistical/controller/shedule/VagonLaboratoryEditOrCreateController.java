package com.uz.laboratory.statistical.controller.shedule;


import com.uz.laboratory.statistical.bean.init.InitComboBoxesUtil;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.trip.VagonLaboratoryEditOrCreateDto;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.service.trip.VagonLaboratoryService;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class VagonLaboratoryEditOrCreateController implements Initializable {
    @FXML
    public Button resetEditButton;
    @FXML
    public TextField vagonLaboratoryNameTextField;
    @Autowired
    private VagonLaboratoryEditOrCreateDto vagonLaboratoryEditOrCreateDto;
    @Autowired
    private VagonLaboratoryService vagonLaboratoryService;

    private VagonLaboratory vagonLaboratory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vagonLaboratory = vagonLaboratoryEditOrCreateDto.getVagonLaboratory();
        if (vagonLaboratory != null) {
            vagonLaboratoryNameTextField.setText(vagonLaboratory.getName());
        }
    }

    @FXML
    public void saveVagonLaboratoryListener(ActionEvent actionEvent) {
        if (!org.apache.commons.lang3.StringUtils.isNotBlank(vagonLaboratoryNameTextField.getText())) {
            AlertGuiUtil.createAlert(Constants.VAGON_LABORATORY_EDIT_NAME_NULL);
        } else {
            if (vagonLaboratory == null || vagonLaboratory.getId() < 0) {
                vagonLaboratory = new VagonLaboratory();
                InitComboBoxesUtil.vagonLaboratoryList.add(vagonLaboratory);
            }
            vagonLaboratory.setName(vagonLaboratoryNameTextField.getText());
            vagonLaboratoryService.save(vagonLaboratory);
            vagonLaboratoryEditOrCreateDto.setVagonLaboratory(vagonLaboratory);
            vagonLaboratory = null;
            ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
        }
    }

    @FXML
    public void resetEditButtonListener(ActionEvent actionEvent) {
        ((javafx.stage.Stage) resetEditButton.getScene().getWindow()).close();
    }
}