package com.uz.laboratory.statistical.controller.remark.ponab;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.ponab.PonabRemarkDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ResourceBundle;

@Controller
public class PonabRemarkViewController implements Initializable {
    @FXML
    public Button ponabRemarkViewCloseButton;
    @FXML
    public TreeView ponabRemarkTreeView;

    @Autowired
    private PonabRemarkDto ponabRemarkDto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTreeView();
    }

    private void initTreeView() {
        TreeItem<String> treeRootItem = new TreeItem<>(Constants.REMARK_VIEW_TREE_TITLE + ponabRemarkDto.getId());

        TreeItem<String> dateRootItem = new TreeItem<>(Constants.REMARK_VIEW_DATE_TITLE);
        TreeItem<String> dateItem = new TreeItem<>(ponabRemarkDto.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(new DateTimeFormatterBuilder().appendPattern("dd.MM.yyyy").toFormatter()));
        dateRootItem.getChildren().addAll(dateItem);

        TreeItem<String> sectorRootItem = new TreeItem<>(Constants.REMARK_VIEW_SECTOR_TITLE);
        TreeItem<String> sectorItem = new TreeItem<>(ponabRemarkDto.getInspectionTrip().getTripSector().getTitle());
        sectorRootItem.getChildren().addAll(sectorItem);

        TreeItem<String> stageRootItem = new TreeItem<>(Constants.REMARK_VIEW_STAGE_TITLE);
        TreeItem<String> stageItem = new TreeItem<>(ponabRemarkDto.getPonabSystem().getStage().getName());
        stageRootItem.getChildren().addAll(stageItem);

        TreeItem<String> noteRootItem = new TreeItem<>(Constants.REMARK_VIEW_NOTE_TITLE);
        TreeItem<String> noteItem = new TreeItem<>(ponabRemarkDto.getNote());
        noteRootItem.getChildren().addAll(noteItem);

        TreeItem<String> ponabSystemItem = new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_TITLE);
        TreeItem<String> ponabSystemTitleRootItem = new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_NAME);
        TreeItem<String> ponabSystemTitleItem = new TreeItem<>(ponabRemarkDto.getPonabSystem().getTitle());
        ponabSystemTitleRootItem.getChildren().addAll(ponabSystemTitleItem);

        TreeItem<String> ponabSystemOptionRootItem = new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_OPTION);
        TreeItem<String> ponabSystemOptionItem = new TreeItem<>(ponabRemarkDto.getPonabSystem().getOption());
        ponabSystemOptionRootItem.getChildren().addAll(ponabSystemOptionItem);

        TreeItem<String> ponabSystemSpeachInfoRootItem = new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_INFORMER);
        TreeItem<String> ponabSystemSpeachInfoItem = new TreeItem<>(ponabRemarkDto.getPonabSystem().isSpeachInformer() ? Constants.REMARK_VIEW_SYSTEM_INFORMER_TRUE : Constants.REMARK_VIEW_SYSTEM_INFORMER_FALSE);
        ponabSystemSpeachInfoRootItem.getChildren().addAll(ponabSystemSpeachInfoItem);
        ponabSystemItem.getChildren().addAll(ponabSystemTitleRootItem, ponabSystemOptionRootItem, ponabSystemSpeachInfoRootItem);

        TreeItem<String> directionOfMovementRootItem = new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_DIRECTION);
        TreeItem<String> directionOfMovementItem = ponabRemarkDto.getEven() ? new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_DIRECTION_EVEN) : new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_DIRECTION_UNEVEN);
        directionOfMovementRootItem.getChildren().addAll(directionOfMovementItem);

        TreeItem<String> repeatRootItem = new TreeItem<>(Constants.REMARK_VIEW_REPEAT_TITLE);
        TreeItem<String> repeatItem = new TreeItem<>(ponabRemarkDto.getRepeatable() ? Constants.REMARK_VIEW_REPEAT_TRUE : Constants.REMARK_VIEW_REPEAT_FALSE);
        repeatRootItem.getChildren().addAll(repeatItem);

        treeRootItem.getChildren().addAll(
                dateRootItem,
                repeatRootItem,
                sectorRootItem,
                stageRootItem,
                noteRootItem,
                ponabSystemItem,
                directionOfMovementRootItem);
        ponabRemarkTreeView.setRoot(treeRootItem);
    }

    @FXML
    public void closeButtonListener(ActionEvent actionEvent) {
        ((javafx.stage.Stage) ponabRemarkViewCloseButton.getScene().getWindow()).close();
    }
}
