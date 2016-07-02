package com.uz.laboratory.statistical.controller.settings;

import com.uz.laboratory.statistical.app.PonabStatisticalReports;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.service.util.utilImpl.HibernateUtilServiceImpl;
import com.uz.laboratory.statistical.util.GitUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class SettingsController implements Initializable {
    @FXML
    public Button updateDatabaseButton;
    @FXML
    public Button updateCurrentDatabaseButton;

    @Autowired
    private GitUtil gitUtil;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private HibernateUtilServiceImpl hibernateUtilService;

    private List<String> errorList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void updateDatabaseButtonListener(ActionEvent actionEvent) {
        try {
            if (java.lang.Runtime.getRuntime().exec("ping www.github.com").waitFor() != 0) {
                AlertGuiUtil.createAlert(Constants.INTERNET_IS_NOT_AVAILABLE);
            } else {
                gitUtil.commitAndPush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateCurrentDatabaseButtonListener(ActionEvent actionEvent) {
        try {
            if (java.lang.Runtime.getRuntime().exec("ping www.github.com").waitFor() != 0) {
                AlertGuiUtil.createAlert(Constants.INTERNET_IS_NOT_AVAILABLE);
            } else {
                hibernateUtilService.shutdownDataBase();
                ((ConfigurableApplicationContext) applicationContext).close();
                gitUtil.pullAndMerge();
                PonabStatisticalReports.restartMainStage();
            }
        } catch (GitAPIException | IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
