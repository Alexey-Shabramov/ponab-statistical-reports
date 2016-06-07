package com.uz.laboratory.statistical.controller.shedule;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.SheduleTripsTypes;
import com.uz.laboratory.statistical.dto.DeleteEntityDto;
import com.uz.laboratory.statistical.dto.tableView.TripsTableDto;
import com.uz.laboratory.statistical.dto.trip.InspectionTripDto;
import com.uz.laboratory.statistical.dto.trip.InspectionTripEditEntityDto;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.filter.TripFilter;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.service.trip.InspectionTripService;
import com.uz.laboratory.statistical.service.trip.VagonLaboratoryService;
import com.uz.laboratory.statistical.util.DtoUtil;
import com.uz.laboratory.statistical.util.InitComboBoxesUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import com.uz.laboratory.statistical.util.fx.ComboBoxUtil;
import com.uz.laboratory.statistical.util.fx.ModalUtil;
import com.uz.laboratory.statistical.util.fx.TableDtoConverter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
public class SheduleController implements Initializable {
    private final static int rowsPerPage = 12;
    private final ContextMenu contextMenu = new ContextMenu();
    @FXML
    public TableView<TripsTableDto> sheduleTableView;
    @FXML
    public TableColumn<TripsTableDto, String> dateColumn;
    @FXML
    public TableColumn<TripsTableDto, String> sectorColumn;
    @FXML
    public TableColumn<TripsTableDto, String> vagonLaboratoryColumn;
    @FXML
    public TableColumn<TripsTableDto, String> tripIdColumn;
    @FXML
    public DatePicker fromDatePicker;
    @FXML
    public DatePicker toDatePicker;
    @FXML
    public Pagination tripsTablePagination;
    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public ComboBox<VagonLaboratory> vagonLaboratoryComboBox;
    @FXML
    public ComboBox<SheduleTripsTypes> tripTypeComboBox;
    @FXML
    public Button searchTripsButton;
    @FXML
    public Button cleanTripsComboBoxes;
    @FXML
    public Button cleanTableViewButton;
    private ObservableList<TripsTableDto> tripsTableData = FXCollections.observableArrayList();
    private IntegerProperty tableViewSelectedIndex = new SimpleIntegerProperty();
    private Long selectectedEntityId;
    private Boolean selectedInspectionTripType;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private InspectionTripService inspectionTripService;
    @Autowired
    private VagonLaboratoryService vagonLaboratoryService;

    @Autowired
    private ModalUtil modalUtil;
    @Autowired
    private InspectionTripDto inspectionTripDto;
    @Autowired
    private DeleteEntityDto deleteEntityDto;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private InspectionTripEditEntityDto inspectionTripEditEntityDto;
    @Autowired
    private InitComboBoxesUtil initComboBoxesUtil;

    private List<String> errorList = new ArrayList<>();

    private void setTableViewSelectedIndex(int tableViewSelectedIndex) {
        this.tableViewSelectedIndex.set(tableViewSelectedIndex);
    }

    public void setSelectedInspectionTripType(Boolean selectedInspectionTripType) {
        this.selectedInspectionTripType = selectedInspectionTripType;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnsForTableView();
        tripsTablePagination.setPageCount(1);
        tripsTablePagination.setPageFactory(this::createPage);
        initComboBoxes();
        initTableView();
        initPopupMenu();
    }

    @FXML
    public void resetDeletionButtonListener(ActionEvent actionEvent) {

    }

    @FXML
    public void deleteConfirmButtonListener(ActionEvent actionEvent) {

    }

