package com.uz.laboratory.statistical.util.fx;


import com.uz.laboratory.statistical.controller.ponab.PonabDevicesController;
import com.uz.laboratory.statistical.controller.shedule.SheduleController;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.service.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;


public class ModalUtil {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private SpringFXMLLoader springFXMLLoader;

    public static void createInspectionEditModal(FXMLLoader fxmlLoader) {
        Stage inspectionModal = new Stage();
        inspectionModal.initModality(Modality.APPLICATION_MODAL);
        try {
            inspectionModal.setScene(new Scene(FXMLLoader.load(SheduleController.class.getResource(Constants.INSPECTION_EDIT_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        inspectionModal.showAndWait();
    }

    public static void createPlannedTripEditModal(FXMLLoader fxmlLoader) {
        Stage plannedTripModal = new Stage();
        plannedTripModal.initModality(Modality.APPLICATION_MODAL);
        try {
            plannedTripModal.setScene(new Scene(FXMLLoader.load(SheduleController.class.getResource(Constants.PLANNED_TRIP_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        plannedTripModal.showAndWait();
    }

    public static void createPonabDeviceEditModal(FXMLLoader fxmlLoader) {
        Stage ponabDeviceModal = new Stage();
        ponabDeviceModal.initModality(Modality.APPLICATION_MODAL);
        try {
            ponabDeviceModal.setScene(new Scene(FXMLLoader.load(PonabDevicesController.class.getResource(Constants.PONAB_DEVICE_EDIT_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ponabDeviceModal.showAndWait();
    }

    public static void createAlsDeviceEditModal(FXMLLoader fxmlLoader) {
        Stage alsDevicesModal = new Stage();
        alsDevicesModal.initModality(Modality.APPLICATION_MODAL);
        try {
            alsDevicesModal.setScene(new Scene(FXMLLoader.load(PonabDevicesController.class.getResource(Constants.ALS_DEVICE_EDIT_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        alsDevicesModal.showAndWait();
    }

    public void createEditRemarkModal() {
        Stage remarkEditModal = new Stage();
        remarkEditModal.initModality(Modality.APPLICATION_MODAL);
        try {
            remarkEditModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.REMARK_EDIT_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remarkEditModal.showAndWait();
    }

    public void createViewRemarkModal() {
        Stage remarkViewModal = new Stage();
        remarkViewModal.initModality(Modality.APPLICATION_MODAL);
        try {
            remarkViewModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.REMARK_VIEW_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remarkViewModal.showAndWait();
    }

    public void createRemarkDeletionConfirmModal() {
        Stage remarkDeletionConfirm = new Stage();
        remarkDeletionConfirm.initModality(Modality.APPLICATION_MODAL);
        try {
            remarkDeletionConfirm.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.VALIDATE_REMARK_DELETION_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remarkDeletionConfirm.showAndWait();
    }
}
