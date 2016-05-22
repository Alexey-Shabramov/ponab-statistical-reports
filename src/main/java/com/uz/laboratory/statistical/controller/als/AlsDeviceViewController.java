package com.uz.laboratory.statistical.controller.als;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.DirectionsOfMovement;
import com.uz.laboratory.statistical.dict.TrackCircuitTypes;
import com.uz.laboratory.statistical.dto.als.AlsSystemDto;
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
public class AlsDeviceViewController implements Initializable {
    @FXML
    public Button alsDeviceViewCloseButton;
    @FXML
    public TreeView alsDeviceTreeView;

    @Autowired
    private AlsSystemDto alsSystemDto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> treeRootItem = new TreeItem<>(Constants.ALS_DEVICE_NAME + alsSystemDto.getId());

        TreeItem<String> stageRootItem = new TreeItem<>(Constants.ALS_DEVICE_STAGE_OR_STATION);
        TreeItem<String> stageOrStationItem = new TreeItem<>(alsSystemDto.isStationalCircuit() ? alsSystemDto.getStation().getName() : alsSystemDto.getStage().getName());
        stageRootItem.getChildren().addAll(stageOrStationItem);

        TreeItem<String> systemTitleRootItem = new TreeItem<>(Constants.ALS_DEVICE_TITLE);
        TreeItem<String> systemTitleItem = new TreeItem<>(alsSystemDto.getName());
        systemTitleRootItem.getChildren().addAll(systemTitleItem);

        TreeItem<String> alsSystemOptionRootItem = new TreeItem<>(Constants.ALS_DEVICE_TYPE);
        TreeItem<String> alsSystemOptionItem = new TreeItem<>(alsSystemDto.isStationalCircuit() ? TrackCircuitTypes.STATION.toString() : TrackCircuitTypes.STAGE.toString());
        alsSystemOptionRootItem.getChildren().addAll(alsSystemOptionItem);

        TreeItem<String> alsSystemDirectionRootItem = new TreeItem<>(Constants.ALS_DEVICE_DIRECTION_OF_MOVEMENT);
        TreeItem<String> alsSystemDirectionItem = new TreeItem<>(alsSystemDto.isEven() ? DirectionsOfMovement.EVEN.toString() : DirectionsOfMovement.UNEVEN.toString());
        alsSystemDirectionRootItem.getChildren().addAll(alsSystemDirectionItem);

        TreeItem<String> picketRootItem = new TreeItem<>(Constants.ALS_DEVICE_PICKET);
        TreeItem<String> picketItem = new TreeItem<>(alsSystemDto.getPicket() != null ? alsSystemDto.getPicket().toString() : "-");
        picketRootItem.getChildren().addAll(picketItem);

        treeRootItem.getChildren().addAll(
                stageRootItem,
                systemTitleRootItem,
                alsSystemOptionRootItem,
                alsSystemDirectionRootItem,
                picketRootItem);

        treeRootItem.setExpanded(true);
        stageRootItem.setExpanded(true);
        systemTitleRootItem.setExpanded(true);
        alsSystemOptionRootItem.setExpanded(true);
        alsSystemDirectionRootItem.setExpanded(true);
        picketRootItem.setExpanded(true);

        alsDeviceTreeView.setRoot(treeRootItem);
    }

    public void closeButtonListener(ActionEvent actionEvent) {
        ((javafx.stage.Stage) alsDeviceViewCloseButton.getScene().getWindow()).close();
    }
}
