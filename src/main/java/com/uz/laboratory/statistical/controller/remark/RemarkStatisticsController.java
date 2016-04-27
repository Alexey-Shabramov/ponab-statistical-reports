package com.uz.laboratory.statistical.controller.remark;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.DirectionsOfMovement;
import com.uz.laboratory.statistical.dict.RemarkRepeat;
import com.uz.laboratory.statistical.dict.Systems;
import com.uz.laboratory.statistical.dto.DeleteEntityDto;
import com.uz.laboratory.statistical.dto.PonabRemarkDto;
import com.uz.laboratory.statistical.dto.PonabRemarkEditEntityDto;
import com.uz.laboratory.statistical.dto.tableView.StatisticsRemarkTableDto;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;
import com.uz.laboratory.statistical.service.location.CommunicationDistanceService;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.service.remark.AlsRemarkService;
import com.uz.laboratory.statistical.service.remark.PonabRemarkService;
import com.uz.laboratory.statistical.service.trip.VagonLaboratoryService;
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
    public ComboBox communicationDistanceComboBox;
    @FXML
    public DatePicker datePicker;

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
    private PonabRemarkDto ponabRemarkDto;

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
        stageComboBox.setItems(FXCollections.observableArrayList(sectorComboBox.getSelectionModel().getSelectedItem().getStageList()));
    }

    @FXML
    public void cleanTableViewData(ActionEvent actionEvent) {

    }

    @FXML
    public void printAllTableViewData(ActionEvent actionEvent) {

    }

    @FXML
    public void cleanDatePicker(ActionEvent actionEvent) {
    }

    @FXML
    public void stageSelectionListener(ActionEvent actionEvent) {
        communicationDistanceComboBox.setValue(null);
    }

    @FXML
    public void communicationDistanceSelectionListener(ActionEvent actionEvent) {
        stageComboBox.setValue(null);
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
    }

    private void initPopupMenu() {
        MenuItem view = new MenuItem(Constants.VIEW_INFO);
        MenuItem edit = new MenuItem(Constants.EDIT_INFO);
        MenuItem safetySpace = new MenuItem("");
        MenuItem delete = new MenuItem(Constants.DELETE_INFO);
        contextMenu.getItems().addAll(view, edit, safetySpace, delete);
        view.setOnAction(event -> {
            if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {

            } else if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 1) {
                dozerBeanMapper.map(ponabRemarkService.get(Long.valueOf(statisticsTableView.getSelectionModel().getSelectedItem().getRemarkId())), ponabRemarkDto, Constants.PONAB_REMARK_TO_DTO);
                modalUtil.createPonabRemarkViewModal();
            }
        });
        edit.setOnAction(event -> {
            if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
                prepareAlsEditDtoEntity();
            } else if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 1) {
                preparePonabEditDtoEntity();
            }
            modalUtil.createPonabRemarkEditModal();
            if (ponabRemarkEditEntityDto.getPonabRemark() != null) {
                statisticsTableData.remove(ponabRemarkEditEntityDto.getTableViewIndex());
                statisticsTableData.set(ponabRemarkEditEntityDto.getTableViewIndex(), TableDtoConverter.convertEditedPonabRemarkToTableDto(statisticsTableView.getSelectionModel().getSelectedItem(), ponabRemarkEditEntityDto.getPonabRemark()));
                ponabRemarkEditEntityDto.setPonabRemark(null);
                ponabRemarkEditEntityDto.setTableViewIndex(null);
            }
        });
        delete.setOnAction(event -> {
            modalUtil.createRemarkDeletionConfirmModal();
            if (deleteEntityDto.getDeleteValidationValue()) {
                deleteConfirm();
                deleteEntityDto.setDeleteValidationValue(false);
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
            alsRemarkService.delete(alsRemarkService.get(selectectedEntityId));
            statisticsTableData.remove(tableViewSelectedIndex.get());
            statisticsTableView.getItems().remove(tableViewSelectedIndex.get());
            statisticsTableView.getSelectionModel().clearSelection();
        } else if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 1) {
            ponabRemarkService.delete(ponabRemarkService.get(selectectedEntityId));
            statisticsTableData.remove(tableViewSelectedIndex.get());
            statisticsTableView.getItems().remove(tableViewSelectedIndex.get());
            statisticsTableView.getSelectionModel().clearSelection();
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

    }

    private <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> col : tableView.getColumns())
            if (col.getText().equals(name)) return col;
        return null;
    }
}
