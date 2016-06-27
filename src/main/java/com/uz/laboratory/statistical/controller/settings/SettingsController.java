package com.uz.laboratory.statistical.controller.settings;

import com.uz.laboratory.statistical.util.GitUtil;
import com.uz.laboratory.statistical.util.fx.UpdateGuiUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class SettingsController implements Initializable {
    @FXML
    public Button updateDatabaseButton;

    @Autowired
    private UpdateGuiUtil updateGuiUtil;
    @Autowired
    private GitUtil gitUtil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void updateDatabaseButtonListener(ActionEvent actionEvent) {
        try {
            gitUtil.commitAndPush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateCurrentDatabaseButtonListener(ActionEvent actionEvent) {
        try {
            gitUtil.pullAndMerge();
        } catch (GitAPIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
