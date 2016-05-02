package com.uz.laboratory.statistical.controller.remark;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.RemarkTableListSaveDto;
import com.uz.laboratory.statistical.util.ReportUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class SaveRemarkTableController implements Initializable {
    @FXML
    public Button saveRemarkTableButton;
    @FXML
    public Button cancelSavingsButton;
    @FXML
    public Button chooseDirectoryButton;
    @FXML
    public TextField saveTableFolderPathTextField;
    @FXML
    public TextField newTextFileTextfield;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private RemarkTableListSaveDto remarkTableListSaveDto;

    private List<String> errorList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void saveRemarkTableButtonAction(ActionEvent actionEvent) {
        if (!StringUtils.isNotEmpty(saveTableFolderPathTextField.getText())) {
            errorList.add(Constants.REMARK_TABLE_SAVE_EMPTY_PATH);
        } else if (Files.notExists(Paths.get(saveTableFolderPathTextField.getText()))) {
            errorList.add(Constants.REMARK_TABLE_FOLDER_NOT_EXISTS);
        }
        if (!StringUtils.isNotBlank(newTextFileTextfield.getText())) {
            errorList.add(Constants.TEXTFIELD_IS_EMPTY);
        }
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            try {
                ReportUtil.createRemarkReport(remarkTableListSaveDto.getStatisticsRemarkTableDtos(), saveTableFolderPathTextField.getText(), newTextFileTextfield.getText());
                ((Stage) saveRemarkTableButton.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void cancelSavingsButtonListener(ActionEvent actionEvent) {
        ((Stage) cancelSavingsButton.getScene().getWindow()).close();
    }

    @FXML
    public void chooseDirectoryButtonListener(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(chooseDirectoryButton.getScene().getWindow());
        if (selectedDirectory == null) {
            saveTableFolderPathTextField.setText(Constants.REMARK_TABLE_SAVE_EMPTY_PATH);
        } else {
            saveTableFolderPathTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }
}
