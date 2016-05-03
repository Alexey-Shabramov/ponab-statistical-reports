package com.uz.laboratory.statistical.controller.remark.als;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.als.AlsRemarkDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ResourceBundle;

@Controller
public class AlsRemarkViewController implements Initializable {
    @FXML
    public Button alsRemarkViewCloseButton;
    @FXML
    public TreeView alsRemarkTreeView;

    @Autowired
    private AlsRemarkDto alsRemarkDto;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> treeRootItem = new TreeItem<>(Constants.REMARK_VIEW_TREE_TITLE + alsRemarkDto.getId());

        TreeItem<String> dateRootItem = new TreeItem<>(Constants.REMARK_VIEW_DATE_TITLE);

        TreeItem<String> dateItem = new TreeItem<>(alsRemarkDto.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(new DateTimeFormatterBuilder().appendPattern("dd.MM.yyyy").toFormatter()));
        dateRootItem.getChildren().addAll(dateItem);

        TreeItem<String> sectorRootItem = new TreeItem<>(Constants.REMARK_VIEW_SECTOR_TITLE);
        TreeItem<String> sectorItem = new TreeItem<>(alsRemarkDto.getInspectionTrip().getTripSector().getTitle());
        sectorRootItem.getChildren().addAll(sectorItem);

        TreeItem<String> stageRootItem = new TreeItem<>(Constants.REMARK_VIEW_STAGE_TITLE);
        TreeItem<String> stageItem = new TreeItem<>(alsRemarkDto.getTrackCircuit().getStage().getName());
        stageRootItem.getChildren().addAll(stageItem);

        TreeItem<String> noteRootItem = new TreeItem<>(Constants.REMARK_VIEW_NOTE_TITLE);
        TreeItem<String> noteItem = new TreeItem<>(alsRemarkDto.getNote());
        noteRootItem.getChildren().addAll(noteItem);

        TreeItem<String> alsSystemRootItem = new TreeItem<>(Constants.REMARK_VIEW_ALS_TRACK_CIRCUIT_TITLE);

        TreeItem<String> alsSystemTitleRootItem = new TreeItem<>(Constants.REMARK_VIEW_TRACK_CIRCUIT_NAME);
        TreeItem<String> ponabSystemTitleItem = new TreeItem<>(alsRemarkDto.getTrackCircuit().getName());
        alsSystemTitleRootItem.getChildren().addAll(ponabSystemTitleItem);

        TreeItem<String> alsSystemTypeRootItem = new TreeItem<>(Constants.REMARK_TRACK_CIRCUIT_TYPE);
        TreeItem<String> alsSystemTypeItem = new TreeItem<>(alsRemarkDto.getTrackCircuit().isStationalCircuit() ? Constants.STATIONAL_TRACK_CIRCUIT : Constants.STAGE_TRACK_CIRCUIT);
        alsSystemTypeRootItem.getChildren().addAll(alsSystemTypeItem);
        alsSystemRootItem.getChildren().addAll(alsSystemTitleRootItem, alsSystemTypeRootItem);

        TreeItem<String> directionOfMovementRootItem = new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_DIRECTION);
        TreeItem<String> directionOfMovementItem = alsRemarkDto.getTrackCircuit().isEven() ? new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_DIRECTION_EVEN) : new TreeItem<>(Constants.REMARK_VIEW_SYSTEM_DIRECTION_UNEVEN);
        directionOfMovementRootItem.getChildren().addAll(directionOfMovementItem);

        TreeItem<String> repeatRootItem = new TreeItem<>(Constants.REMARK_VIEW_REPEAT_TITLE);
        TreeItem<String> repeatItem = new TreeItem<>(alsRemarkDto.getRepeatable() ? Constants.REMARK_VIEW_REPEAT_TRUE : Constants.REMARK_VIEW_REPEAT_FALSE);
        repeatRootItem.getChildren().addAll(repeatItem);

        treeRootItem.getChildren().addAll(
                dateRootItem,
                repeatRootItem,
                sectorRootItem,
                stageRootItem,
                noteRootItem,
                alsSystemRootItem,
                directionOfMovementRootItem);
        alsRemarkTreeView.setRoot(treeRootItem);
    }

    @FXML
    public void closeButtonListener(ActionEvent actionEvent) {
        dozerBeanMapper.map(new AlsRemarkDto(), alsRemarkDto, Constants.CLEAN_ALS_REMARK_DTO);
        ((javafx.stage.Stage) alsRemarkViewCloseButton.getScene().getWindow()).close();
    }
}
