package com.uz.laboratory.statistical.util.fx;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.DeleteEntityDto;
import com.uz.laboratory.statistical.dto.RemarkTableListSaveDto;
import com.uz.laboratory.statistical.dto.als.AlsRemarkDto;
import com.uz.laboratory.statistical.dto.als.AlsRemarkEditEntityDto;
import com.uz.laboratory.statistical.dto.als.AlsSystemDto;
import com.uz.laboratory.statistical.dto.location.CommunicationDistanceEditOrCreateDto;
import com.uz.laboratory.statistical.dto.location.SectorEditOrCreateDto;
import com.uz.laboratory.statistical.dto.location.StageEditOrCreateDto;
import com.uz.laboratory.statistical.dto.location.StationEditOrCreateDto;
import com.uz.laboratory.statistical.dto.ponab.PonabRemarkDto;
import com.uz.laboratory.statistical.dto.ponab.PonabSystemDto;
import com.uz.laboratory.statistical.dto.trip.InspectionTripDto;
import com.uz.laboratory.statistical.dto.trip.InspectionTripEditEntityDto;
import com.uz.laboratory.statistical.dto.trip.VagonLaboratoryEditOrCreateDto;
import com.uz.laboratory.statistical.service.SpringFXMLLoader;
import com.uz.laboratory.statistical.util.DtoUtil;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    @Autowired
    private AlsSystemDto alsSystemDto;
    @Autowired
    private InspectionTripDto inspectionTripDto;
    @Autowired
    private StationEditOrCreateDto stationEditOrCreateDto;
    @Autowired
    private CommunicationDistanceEditOrCreateDto communicationDistanceEditOrCreateDto;
    @Autowired
    private VagonLaboratoryEditOrCreateDto vagonLaboratoryEditOrCreateDto;
    @Autowired
    private StageEditOrCreateDto stageEditOrCreateDto;
    @Autowired
    private SectorEditOrCreateDto sectorEditOrCreateDto;
    @Autowired
    private InspectionTripEditEntityDto inspectionTripEditEntityDto;

    public void createTripViewModal() {
        Stage tripViewModal = new Stage();
        tripViewModal.initModality(Modality.APPLICATION_MODAL);
        try {
            tripViewModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.INSPECTION_TRIP_VIEW_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tripViewModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                DtoUtil.cleanInspectionDto(inspectionTripDto);
            });
        });
        tripViewModal.setTitle(Constants.TRIP_VIEW_MODAL_TITLE);
        tripViewModal.showAndWait();
    }

    public void createTripCreateOrEditModal() {
        Stage plannedTripModal = new Stage();
        plannedTripModal.initModality(Modality.APPLICATION_MODAL);
        try {
            plannedTripModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.TRIP_EDIT_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        plannedTripModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                inspectionTripEditEntityDto.setInspectionTrip(null);
            });
        });
        plannedTripModal.setTitle(Constants.TRIP_VIEW_TITLE);
        plannedTripModal.showAndWait();
    }

    public void createPonabDeviceEditOrCreateModal() {
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

    public void createAlsDeviceEditOrCreateModal() {
        Stage alsDeviceModal = new Stage();
        alsDeviceModal.initModality(Modality.APPLICATION_MODAL);
        try {
            alsDeviceModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.ALS_DEVICE_EDIT_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        alsDeviceModal.setTitle(Constants.ALS_DEVICE_EDIT_TITLE);
        alsDeviceModal.showAndWait();
    }

    public void createStationEditOrCreateModal() {
        Stage stationModal = new Stage();
        stationModal.initModality(Modality.APPLICATION_MODAL);
        try {
            stationModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.STATION_EDIT_OR_CREATE_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stationModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                stationEditOrCreateDto.setStation(null);
            });
        });
        stationModal.setTitle(Constants.STATION_MODAL_TITLE);
        stationModal.showAndWait();
    }

    public void createSectorEditOrCreateModal() {
        Stage sectorModal = new Stage();
        sectorModal.initModality(Modality.APPLICATION_MODAL);
        try {
            sectorModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.SECTOR_EDIT_OR_CREATE_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sectorModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                sectorEditOrCreateDto.setSector(null);
            });
        });
        sectorModal.setTitle(Constants.SECTOR_MODAL_TITLE);
        sectorModal.showAndWait();
    }

    public void createCommunicationDistanceEditOrCreateModal() {
        Stage communicationDistanceModal = new Stage();
        communicationDistanceModal.initModality(Modality.APPLICATION_MODAL);
        try {
            communicationDistanceModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.DISTANCE_EDIT_OR_CREATE_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        communicationDistanceModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                communicationDistanceEditOrCreateDto.setCommunicationDistance(null);
            });
        });
        communicationDistanceModal.setTitle(Constants.DISTANCE_MODAL_TITLE);
        communicationDistanceModal.showAndWait();
    }

    public void createVagonLaboratoryEditOrCreateModal() {
        Stage vagonLaboratoryModal = new Stage();
        vagonLaboratoryModal.initModality(Modality.APPLICATION_MODAL);
        try {
            vagonLaboratoryModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.LABORATORY_EDIT_OR_CREATE_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vagonLaboratoryModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                vagonLaboratoryEditOrCreateDto.setVagonLaboratory(null);
            });
        });
        vagonLaboratoryModal.setTitle(Constants.LABORATORY_MODAL_TITLE);
        vagonLaboratoryModal.showAndWait();
    }

    public void createStageEditOrCreateModal() {
        Stage stageModal = new Stage();
        stageModal.initModality(Modality.APPLICATION_MODAL);
        try {
            stageModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.STAGE_EDIT_OR_CREATE_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stageModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                stageEditOrCreateDto.setStage(null);
            });
        });
        stageModal.setTitle(Constants.STAGE_MODAL_TITLE);
        stageModal.showAndWait();
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
        remarkViewModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                dozerBeanMapper.map(new PonabRemarkDto(), ponabRemarkDto, Constants.CLEAN_PONAB_REMARK_DTO);
            });
        });
        remarkViewModal.setTitle(Constants.REMARK_PONAB_VIEW_MODAL_TITLE);
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
        deletionConfirm.setOnHiding(event -> {
            Platform.runLater(() -> {
                deleteEntityDto.setDeleteValidationValue(null);
            });
        });
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
        remarkTableSave.setOnHiding(event -> {
            Platform.runLater(() -> {
                remarkTableListSaveDto.setStatisticsRemarkTableDtos(null);
            });
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
        remarkEditModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                alsRemarkEditEntityDto.setAlsRemark(null);
                alsRemarkEditEntityDto.setTableViewIndex(null);
                alsRemarkEditEntityDto.setNote(null);
                alsRemarkEditEntityDto.setEditedEntityId(null);
                alsRemarkEditEntityDto.setRepeatList(null);
                alsRemarkEditEntityDto.setSectorList(null);
            });
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
        remarkViewModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                dozerBeanMapper.map(new AlsRemarkDto(), alsRemarkDto, Constants.CLEAN_ALS_REMARK_DTO);
            });
        });
        remarkViewModal.setTitle(Constants.REMARK_ALS_VIEW_MODAL_TITLE);
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
        ponabDeviceModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                dozerBeanMapper.map(new PonabSystemDto(), ponabSystemDto, Constants.CLEAN_PONAB_DEVICE_DTO);
            });
        });
        ponabDeviceModal.setTitle(Constants.PONAB_DEVICE_VIEW_TITLE);
        ponabDeviceModal.showAndWait();
    }

    public void createAlsDeviceViewModal() {
        Stage alsDeviceModal = new Stage();
        alsDeviceModal.initModality(Modality.APPLICATION_MODAL);
        try {
            alsDeviceModal.setScene(new Scene((Parent) context.getBean(SpringFXMLLoader.class).load(Constants.ALS_DEVICE_VIEW_MODAL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        alsDeviceModal.setOnHiding(event -> {
            Platform.runLater(() -> {
                dozerBeanMapper.map(new AlsSystemDto(), alsSystemDto, Constants.CLEAN_ALS_DEVICE_DTO);
            });
        });
        alsDeviceModal.setTitle(Constants.ALS_DEVICE_VIEW_TITLE);
        alsDeviceModal.showAndWait();
    }
}
