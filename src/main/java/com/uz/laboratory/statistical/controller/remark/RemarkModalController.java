package com.uz.laboratory.statistical.controller.remark;

import com.uz.laboratory.statistical.dto.DeleteEntityDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class RemarkModalController implements Initializable {
    @FXML
    public Button deleteConfirmButton;
    @FXML
    public Button resetDeletionButton;
    @Autowired
    private DeleteEntityDto deleteEntityDto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void deleteConfirmButtonListener(ActionEvent actionEvent) {
        deleteEntityDto.setDeleteValidationValue(true);
        ((Stage) deleteConfirmButton.getScene().getWindow()).close();
    }

    @FXML
    public void resetDeletionButtonListener(ActionEvent actionEvent) {
        deleteEntityDto.setDeleteValidationValue(false);
        ((Stage) deleteConfirmButton.getScene().getWindow()).close();
    }
}
