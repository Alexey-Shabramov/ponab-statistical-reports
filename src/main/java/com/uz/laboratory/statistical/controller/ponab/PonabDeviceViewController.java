package com.uz.laboratory.statistical.controller.ponab;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.ponab.PonabSystemDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class PonabDeviceViewController implements Initializable {
    @FXML
    public Button ponabDeviceViewCloseButton;
    @FXML
    public TreeView ponabDeviceTreeView;

    @Autowired
    private PonabSystemDto ponabSystemDto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> treeRootItem = new TreeItem<>(Constants.PONAB_DEVICE_NUMBER + ponabSystemDto.getId());

        TreeItem<String> stageRootItem = new TreeItem<>(Constants.PONAB_DEVICE_STAGE_TITLE);
        TreeItem<String> stageItem = new TreeItem<>(ponabSystemDto.getStage().getName());
        stageRootItem.getChildren().addAll(stageItem);

        TreeItem<String> titleRootItem = new TreeItem<>(Constants.PONAB_DEVICE_TITLE);
        TreeItem<String> noteItem = new TreeItem<>(ponabSystemDto.getTitle());
        titleRootItem.getChildren().addAll(noteItem);

        TreeItem<String> ponabSystemOptionItem = new TreeItem<>(Constants.PONAB_DEVICE_OPTION);
        TreeItem<String> ponabSystemTitleItem = new TreeItem<>(ponabSystemDto.getOption());
        ponabSystemOptionItem.getChildren().addAll(ponabSystemTitleItem);

        TreeItem<String> ponabSystemLocationRootItem = new TreeItem<>(Constants.PONAB_DEVICE_LOCATION);
        TreeItem<String> ponabSystemLocationItemItem = new TreeItem<>(ponabSystemDto.getLocation());
        ponabSystemLocationRootItem.getChildren().addAll(ponabSystemLocationItemItem);

        TreeItem<String> ponabSystemDirectionRootItem = new TreeItem<>(Constants.PONAB_DEVICE_DIRECTION_OF_MOVEMENT);
        TreeItem<String> ponabSystemDirectionItem = new TreeItem<>(ponabSystemDto.isEvenDirectionOfMovement() ? Constants.EVEN : Constants.UNEVEN);
        ponabSystemDirectionRootItem.getChildren().addAll(ponabSystemDirectionItem);

        TreeItem<String> speachInformerRootItem = new TreeItem<>(Constants.PONAB_DEVICE_SPEACH_INFORMER);
        TreeItem<String> speachInformerItem = new TreeItem<>(ponabSystemDto.isSpeachInformer() ? Constants.PONAB_DEVICE_SPEACH_INFORMER_TRUE : Constants.PONAB_DEVICE_SPEACH_INFORMER_FALSE);
        speachInformerRootItem.getChildren().addAll(speachInformerItem);

        treeRootItem.getChildren().addAll(
                stageRootItem,
                titleRootItem,
                ponabSystemOptionItem,
                stageRootItem,
                ponabSystemLocationRootItem,
                ponabSystemDirectionRootItem,
                speachInformerRootItem);

        treeRootItem.setExpanded(true);
        stageRootItem.setExpanded(true);
        titleRootItem.setExpanded(true);
        ponabSystemOptionItem.setExpanded(true);
        stageRootItem.setExpanded(true);
        ponabSystemLocationRootItem.setExpanded(true);
        ponabSystemDirectionRootItem.setExpanded(true);
        speachInformerRootItem.setExpanded(true);

        ponabDeviceTreeView.setRoot(treeRootItem);
    }

    @FXML
    public void closeButtonListener(ActionEvent actionEvent) {
        ((javafx.stage.Stage) ponabDeviceViewCloseButton.getScene().getWindow()).close();
    }
}
