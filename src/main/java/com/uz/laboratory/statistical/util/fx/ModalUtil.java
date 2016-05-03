package com.uz.laboratory.statistical.util.fx;


import com.uz.laboratory.statistical.controller.shedule.SheduleController;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.DeleteEntityDto;
import com.uz.laboratory.statistical.dto.RemarkTableListSaveDto;
import com.uz.laboratory.statistical.dto.als.AlsRemarkDto;
import com.uz.laboratory.statistical.dto.als.AlsRemarkEditEntityDto;
import com.uz.laboratory.statistical.dto.ponab.PonabRemarkDto;
import com.uz.laboratory.statistical.dto.ponab.PonabSystemDto;
import com.uz.laboratory.statistical.service.SpringFXMLLoader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;


public class ModalUtil {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private PonabRemarkDto ponabRemarkDto;
    @Autowired
    private AlsRemarkDto alsRemarkDto;
    @Autowired
    private RemarkTableListSaveDto remarkTableListSaveDto;
    @Autowired
    private PonabSystemDto ponabSystemDto;
    @Autowired
    private AlsRemarkEditEntityDto alsRemarkEditEntityDto;
    @Autowired
    private DeleteEntityDto deleteEntityDto;

    public static void createInspectionEditModal() {
        Stage inspectionModal = new Stage();
        inspectionModal.initModality(Modality.APPLICATION_MODAL);
        try {
            inspectionModal.setScene(new Scene(FXMLLoader.load(SheduleController.class.getResource(Constants.INSPECTION_EDIT_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        inspectionModal.showAndWait();
    }

    public static void createPlannedTripEditModal() {
        Stage plannedTripModal = new Stage();
        plannedTripModal.initModality(Modality.APPLICATION_MODAL);
        try {
            plannedTripModal.setScene(new Scene(FXMLLoader.load(SheduleController.class.getResource(Constants.PLANNED_TRIP_MODAL))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        plannedTripModal.showAndWait();
    }

    public void createPonabDeviceEditModal() {
        Stage ponabDeviceModal = new Stage();
        ponabDeviceModal.initModality(Modality.APPLICATION_MODAL);
        try {
            ponabDeviceModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.PONAB_DEVICE_EDIT_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ponabDeviceModal.setTitle(Constants.PONAB_DEVICE_EDIT_TITLE);
        ponabDeviceModal.showAndWait();
    }

    public void createPonabRemarkEditModal() {
        Stage remarkEditModal = new Stage();
        remarkEditModal.initModality(Modality.APPLICATION_MODAL);
        try {
            remarkEditModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.PONAB_REMARK_EDIT_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remarkEditModal.setTitle(Constants.REMARK_PONAB_EDIT_MODAL_TITLE);
        remarkEditModal.showAndWait();
    }

    public void createPonabRemarkViewModal() {
        Stage remarkViewModal = new Stage();
        remarkViewModal.initModality(Modality.APPLICATION_MODAL);
        try {
            remarkViewModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.PONAB_REMARK_VIEW_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remarkViewModal.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dozerBeanMapper.map(new PonabRemarkDto(), ponabRemarkDto, Constants.CLEAN_PONAB_REMARK_DTO);
                    }
                });
            }
        });
        remarkViewModal.showAndWait();
    }

    public void createDeletionConfirmModal() {
        Stage deletionConfirm = new Stage();
        deletionConfirm.initModality(Modality.APPLICATION_MODAL);
        try {
            deletionConfirm.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.VALIDATE_ENTITY_DELETION_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        deletionConfirm.setTitle(Constants.ENTITY_DELETION_MODAL_TITLE);
        deletionConfirm.showAndWait();
    }

    public void createRemarkTableSaveModal() {
        Stage remarkTableSave = new Stage();
        remarkTableSave.initModality(Modality.APPLICATION_MODAL);
        try {
            remarkTableSave.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.REMARK_TABLE_SAVE_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remarkTableSave.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        remarkTableListSaveDto.setStatisticsRemarkTableDtos(null);
                    }
                });
            }
        });
        remarkTableSave.setTitle(Constants.REMARK_TABLE_SAVE_MODAL_TITLE);
        remarkTableSave.showAndWait();
    }

    public void createAlsRemarkEditModal() {
        Stage remarkEditModal = new Stage();
        remarkEditModal.initModality(Modality.APPLICATION_MODAL);
        try {
            remarkEditModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.ALS_REMARK_EDIT_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remarkEditModal.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        alsRemarkEditEntityDto.setAlsRemark(null);
                        alsRemarkEditEntityDto.setTableViewIndex(null);
                        alsRemarkEditEntityDto.setNote(null);
                        alsRemarkEditEntityDto.setEditedEntityId(null);
                        alsRemarkEditEntityDto.setRepeatList(null);
                        alsRemarkEditEntityDto.setSectorList(null);
                    }
                });
            }
        });
        remarkEditModal.setTitle(Constants.REMARK_ALS_EDIT_MODAL_TITLE);
        remarkEditModal.showAndWait();
    }

    public void createAlsRemarkViewModal() {
        Stage remarkViewModal = new Stage();
        remarkViewModal.initModality(Modality.APPLICATION_MODAL);
        try {
            remarkViewModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.ALS_REMARK_VIEW_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remarkViewModal.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dozerBeanMapper.map(new AlsRemarkDto(), alsRemarkDto, Constants.CLEAN_ALS_REMARK_DTO);
                    }
                });
            }
        });
        remarkViewModal.showAndWait();
    }

    public void createPonabDeviceViewModal() {
        Stage ponabDeviceModal = new Stage();
        ponabDeviceModal.initModality(Modality.APPLICATION_MODAL);
        try {
            ponabDeviceModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.PONAB_DEVICE_VIEW_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ponabDeviceModal.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dozerBeanMapper.map(new PonabSystemDto(), ponabSystemDto, Constants.CLEAN_PONAB_DEVICE_DTO);
                    }
                });
            }
        });
        ponabDeviceModal.setTitle(Constants.PONAB_DEVICE_VIEW_TITLE);
        ponabDeviceModal.showAndWait();
    }
}
