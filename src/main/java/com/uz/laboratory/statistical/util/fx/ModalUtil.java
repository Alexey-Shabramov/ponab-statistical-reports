package com.uz.laboratory.statistical.util.fx;


import com.uz.laboratory.statistical.controller.PonabDevicesController;
import com.uz.laboratory.statistical.controller.SheduleController;
import com.uz.laboratory.statistical.controller.StatisticsController;
import com.uz.laboratory.statistical.dict.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ModalUtil {
    public static void createRemarkModal() {
        Stage remarkEditModal = new Stage();
        remarkEditModal.initModality(Modality.APPLICATION_MODAL);
        try {
            remarkEditModal.setScene(new Scene(FXMLLoader.load(StatisticsController.class.getResource(Constants.REMARK_EDIT_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remarkEditModal.show();
    }

    public static void createInspectionModal() {
        Stage inspectionModal = new Stage();
        inspectionModal.initModality(Modality.APPLICATION_MODAL);
        try {
            inspectionModal.setScene(new Scene(FXMLLoader.load(SheduleController.class.getResource(Constants.INSPECTION_EDIT_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        inspectionModal.show();
    }

    public static void createPlannedTripModal() {
        Stage plannedTripModal = new Stage();
        plannedTripModal.initModality(Modality.APPLICATION_MODAL);
        try {
            plannedTripModal.setScene(new Scene(FXMLLoader.load(SheduleController.class.getResource(Constants.PLANNED_TRIP_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        plannedTripModal.show();
    }

    public static void createPonabDeviceModal() {
        Stage ponabDeviceModal = new Stage();
        ponabDeviceModal.initModality(Modality.APPLICATION_MODAL);
        try {
            ponabDeviceModal.setScene(new Scene(FXMLLoader.load(PonabDevicesController.class.getResource(Constants.PONAB_DEVICE_EDIT_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ponabDeviceModal.show();
    }

    public static void createAlsDeviceModal() {
        Stage alsDevicesModal = new Stage();
        alsDevicesModal.initModality(Modality.APPLICATION_MODAL);
        try {
            alsDevicesModal.setScene(new Scene(FXMLLoader.load(PonabDevicesController.class.getResource(Constants.ALS_DEVICE_EDIT_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        alsDevicesModal.show();
    }
}
