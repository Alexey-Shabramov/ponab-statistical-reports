package com.uz.laboratory.statistical.controller.ponab;


import com.uz.laboratory.statistical.dict.*;
import com.uz.laboratory.statistical.dto.PonabDevicesTableDto;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.filter.PonabDevicesFilter;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.service.location.StageService;
import com.uz.laboratory.statistical.service.ponab.PonabSystemService;
import com.uz.laboratory.statistical.util.fx.ModalUtil;
import com.uz.laboratory.statistical.util.fx.TableDtoConverter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class PonabDevicesController implements Initializable {
    private final static int rowsPerPage = 9;
    private static FXMLLoader fxmlLoader = new FXMLLoader();
    final ContextMenu contextMenu = new ContextMenu();
    @FXML
    public TableView<PonabDevicesTableDto> ponabDevicesTableView;
    @FXML
    public Button allComboBoxesResetButton;
    @FXML
    public Pagination ponabDevicesTablePagination;
    @FXML
    public TableColumn<PonabDevicesTableDto, String> deviceIdColumn;
    @FXML
    public TableColumn<PonabDevicesTableDto, String> sectorColumn;
    @FXML
    public TableColumn<PonabDevicesTableDto, String> stageColumn;
    @FXML
    public TableColumn<PonabDevicesTableDto, String> systemColumn;
    @FXML
    public TableColumn<PonabDevicesTableDto, String> directionOfMovementColumn;
    @FXML
    public TableColumn<PonabDevicesTableDto, String> optionColumn;
    @FXML
    public TableColumn<PonabDevicesTableDto, String> speachInformerColumn;
    @FXML
    public TableColumn<PonabDevicesTableDto, String> directionOfMovement;
    @FXML
    public ComboBox<Sector> sectorComboBox;
    @FXML
    public ComboBox<Stage> stageComboBox;
    @FXML
    public ComboBox<PonabSystems> ponabSystemComboBox;
    @FXML
    public ComboBox<DirectionsOfMovement> directionOfMovementComboBox;
    @FXML
    public ComboBox<PonabOptions> optionComboBox;
    @FXML
    public ComboBox<SpeachInformer> speachInformerComboBox;
    private ObservableList<PonabDevicesTableDto> ponabDevicesTableData = FXCollections.observableArrayList();
    private IntegerProperty tableViewSelectedIndex = new SimpleIntegerProperty();
    private Long selectectedEntityId;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private StageService stageService;
    @Autowired
    private PonabSystemService ponabSystemService;

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

    public void setTableViewSelectedIndex(int tableViewSelectedIndex) {
        this.tableViewSelectedIndex.set(tableViewSelectedIndex);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnsForTableView();
        ponabDevicesTablePagination.setPageFactory(this::initializePage);
        stageComboBox.setConverter(stageConverter);
        sectorComboBox.setConverter(sectorConverter);
        sectorComboBox.setItems(FXCollections.observableArrayList(sectorService.listAll()));

        directionOfMovementComboBox.getItems().setAll(DirectionsOfMovement.values());
        ponabSystemComboBox.getItems().setAll(PonabSystems.values());
        optionComboBox.getItems().setAll(PonabOptions.values());
        speachInformerComboBox.getItems().setAll(SpeachInformer.values());

        ponabDevicesTableView.setEditable(true);
        ponabDevicesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ponabDevicesTableView.setContextMenu(contextMenu);
        ponabDevicesTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (ponabDevicesTableView.getSelectionModel().getSelectedItem() != null
                        && mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    contextMenu.show(ponabDevicesTableView, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                    setTableViewSelectedIndex(ponabDevicesTableView.getSelectionModel().getSelectedIndex());
                    selectectedEntityId = Long.valueOf(ponabDevicesTableView.getSelectionModel().getSelectedItem().getDeviceId());
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

        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ModalUtil.createPonabDeviceEditModal();
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ponabSystemService.delete(ponabSystemService.get(selectectedEntityId));
                ponabDevicesTableData.remove(tableViewSelectedIndex.get());
                ponabDevicesTableView.getItems().remove(tableViewSelectedIndex.get());
                ponabDevicesTableView.getSelectionModel().clearSelection();
            }
        });
    }

    @FXML
    public void findPonabDeviceButton(ActionEvent actionEvent) {
        PonabDevicesFilter ponabDevicesFilter = new PonabDevicesFilter();
        ponabDevicesFilter.setSector(sectorComboBox.getSelectionModel().getSelectedItem());
        ponabDevicesFilter.setStage(stageComboBox.getSelectionModel().getSelectedItem());
        if (ponabSystemComboBox.getSelectionModel().getSelectedItem() != null) {
            ponabDevicesFilter.setPonabSystem(ponabSystemComboBox.getSelectionModel().getSelectedItem());
        }
        if (directionOfMovementComboBox.getSelectionModel().getSelectedItem() != null) {
            if (directionOfMovementComboBox.getSelectionModel().getSelectedIndex() == 0) {
                ponabDevicesFilter.setEvenDirectionOfMovement(true);
            } else if (directionOfMovementComboBox.getSelectionModel().getSelectedIndex() == 1) {
                ponabDevicesFilter.setEvenDirectionOfMovement(false);
            }
        }
        if (speachInformerComboBox.getSelectionModel().getSelectedItem() != null) {
            if (speachInformerComboBox.getSelectionModel().getSelectedIndex() == 0) {
                ponabDevicesFilter.setSpeachInformer(true);
            } else if (speachInformerComboBox.getSelectionModel().getSelectedIndex() == 1) {
                ponabDevicesFilter.setSpeachInformer(false);
            }
        }
        if (optionComboBox.getSelectionModel().getSelectedItem() != null) {
            ponabDevicesFilter.setOption(optionComboBox.getSelectionModel().getSelectedItem().toString());
        }
        ponabDevicesTableData.clear();
        ponabDevicesTableData.setAll(TableDtoConverter.convertPonabDeviceListToDto(ponabSystemService.getRemarkListByFilter(ponabDevicesFilter)));
        ponabDevicesTablePagination.setPageFactory(this::createPage);
    }

    @FXML
    public void cleanPonabDevicesTableButton(ActionEvent actionEvent) {
    }

    @FXML
    public void sectorSelectedListener(ActionEvent actionEvent) {
        stageComboBox.setItems(FXCollections.observableArrayList(sectorComboBox.getSelectionModel().getSelectedItem().getStageList()));
    }

    private void initColumnsForTableView() {
        sectorColumn.setCellValueFactory(param -> param.getValue().sectorTitleProperty());
        stageColumn.setCellValueFactory(param -> param.getValue().stageTitleProperty());
        systemColumn.setCellValueFactory(param -> param.getValue().systemTitleProperty());
        directionOfMovementColumn.setCellValueFactory(param -> param.getValue().directionOfMovementProperty());
        optionColumn.setCellValueFactory(param -> param.getValue().optionProperty());
        speachInformerColumn.setCellValueFactory(param -> param.getValue().speachInformatorProperty());
        directionOfMovement.setCellValueFactory(param -> param.getValue().directionOfMovementProperty());
        deviceIdColumn.setCellValueFactory(param -> param.getValue().deviceIdProperty());
    }

    private Node initializePage(int pageIndex) {
        return new BorderPane(ponabDevicesTableView);
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, ponabDevicesTableData.size());
        ponabDevicesTableView.setItems(FXCollections.observableArrayList(ponabDevicesTableData.subList(fromIndex, toIndex)));
        return new BorderPane(ponabDevicesTableView);
    }

    @FXML
    public void resetAllComboBoxes(ActionEvent actionEvent) {
        directionOfMovementComboBox.setValue(null);
        ponabSystemComboBox.setValue(null);
        optionComboBox.setValue(null);
        speachInformerComboBox.setValue(null);
    }

    @FXML
    public void deleteConfirmButtonListener(ActionEvent actionEvent) {
    }

    @FXML
    public void resetDeletionButtonListener(ActionEvent actionEvent) {
    }
}
