package com.uz.laboratory.statistical.controller.remark;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.DirectionsOfMovement;
import com.uz.laboratory.statistical.dict.RemarkRepeat;
import com.uz.laboratory.statistical.dict.Systems;
import com.uz.laboratory.statistical.dto.DeleteEntityDto;
import com.uz.laboratory.statistical.dto.RemarkTableListSaveDto;
import com.uz.laboratory.statistical.dto.als.AlsRemarkDto;
import com.uz.laboratory.statistical.dto.als.AlsRemarkEditEntityDto;
import com.uz.laboratory.statistical.dto.ponab.PonabRemarkDto;
import com.uz.laboratory.statistical.dto.ponab.PonabRemarkEditEntityDto;
import com.uz.laboratory.statistical.dto.tableView.StatisticsRemarkTableDto;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;
import com.uz.laboratory.statistical.service.location.CommunicationDistanceService;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.service.remark.AlsRemarkService;
import com.uz.laboratory.statistical.service.remark.PonabRemarkService;
import com.uz.laboratory.statistical.service.trip.VagonLaboratoryService;
import com.uz.laboratory.statistical.util.DtoUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.ModalUtil;
import com.uz.laboratory.statistical.util.fx.TableDtoConverter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class RemarkStatisticsController implements Initializable {
    private final static int rowsPerPage = 9;
    private final ContextMenu contextMenu = new ContextMenu();
    @FXML
    public ComboBox<Stage> stageComboBox;
    @FXML
    public ComboBox<VagonLaboratory> vagonLaboratoryComboBox;
    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public ComboBox<Systems> deviceTypeComboBox;
    @FXML
    public ComboBox<DirectionsOfMovement> directionOfMovementComboBox;
    @FXML
    public ComboBox<CommunicationDistance> communicationDistanceComboBox;
    @FXML
    public DatePicker datePicker;
    @FXML
    public ComboBox<RemarkRepeat> repeatRemarkStatusComboBox;

    @FXML
    public TableView<StatisticsRemarkTableDto> statisticsTableView;
    @FXML
    public TableColumn<StatisticsRemarkTableDto, String> objectColumn;
    @FXML
    public TableColumn<StatisticsRemarkTableDto, String> noteColumn;
    @FXML
    public TableColumn<StatisticsRemarkTableDto, String> stageColumn;
    @FXML
    public TableColumn<StatisticsRemarkTableDto, String> dateColumn;
    @FXML
    public TableColumn<StatisticsRemarkTableDto, String> vagonColumn;
    @FXML
    public TableColumn<StatisticsRemarkTableDto, String> repeatColumn;
    @FXML
    public TableColumn<StatisticsRemarkTableDto, String> remarkIdColumn;
    @FXML
    public Pagination statisticsTableViewPagination;

    @FXML
    public Button search;
    @FXML
    public Button cleanTableView;
    @FXML
    public Button printAllTableViewDataFromExcel;
    @FXML
    public Button cleanDateComboBoxes;

    private ObservableList<StatisticsRemarkTableDto> statisticsTableData = FXCollections.observableArrayList();
    private IntegerProperty tableViewSelectedIndex = new SimpleIntegerProperty();
    private Long selectectedEntityId;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private VagonLaboratoryService vagonLaboratoryService;
    @Autowired
    private PonabRemarkService ponabRemarkService;
    @Autowired
    private AlsRemarkService alsRemarkService;
    @Autowired
    private CommunicationDistanceService communicationDistanceService;
    @Autowired
    private DeleteEntityDto deleteEntityDto;
    @Autowired
    private ModalUtil modalUtil;
    @Autowired
    private PonabRemarkEditEntityDto ponabRemarkEditEntityDto;
    @Autowired
    private AlsRemarkEditEntityDto alsRemarkEditEntityDto;
    @Autowired
    private PonabRemarkDto ponabRemarkDto;
    @Autowired
    private AlsRemarkDto alsRemarkDto;
    @Autowired
    private RemarkTableListSaveDto remarkTableListSaveDto;

    private List<String> errorList = new ArrayList<>();

    private StringConverter<Stage> stageConverter = new StringConverter<Stage>() {
        @Override
        public String toString(Stage object) {
            return object.getName();
        }

        @Override
        public Stage fromString(String string) {
            return null;
        }
    };
    private StringConverter<Sector> sectorConverter = new StringConverter<Sector>() {
        @Override
        public String toString(Sector object) {
            return object.getTitle();
        }

        @Override
        public Sector fromString(String string) {
            return null;
        }
    };
    private StringConverter<VagonLaboratory> vagonLaboratoryConverter = new StringConverter<VagonLaboratory>() {
        @Override
        public String toString(VagonLaboratory object) {
            return object.getName();
        }

        @Override
        public VagonLaboratory fromString(String string) {
            return null;
        }
    };
    private StringConverter<CommunicationDistance> communicationDistanceConverter = new StringConverter<CommunicationDistance>() {
        @Override
        public String toString(CommunicationDistance object) {
            return object.getNumber().toString();
        }

        @Override
        public CommunicationDistance fromString(String string) {
            return null;
        }
    };

    public RemarkStatisticsController() {
    }

    private void setTableViewSelectedIndex(int tableViewSelectedIndex) {
        this.tableViewSelectedIndex.set(tableViewSelectedIndex);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnsForTableView();
        statisticsTableViewPagination.setPageCount(1);
        statisticsTableViewPagination.setPageFactory(this::createPage);
        initComboBoxes();
        initTableView();
        initPopupMenu();
    }

    @FXML
    public void searchRemarksByFilter(ActionEvent actionEvent) {
        RemarkStatisticsFilter statisticsFilter = new RemarkStatisticsFilter();
        statisticsFilter.setSector(sectorComboBox.getSelectionModel().getSelectedItem());
        statisticsFilter.setStage(stageComboBox.getSelectionModel().getSelectedItem());
        statisticsFilter.setCommunicationDistance(communicationDistanceComboBox.getSelectionModel().getSelectedItem());
        statisticsFilter.setVagonLaboratory(vagonLaboratoryComboBox.getSelectionModel().getSelectedItem());
        statisticsFilter.setDeviceType(deviceTypeComboBox.getSelectionModel().getSelectedIndex());
        if (statisticsFilter.getDeviceType() == null
                || statisticsFilter.getDeviceType() < 0) {
            errorList.add(Constants.DEVICES_TYPE_IS_NOT_SET);
        }
        if (datePicker.getValue() != null) {
            statisticsFilter.setDate(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if (Integer.valueOf(directionOfMovementComboBox.getSelectionModel().getSelectedIndex()) != null) {
            if (directionOfMovementComboBox.getSelectionModel().getSelectedIndex() == 0) {
                statisticsFilter.setDirectionOfMovement(true);
            } else if (directionOfMovementComboBox.getSelectionModel().getSelectedIndex() == 1) {
                statisticsFilter.setDirectionOfMovement(false);
            }
        }
        if (repeatRemarkStatusComboBox.getSelectionModel().getSelectedItem() != null) {
            statisticsFilter.setRepeatable(repeatRemarkStatusComboBox.getSelectionModel().getSelectedIndex() == 0);
        }
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            statisticsTableData.clear();
            if (statisticsFilter.getDeviceType() == 0) {
                statisticsTableData.setAll(TableDtoConverter.convertAlsRemarkListToDto(alsRemarkService.getRemarkListByFilter(statisticsFilter)));
            } else if (statisticsFilter.getDeviceType() == 1) {
                statisticsTableData.setAll(TableDtoConverter.convertPonabRemarkListToDto(ponabRemarkService.getRemarkListByFilter(statisticsFilter)));
            }
            statisticsTableViewPagination.setPageFactory(this::createPage);
            if (statisticsTableData.size() <= 9) {
                statisticsTableViewPagination.setPageCount(1);
            } else {
                statisticsTableViewPagination.setPageCount((int) Math.ceil((double) statisticsTableData.size() / rowsPerPage));
            }
        }
    }

    @FXML
    public void currentSectorSelected(ActionEvent actionEvent) {
        if (sectorComboBox.getSelectionModel().getSelectedItem() != null) {
            stageComboBox.setItems(FXCollections.observableArrayList(sectorComboBox.getSelectionModel().getSelectedItem().getStageList()));
        }
    }

    @FXML
    public void cleanTableViewButtonListener(ActionEvent actionEvent) {
        statisticsTableData.clear();
        statisticsTableView.getItems().clear();
    }

    @FXML
    public void stageSelectionListener(ActionEvent actionEvent) {
        communicationDistanceComboBox.setValue(null);
    }

    @FXML
    public void communicationDistanceSelectionListener(ActionEvent actionEvent) {
        stageComboBox.setValue(null);
    }

    @FXML
    public void cleanChoiceComboBoxes(ActionEvent actionEvent) {
        sectorComboBox.setValue(null);
        stageComboBox.setValue(null);
        vagonLaboratoryComboBox.setValue(null);
        deviceTypeComboBox.setValue(null);
        directionOfMovementComboBox.setValue(null);
        communicationDistanceComboBox.setValue(null);
        datePicker.setValue(null);
        repeatRemarkStatusComboBox.setValue(null);
    }
    private void initTableView() {
        statisticsTableView.setEditable(true);
        statisticsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        statisticsTableView.setContextMenu(contextMenu);
        statisticsTableView.setOnMouseClicked(mouseEvent -> {
            if (statisticsTableView.getSelectionModel().getSelectedItem() != null
                    && mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                contextMenu.show(statisticsTableView, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                setTableViewSelectedIndex(statisticsTableView.getSelectionModel().getSelectedIndex());
                selectectedEntityId = Long.valueOf(statisticsTableView.getSelectionModel().getSelectedItem().getRemarkId());
            }
        });
    }

    private void initComboBoxes() {
        stageComboBox.setConverter(stageConverter);
        communicationDistanceComboBox.setConverter(communicationDistanceConverter);
        sectorComboBox.setConverter(sectorConverter);
        vagonLaboratoryComboBox.setConverter(vagonLaboratoryConverter);

        communicationDistanceComboBox.getItems().setAll(communicationDistanceService.listAll());
        directionOfMovementComboBox.getItems().setAll(DirectionsOfMovement.values());
        deviceTypeComboBox.getItems().setAll(Systems.values());
        vagonLaboratoryComboBox.getItems().setAll(vagonLaboratoryService.listAll());
        sectorComboBox.getItems().setAll(sectorService.listAll());
        repeatRemarkStatusComboBox.getItems().setAll(RemarkRepeat.values());
    }

    private void initPopupMenu() {
        MenuItem view = new MenuItem(Constants.POPUP_MENU_VIEW_INFO);
        MenuItem edit = new MenuItem(Constants.POPUP_MENU_EDIT_INFO);
        MenuItem printTableView = new MenuItem(Constants.POPUP_MENU_PRINT_TABLE_VIEW);
        MenuItem saveTableView = new MenuItem(Constants.POPUP_MENU_SAVE_TABLE_VIEW);
        MenuItem delete = new MenuItem(Constants.POPUP_MENU_DELETE_INFO);
        MenuItem safetySpaceFirst = new MenuItem(Constants.SAFETY_SPACE);
        MenuItem safetySpaceSecond = new MenuItem(Constants.SAFETY_SPACE);

        contextMenu.getItems().addAll(view, edit, safetySpaceFirst, printTableView, saveTableView, safetySpaceSecond, delete);
        view.setOnAction(event -> {
            if (statisticsTableView.getSelectionModel().getSelectedItem() != null) {
                if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
                    DtoUtil.convertAlsRemarkToDto((AlsRemark) alsRemarkService.get(Long.valueOf(statisticsTableView.getSelectionModel().getSelectedItem().getRemarkId())), alsRemarkDto);
                    modalUtil.createAlsRemarkViewModal();
                } else if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 1) {
                    DtoUtil.convertPonabRemarkToDto((PonabRemark) ponabRemarkService.get(Long.valueOf(statisticsTableView.getSelectionModel().getSelectedItem().getRemarkId())), ponabRemarkDto);
                    modalUtil.createPonabRemarkViewModal();
                }
            }
        });
        edit.setOnAction(event -> {
            if (statisticsTableView.getSelectionModel().getSelectedItem() != null) {
                if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
                    prepareAlsEditDtoEntity();
                    modalUtil.createAlsRemarkEditModal();
                    if (alsRemarkEditEntityDto.getAlsRemark() != null) {
                        statisticsTableData.remove(alsRemarkEditEntityDto.getTableViewIndex());
                        statisticsTableData.set(alsRemarkEditEntityDto.getTableViewIndex(), TableDtoConverter.convertEditedAlsRemarkToTableDto(statisticsTableView.getSelectionModel().getSelectedItem(), alsRemarkEditEntityDto.getAlsRemark()));
                        alsRemarkEditEntityDto.setAlsRemark(null);
                        alsRemarkEditEntityDto.setTableViewIndex(null);
                    }
                } else if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 1) {
                    preparePonabEditDtoEntity();
                    modalUtil.createPonabRemarkEditModal();
                    if (ponabRemarkEditEntityDto.getPonabRemark() != null) {
                        statisticsTableData.remove(ponabRemarkEditEntityDto.getTableViewIndex());
                        statisticsTableData.set(ponabRemarkEditEntityDto.getTableViewIndex(), TableDtoConverter.convertEditedPonabRemarkToTableDto(statisticsTableView.getSelectionModel().getSelectedItem(), ponabRemarkEditEntityDto.getPonabRemark()));
                        ponabRemarkEditEntityDto.setPonabRemark(null);
                        ponabRemarkEditEntityDto.setTableViewIndex(null);
                    }
                }
            }
        });
        saveTableView.setOnAction(event -> {
            remarkTableListSaveDto.setStatisticsRemarkTableDtos(statisticsTableData);
            modalUtil.createRemarkTableSaveModal();
        });
        delete.setOnAction(event -> {
            if (statisticsTableView.getSelectionModel().getSelectedItem() != null) {
                modalUtil.createDeletionConfirmModal();
                if (deleteEntityDto.getDeleteValidationValue() != null
                        && deleteEntityDto.getDeleteValidationValue()) {
                    deleteConfirm();
                    deleteEntityDto.setDeleteValidationValue(false);
                }
            }
        });
    }

    private void initColumnsForTableView() {
        objectColumn.setCellValueFactory(param -> param.getValue().objectColumnProperty());
        noteColumn.setCellValueFactory(param -> param.getValue().noteColumnProperty());
        stageColumn.setCellValueFactory(param -> param.getValue().stageColumnProperty());
        dateColumn.setCellValueFactory(param -> param.getValue().dateColumnProperty());
        vagonColumn.setCellValueFactory(param -> param.getValue().vagonColumnProperty());
        repeatColumn.setCellValueFactory(param -> param.getValue().repeatColumnProperty());
        remarkIdColumn.setCellValueFactory(param -> param.getValue().remarkIdProperty());
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, statisticsTableData.size());
        statisticsTableView.setItems(FXCollections.observableArrayList(statisticsTableData.subList(fromIndex, toIndex)));
        return new BorderPane(statisticsTableView);
    }

    private void deleteConfirm() {
        if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
            if (alsRemarkService.isExists(selectectedEntityId)) {
                alsRemarkService.delete(alsRemarkService.get(selectectedEntityId));
                cleanTableViewFromValue();
            } else {
                cleanTableViewFromValue();
            }
        } else if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 1) {
            if (ponabRemarkService.isExists(selectectedEntityId)) {
                ponabRemarkService.delete(ponabRemarkService.get(selectectedEntityId));
                cleanTableViewFromValue();
            } else {
                cleanTableViewFromValue();
            }
        }
        if (statisticsTableData.size() <= 9) {
            statisticsTableViewPagination.setPageCount(1);
        } else {
            statisticsTableViewPagination.setPageCount((int) Math.ceil((double) statisticsTableData.size() / rowsPerPage));
        }
    }

    private void preparePonabEditDtoEntity() {
        ponabRemarkEditEntityDto.setTableViewIndex(statisticsTableView.getSelectionModel().getSelectedIndex());
        ponabRemarkEditEntityDto.setEditedEntityId(selectectedEntityId);
        ponabRemarkEditEntityDto.setSectorList(sectorComboBox.getItems());
        ponabRemarkEditEntityDto.setNote(statisticsTableView.getSelectionModel().getSelectedItem().getNoteColumn());
        ponabRemarkEditEntityDto.setRepeatList(FXCollections.observableArrayList(RemarkRepeat.values()));
    }

    private void prepareAlsEditDtoEntity() {
        alsRemarkEditEntityDto.setTableViewIndex(statisticsTableView.getSelectionModel().getSelectedIndex());
        alsRemarkEditEntityDto.setEditedEntityId(selectectedEntityId);
        alsRemarkEditEntityDto.setSectorList(sectorComboBox.getItems());
        alsRemarkEditEntityDto.setNote(statisticsTableView.getSelectionModel().getSelectedItem().getNoteColumn());
        alsRemarkEditEntityDto.setRepeatList(FXCollections.observableArrayList(RemarkRepeat.values()));
    }

    private void cleanTableViewFromValue() {
        statisticsTableData.remove(tableViewSelectedIndex.get());
        statisticsTableView.getItems().remove(tableViewSelectedIndex.get());
        statisticsTableView.getSelectionModel().clearSelection();
    }

    private <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> col : tableView.getColumns())
            if (col.getText().equals(name)) return col;
        return null;
    }
}
