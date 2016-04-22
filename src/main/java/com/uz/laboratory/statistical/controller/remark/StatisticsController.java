package com.uz.laboratory.statistical.controller.remark;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.DirectionsOfMovement;
import com.uz.laboratory.statistical.dict.Systems;
import com.uz.laboratory.statistical.dto.DeleteEntityDto;
import com.uz.laboratory.statistical.dto.StatisticsRemarkTableDto;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.filter.StatisticsFilter;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.service.remark.AlsRemarkService;
import com.uz.laboratory.statistical.service.remark.PonabRemarkService;
import com.uz.laboratory.statistical.service.trip.VagonLaboratoryService;
import com.uz.laboratory.statistical.util.DateUtil;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
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
import javafx.util.StringConverter;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.collections.impl.list.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class StatisticsController implements Initializable {
    private final static int rowsPerPage = 9;
    final ContextMenu contextMenu = new ContextMenu();
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
    public ComboBox<Integer> dayComboBox;
    @FXML
    public ComboBox<String> monthComboBox;
    @FXML
    public ComboBox<Integer> yearsComboBox;

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
    @FXML
    public Button deleteConfirmButton;
    @FXML
    public Button resetDeletionButton;
    private ObservableList<StatisticsRemarkTableDto> statisticsTableData = FXCollections.observableArrayList();
    private IntegerProperty tableViewSelectedIndex = new SimpleIntegerProperty();
    private Long selectectedEntityId;

    @Autowired
    private SectorService sectorService;
    @Autowired
    private VagonLaboratoryService vagonLaboratoryService;
    @Autowired
    private PonabRemarkService ponabRemarkService;
    @Autowired
    private AlsRemarkService alsRemarkService;
    @Autowired
    private DeleteEntityDto deleteEntityDto;
    @Autowired
    private ModalUtil modalUtil;

    private List<String> errorList = new ArrayList<>();

    private boolean cleaningValue = false;
    private boolean resetCleaningValue = false;
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

    public StatisticsController() {
    }

    public void setTableViewSelectedIndex(int tableViewSelectedIndex) {
        this.tableViewSelectedIndex.set(tableViewSelectedIndex);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnsForTableView();
        statisticsTableViewPagination.setPageFactory(this::createPage);
        stageComboBox.setConverter(stageConverter);
        sectorComboBox.setConverter(sectorConverter);
        vagonLaboratoryComboBox.setConverter(vagonLaboratoryConverter);
        directionOfMovementComboBox.getItems().setAll(DirectionsOfMovement.values());
        deviceTypeComboBox.getItems().setAll(Systems.values());
        vagonLaboratoryComboBox.setItems(FXCollections.observableArrayList(vagonLaboratoryService.listAll()));
        sectorComboBox.setItems(FXCollections.observableArrayList(sectorService.listAll()));
        yearsComboBox.setItems(FXCollections.observableArrayList(Interval.fromTo(2013, 2045)));

        statisticsTableView.setEditable(true);
        statisticsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        statisticsTableView.setContextMenu(contextMenu);
        statisticsTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (statisticsTableView.getSelectionModel().getSelectedItem() != null
                        && mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    contextMenu.show(statisticsTableView, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                    setTableViewSelectedIndex(statisticsTableView.getSelectionModel().getSelectedIndex());
                    selectectedEntityId = Long.valueOf(statisticsTableView.getSelectionModel().getSelectedItem().getRemarkId());
                }
                }
        });

        /**
         * Popup menu definitions
         */
        MenuItem view = new MenuItem(Constants.VIEW_INFO);
        MenuItem edit = new MenuItem(Constants.EDIT_INFO);
        MenuItem safetySpace = new MenuItem("");
        MenuItem delete = new MenuItem(Constants.DELETE_INFO);
        contextMenu.getItems().addAll(view, edit, safetySpace, delete);
        StatisticsController statisticsController = this;
        view.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modalUtil.createViewRemarkModal();
                }
        });
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modalUtil.createEditRemarkModal();
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modalUtil.createRemarkDeletionConfirmModal();
                if (deleteEntityDto.getDeleteValidationValue()) {
                    deleteConfirm();
                    deleteEntityDto.setDeleteValidationValue(false);
                }
            }
        });
    }

    @FXML
    public void searchRemarksByFilter(ActionEvent actionEvent) {
        StatisticsFilter statisticsFilter = new StatisticsFilter();
        statisticsFilter.setSector(sectorComboBox.getSelectionModel().getSelectedItem());
        statisticsFilter.setStage(stageComboBox.getSelectionModel().getSelectedItem());
        statisticsFilter.setVagonLaboratory(vagonLaboratoryComboBox.getSelectionModel().getSelectedItem());
        statisticsFilter.setDeviceType(deviceTypeComboBox.getSelectionModel().getSelectedIndex());
        try {
            statisticsFilter.setDate(DateUtil.dateBuilder(
                    yearsComboBox.getSelectionModel().getSelectedItem(),
                    monthComboBox.getSelectionModel().getSelectedItem(),
                    dayComboBox.getSelectionModel().getSelectedItem()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Integer.valueOf(directionOfMovementComboBox.getSelectionModel().getSelectedIndex()) != null) {
            if (directionOfMovementComboBox.getSelectionModel().getSelectedIndex() == 0) {
                statisticsFilter.setDirectionOfMovement(true);
            } else if (directionOfMovementComboBox.getSelectionModel().getSelectedIndex() == 1) {
                statisticsFilter.setDirectionOfMovement(false);
            }
        }
        if (statisticsFilter.getDeviceType() == 0) {
            statisticsTableData.clear();
            statisticsTableData.setAll(TableDtoConverter.convertAlsRemarkListToDto(alsRemarkService.getRemarkListByFilter(statisticsFilter)));
            statisticsTableViewPagination.setPageFactory(this::createPage);
        } else if (statisticsFilter.getDeviceType() == 1) {
            statisticsTableData.clear();
            statisticsTableData.setAll(TableDtoConverter.convertPonabRemarkListToDto(ponabRemarkService.getRemarkListByFilter(statisticsFilter)));
            statisticsTableViewPagination.setPageFactory(this::createPage);
        }
        if (statisticsTableData.size() <= 9) {
            statisticsTableViewPagination.setPageCount(1);
        } else {
            statisticsTableViewPagination.setPageCount((int) Math.ceil((double) statisticsTableData.size() / rowsPerPage));
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
    public void monthSelectedAction(ActionEvent actionEvent) {
        if (resetCleaningValue) {
            /**
             * Skips the validations for cleaning comboboxes
             */
        } else {
            dayComboBox.getItems().clear();
            if (yearsComboBox.getSelectionModel().getSelectedItem() == null
                    || !StringUtils.isNotEmpty(yearsComboBox.getSelectionModel().getSelectedItem().toString())) {
                errorList.add(Constants.YEAR_VALUES_IS_NOT_SET);
            }
            if (cleaningValue) {
                if (monthComboBox.getSelectionModel().getSelectedItem() == null
                        || !StringUtils.isNotEmpty(monthComboBox.getSelectionModel().getSelectedItem())) {
                    errorList.add(Constants.MONTH_VALUES_IS_NOT_SET);
                    cleaningValue = false;
                }
            }

            if (!errorList.isEmpty()) {
                createAlert(AlertGuiUtil.prepareAlertMessage(errorList));
                errorList.clear();
            } else {
                try {
                    if (monthComboBox.getSelectionModel().getSelectedItem() != null
                            && yearsComboBox.getSelectionModel().getSelectedItem() != null) {
                        dayComboBox.setItems(FXCollections.observableArrayList(DateUtil.getDayListByMonthYear(yearsComboBox.getSelectionModel().getSelectedItem(), DateUtil.getMonthNumberByTitle(monthComboBox.getSelectionModel().getSelectedItem()))));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void yearSelectedAction(ActionEvent actionEvent) {
        if (resetCleaningValue) {
            /**
             * Skips the validations for cleaning comboboxes
             */
        } else {
            monthComboBox.getItems().clear();
            if (yearsComboBox.getSelectionModel().getSelectedItem() == null
                    || !StringUtils.isNotEmpty(yearsComboBox.getSelectionModel().getSelectedItem().toString())) {
                createAlert(Constants.YEAR_VALUES_IS_NOT_SET);
            } else {
                monthComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(new DateFormatSymbols().getMonths())));
            }
        }
    }

    @FXML
    public void cleanDateComboBoxes(ActionEvent actionEvent) {
        resetCleaningValue = true;
        yearsComboBox.setValue(null);
        dayComboBox.setValue(null);
        monthComboBox.setValue(null);
        resetCleaningValue = false;
    }

    public void resetDeletionButtonListener() {

    }

    public void deleteConfirm() {
        if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 0) {
            alsRemarkService.delete(alsRemarkService.get(selectectedEntityId));
            statisticsTableData.remove(tableViewSelectedIndex.get());
            statisticsTableView.getItems().remove(tableViewSelectedIndex.get());
            statisticsTableView.getSelectionModel().clearSelection();
        } else if (deviceTypeComboBox.getSelectionModel().getSelectedIndex() == 1) {
            ponabRemarkService.delete(alsRemarkService.get(selectectedEntityId));
            statisticsTableData.remove(tableViewSelectedIndex.get());
            statisticsTableView.getItems().remove(tableViewSelectedIndex.get());
            statisticsTableView.getSelectionModel().clearSelection();
        }
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

    private Node initializePage(int pageIndex) {
        return new BorderPane(statisticsTableView);
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, statisticsTableData.size());
        System.out.println("From index = " + fromIndex);
        System.out.println("To index = " + toIndex);
        statisticsTableView.setItems(FXCollections.observableArrayList(statisticsTableData.subList(fromIndex, toIndex)));
        return new BorderPane(statisticsTableView);
    }

    private void updateTable() {
        statisticsTableViewPagination.setPageFactory(this::createPage);
    }

    private <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> col : tableView.getColumns())
            if (col.getText().equals(name)) return col;
        return null;
    }

    private void createAlert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Constants.ERROR_TITLE);
        alert.setHeaderText(Constants.ERROR_HEADER);
        alert.setContentText(error);
        alert.showAndWait();
        alert = null;
    }
}
