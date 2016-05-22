package com.uz.laboratory.statistical.controller.als;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.DirectionsOfMovement;
import com.uz.laboratory.statistical.dict.RemarkRepeat;
import com.uz.laboratory.statistical.dict.TrackCircuitTypes;
import com.uz.laboratory.statistical.dto.DeleteEntityDto;
import com.uz.laboratory.statistical.dto.als.AlsSystemDto;
import com.uz.laboratory.statistical.dto.als.AlsSystemEditEntityDto;
import com.uz.laboratory.statistical.dto.tableView.AlsDevicesTableDto;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.filter.AlsDevicesFilter;
import com.uz.laboratory.statistical.service.als.TrackCircuitService;
import com.uz.laboratory.statistical.service.location.CommunicationDistanceService;
import com.uz.laboratory.statistical.service.location.SectorService;
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
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class AlsDevicesController implements Initializable {
    private final static int rowsPerPage = 9;
    private final ContextMenu contextMenu = new ContextMenu();

    @FXML
    public TableView<AlsDevicesTableDto> alsDevicesTableView;
    @FXML
    public TableColumn<AlsDevicesTableDto, String> trackCircuitColumn;
    @FXML
    public TableColumn<AlsDevicesTableDto, String> sectorColumn;
    @FXML
    public TableColumn<AlsDevicesTableDto, String> stageOrStationColumn;
    @FXML
    public TableColumn<AlsDevicesTableDto, String> directionOfMovementColumn;
    @FXML
    public TableColumn<AlsDevicesTableDto, String> trackCircuitTypeColumn;
    @FXML
    public TableColumn<AlsDevicesTableDto, String> picketColumn;
    @FXML
    public TableColumn<AlsDevicesTableDto, String> deviceIdColumn;
    @FXML
    public Pagination alsDevicesTablePagination;

    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public ComboBox<Stage> stageComboBox;
    @FXML
    public ComboBox<CommunicationDistance> communicationDistanceComboBox;
    @FXML
    public ComboBox<DirectionsOfMovement> directionOfMovementComboBox;
    @FXML
    public ComboBox<TrackCircuitTypes> trackCircuitTypeComboBox;
    @FXML
    public ComboBox<Station> stationComboBox;
    @FXML
    public TextField picketTextField;
    @FXML
    public Button allComboBoxesResetButton;
    private ObservableList<AlsDevicesTableDto> alsDevicesTableData = FXCollections.observableArrayList();
    private IntegerProperty tableViewSelectedIndex = new SimpleIntegerProperty();
    private Long selectectedEntityId;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private TrackCircuitService trackCircuitService;

    @Autowired
    private ModalUtil modalUtil;
    @Autowired
    private AlsSystemDto alsSystemDto;
    @Autowired
    private AlsSystemEditEntityDto alsSystemEditEntityDto;
    @Autowired
    private DeleteEntityDto deleteEntityDto;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private CommunicationDistanceService communicationDistanceService;

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
    private StringConverter<Station> stationConverter = new StringConverter<Station>() {
        @Override
        public String toString(Station object) {
            return object.getName();
        }

        @Override
        public Station fromString(String string) {
            return null;
        }
    };

    private void setTableViewSelectedIndex(int tableViewSelectedIndex) {
        this.tableViewSelectedIndex.set(tableViewSelectedIndex);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnsForTableView();
        alsDevicesTablePagination.setPageCount(1);
        alsDevicesTablePagination.setPageFactory(this::createPage);
        initComboBoxes();
        initTableView();
        initPopupMenu();
    }

    private void initColumnsForTableView() {
        sectorColumn.setCellValueFactory(param -> param.getValue().sectorTitleProperty());
        trackCircuitColumn.setCellValueFactory(param -> param.getValue().trackCircuitNameProperty());
        stageOrStationColumn.setCellValueFactory(param -> param.getValue().stageOrStationTitleProperty());
        directionOfMovementColumn.setCellValueFactory(param -> param.getValue().directionOfMovementProperty());
        directionOfMovementColumn.setCellValueFactory(param -> param.getValue().directionOfMovementProperty());
        trackCircuitTypeColumn.setCellValueFactory(param -> param.getValue().trackCircuitTypeProperty());
        picketColumn.setCellValueFactory(param -> param.getValue().picketNumberProperty());
        deviceIdColumn.setCellValueFactory(param -> param.getValue().deviceIdProperty());
    }

    private void initComboBoxes() {
        sectorComboBox.setConverter(sectorConverter);
        stageComboBox.setConverter(stageConverter);
        communicationDistanceComboBox.setConverter(communicationDistanceConverter);
        stationComboBox.setConverter(stationConverter);
        sectorComboBox.getItems().setAll(sectorService.listAll());
        communicationDistanceComboBox.getItems().setAll(communicationDistanceService.listAll());
        directionOfMovementComboBox.getItems().setAll(DirectionsOfMovement.values());
        trackCircuitTypeComboBox.getItems().setAll(TrackCircuitTypes.values());
    }

    private void initTableView() {
        alsDevicesTableView.setEditable(true);
        alsDevicesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        alsDevicesTableView.setContextMenu(contextMenu);
        alsDevicesTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (alsDevicesTableView.getSelectionModel().getSelectedItem() != null
                        && mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    contextMenu.show(alsDevicesTableView, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                    setTableViewSelectedIndex(alsDevicesTableView.getSelectionModel().getSelectedIndex());
                    selectectedEntityId = Long.valueOf(alsDevicesTableView.getSelectionModel().getSelectedItem().getDeviceId());
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
                if (alsDevicesTableView.getSelectionModel().getSelectedItem() != null) {
                    dozerBeanMapper.map(trackCircuitService.get(Long.valueOf(alsDevicesTableView.getSelectionModel().getSelectedItem().getDeviceId())), alsSystemDto, Constants.ALS_DEVICE_TO_DTO);
                    modalUtil.createPonabDeviceViewModal();
                }
            }
        });
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (alsDevicesTableView.getSelectionModel().getSelectedItem() != null) {
                    preparePonabSystemDto();
                    modalUtil.createPonabDeviceEditModal();
                    if (alsSystemEditEntityDto.getTrackCircuit() != null) {
                        alsDevicesTableData.remove(alsSystemEditEntityDto.getTableViewIndex());
                        alsDevicesTableData.set(alsSystemEditEntityDto.getTableViewIndex(), TableDtoConverter.convertEditedAlsSystemToTableDto(alsDevicesTableView.getSelectionModel().getSelectedItem(), alsSystemEditEntityDto.getTrackCircuit()));
                        alsSystemEditEntityDto.setTrackCircuit(null);
                        alsSystemEditEntityDto.setTableViewIndex(null);
                    }
                }
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (alsDevicesTableView.getSelectionModel().getSelectedItem() != null) {
                    modalUtil.createDeletionConfirmModal();
                    if (deleteEntityDto.getDeleteValidationValue()) {
                        trackCircuitService.delete(trackCircuitService.get(selectectedEntityId));
                        alsDevicesTableData.remove(tableViewSelectedIndex.get());
                        alsDevicesTableView.getItems().remove(tableViewSelectedIndex.get());
                        alsDevicesTableView.getSelectionModel().clearSelection();
                        deleteEntityDto.setDeleteValidationValue(false);
                        alsDevicesTablePagination.setPageCount(alsDevicesTableData.size() <= 9 ? 1 : (int) Math.ceil((double) alsDevicesTableData.size() / rowsPerPage));
                    }
                }
            }
        });
    }

    @FXML
    public void deleteConfirmButtonListener(ActionEvent actionEvent) {
    }

    @FXML
    public void resetDeletionButtonListener(ActionEvent actionEvent) {
    }

    @FXML
    public void sectorSelectedListener(ActionEvent actionEvent) {
        if (sectorComboBox.getSelectionModel().getSelectedItem() != null) {
            stageComboBox.getItems().setAll(sectorComboBox.getSelectionModel().getSelectedItem().getStageList());
            List stationList = new ArrayList<>();
            for (Stage stage : sectorComboBox.getSelectionModel().getSelectedItem().getStageList()) {
                stationList.add(stage.getFirstStation());
                stationList.add(stage.getSecondStation());
            }
            stationComboBox.getItems().setAll(stationList);
        }
    }

    @FXML
    public void stageComboBoxChangeListener(ActionEvent actionEvent) {
    }

    @FXML
    public void stationComboBoxChangeListener(ActionEvent actionEvent) {
    }

    @FXML
    public void trackCircuitTypeComboBoxChangeListener(ActionEvent actionEvent) {
    }

    @FXML
    public void findAlsDevicesButton(ActionEvent actionEvent) {
        AlsDevicesFilter alsDevicesFilter = new AlsDevicesFilter();
        alsDevicesFilter.setSector(sectorComboBox.getSelectionModel().getSelectedItem());
        alsDevicesFilter.setStage(stageComboBox.getSelectionModel().getSelectedItem());
        alsDevicesFilter.setCommunicationDistance(communicationDistanceComboBox.getSelectionModel().getSelectedItem());
        alsDevicesFilter.setStation(stationComboBox.getSelectionModel().getSelectedItem());
        if (directionOfMovementComboBox.getSelectionModel().getSelectedItem() != null) {
            alsDevicesFilter.setEven(directionOfMovementComboBox.getSelectionModel().getSelectedIndex() == 0);
        }
        if (StringUtils.isNotBlank(picketTextField.getText())) {
            alsDevicesFilter.setPicket(Double.valueOf(picketTextField.getText()));
        }
        if (trackCircuitTypeComboBox.getSelectionModel().getSelectedItem() != null) {
            alsDevicesFilter.setStationalTrackCircuit(trackCircuitTypeComboBox.getSelectionModel().getSelectedIndex() != 0);
        }
        alsDevicesTableData.clear();
        alsDevicesTableData.setAll(TableDtoConverter.convertAlsDeviceListToDto(trackCircuitService.getCircuitListByFilter(alsDevicesFilter)));

        alsDevicesTablePagination.setPageFactory(this::createPage);
        alsDevicesTablePagination.setPageCount(alsDevicesTableData.size() <= 9 ? 1 : (int) Math.ceil((double) alsDevicesTableData.size() / rowsPerPage));
    }

    @FXML
    public void cleanAlsDevicesTableButton(ActionEvent actionEvent) {
        alsDevicesTableView.getItems().clear();
    }

    @FXML
    public void resetAllComboBoxes(ActionEvent actionEvent) {
        communicationDistanceComboBox.setValue(null);
        directionOfMovementComboBox.setValue(null);
        sectorComboBox.setValue(null);
        stageComboBox.setValue(null);
        stationComboBox.setValue(null);
        trackCircuitTypeComboBox.setValue(null);
        picketTextField.setText(null);
    }

    @FXML
    public void communicationDistanceComboBoxListener(ActionEvent actionEvent) {
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, alsDevicesTableData.size());
        alsDevicesTableView.setItems(FXCollections.observableArrayList(alsDevicesTableData.subList(fromIndex, toIndex)));
        return new BorderPane(alsDevicesTableView);
    }

    private void preparePonabSystemDto() {
        alsSystemEditEntityDto.setTableViewIndex(alsDevicesTableView.getSelectionModel().getSelectedIndex());
        alsSystemEditEntityDto.setEditedEntityId(selectectedEntityId);
        alsSystemEditEntityDto.setSectorList(sectorComboBox.getItems());
        alsSystemEditEntityDto.setRepeatList(FXCollections.observableArrayList(RemarkRepeat.values()));
    }
}