    @FXML
    public void searchTripsButtonListener(ActionEvent actionEvent) {
        TripFilter tripFilter = new TripFilter();
        tripFilter.setTripSector(sectorComboBox.getSelectionModel().getSelectedItem());
        tripFilter.setVagonLaboratory(vagonLaboratoryComboBox.getSelectionModel().getSelectedItem());
        if (tripTypeComboBox.getSelectionModel().getSelectedItem() == null) {
            errorList.add(Constants.SHEDULE_ERROR_TYPE_IS_NOT_SET);
        }
        if (fromDatePicker.getValue() != null) {
            tripFilter.setBeginDate(Date.from(fromDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if (toDatePicker.getValue() != null) {
            tripFilter.setEndDate(Date.from(toDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if (!errorList.isEmpty()) {
            AlertGuiUtil.prepareAlertMessage(errorList);
        } else {
            tripsTableData.clear();
            tripsTableData.setAll(TableDtoConverter.convertInspectionTripsListToDto(inspectionTripService.getInspectionListByFilter(tripFilter)));
            tripsTablePagination.setPageFactory(this::createPage);
            tripsTablePagination.setPageCount(tripsTableData.size() <= rowsPerPage ? 1 : (int) Math.ceil((double) tripsTableData.size() / rowsPerPage));
        }
    }

    @FXML
    public void cleanTripsComboBoxesListener(ActionEvent actionEvent) {
        vagonLaboratoryComboBox.setValue(null);
        sectorComboBox.setValue(null);
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);
        tripTypeComboBox.setValue(null);
    }

    @FXML
    public void cleanTableViewListener(ActionEvent actionEvent) {
        tripsTableData.clear();
        sheduleTableView.getItems().clear();
        tripsTablePagination.setPageCount(1);
        tripsTablePagination.setPageFactory(this::createPage);
    }

    private void initColumnsForTableView() {
        sectorColumn.setCellValueFactory(param -> param.getValue().sectorTitleProperty());
        vagonLaboratoryColumn.setCellValueFactory(param -> param.getValue().vagonLaboratoryTitleProperty());
        dateColumn.setCellValueFactory(param -> param.getValue().dateProperty());
        tripIdColumn.setCellValueFactory(param -> param.getValue().deviceIdProperty());
    }

    private void initComboBoxes() {
        sectorComboBox.setConverter(ComboBoxUtil.sectorConverter);
        vagonLaboratoryComboBox.setConverter(ComboBoxUtil.vagonLaboratoryConverter);
        sectorComboBox.getItems().setAll(InitComboBoxesUtil.sectorList);
        vagonLaboratoryComboBox.getItems().setAll(InitComboBoxesUtil.vagonLaboratoryList);
        tripTypeComboBox.getItems().setAll(SheduleTripsTypes.values());
    }

    private void initTableView() {
        sheduleTableView.setEditable(true);
        sheduleTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        sheduleTableView.setContextMenu(contextMenu);
        sheduleTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (sheduleTableView.getSelectionModel().getSelectedItem() != null
                        && mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    contextMenu.show(sheduleTableView, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                    setTableViewSelectedIndex(sheduleTableView.getSelectionModel().getSelectedIndex());
                    selectectedEntityId = Long.valueOf(sheduleTableView.getSelectionModel().getSelectedItem().getDeviceId());
                }
            }
        });
    }

    private void initPopupMenu() {
        MenuItem view = new MenuItem(Constants.POPUP_MENU_VIEW_INFO);
        MenuItem edit = new MenuItem(Constants.POPUP_MENU_EDIT_INFO);
        MenuItem safetySpace = new MenuItem("");
        MenuItem delete = new MenuItem(Constants.POPUP_MENU_DELETE_INFO);
        contextMenu.getItems().addAll(view, edit, safetySpace, delete);
        view.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sheduleTableView.getSelectionModel().getSelectedItem() != null) {
                    DtoUtil.convertInspectionTripToDto((InspectionTrip) inspectionTripService.get(Long.valueOf(sheduleTableView.getSelectionModel().getSelectedItem().getDeviceId())), inspectionTripDto);
                    modalUtil.createTripViewModal();
                }
            }
        });
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sheduleTableView.getSelectionModel().getSelectedItem() != null) {
                    prepareAbstractTripDto();
                    modalUtil.createTripEditModal();
                    if (inspectionTripEditEntityDto.getInspectionTrip() != null) {
                        tripsTableData.remove(inspectionTripEditEntityDto.getTableViewIndex());
                        tripsTableData.set(inspectionTripEditEntityDto.getTableViewIndex(), TableDtoConverter.convertEditedTripsToTableDto(sheduleTableView.getSelectionModel().getSelectedItem(), inspectionTripEditEntityDto.getInspectionTrip()));
                        inspectionTripEditEntityDto.setInspectionTrip(null);
                        inspectionTripEditEntityDto.setTableViewIndex(null);
                    }
                }
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sheduleTableView.getSelectionModel().getSelectedItem() != null) {
                    modalUtil.createDeletionConfirmModal();
                    if (deleteEntityDto.getDeleteValidationValue() != null
                            && deleteEntityDto.getDeleteValidationValue()) {
                        inspectionTripService.delete(selectectedEntityId);
                        tripsTableData.remove(tableViewSelectedIndex.get());
                        sheduleTableView.getItems().remove(tableViewSelectedIndex.get());
                        sheduleTableView.getSelectionModel().clearSelection();
                        deleteEntityDto.setDeleteValidationValue(false);
                        tripsTablePagination.setPageCount(tripsTableData.size() <= rowsPerPage ? 1 : (int) Math.ceil((double) tripsTableData.size() / rowsPerPage));
                    }
                }
            }
        });
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, tripsTableData.size());
        sheduleTableView.setItems(FXCollections.observableArrayList(tripsTableData.subList(fromIndex, toIndex)));
        return new BorderPane(sheduleTableView);
    }

    private void prepareAbstractTripDto() {
        inspectionTripEditEntityDto.setTableViewIndex(sheduleTableView.getSelectionModel().getSelectedIndex());
        inspectionTripEditEntityDto.setEditedEntityId(selectectedEntityId);
        inspectionTripEditEntityDto.setSectorList(sectorComboBox.getItems());
        inspectionTripEditEntityDto.setVagonLaboratoryList(vagonLaboratoryComboBox.getItems());
    }
}
