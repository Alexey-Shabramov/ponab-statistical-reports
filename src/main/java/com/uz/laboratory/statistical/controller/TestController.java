package com.uz.laboratory.statistical.controller;

import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.service.location.CommunicationDistanceService;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.service.location.StageService;
import com.uz.laboratory.statistical.service.location.locationImpl.StationServiceImpl;
import com.uz.laboratory.statistical.service.trip.VagonLaboratoryService;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class TestController implements Initializable {

    @Autowired
    private CommunicationDistanceService communicationDistanceService;

    @Autowired
    private StationServiceImpl stationService;

    @Autowired
    private StageService stageService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private VagonLaboratoryService vagonLaboratoryService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Station station = new Station();
        station.setName("Лозовая 1");
        stationService.save(station);

        System.out.println("Станция 1 равна: " + station.toString());

        Station station1 = new Station();
        station1.setName("Станция 2");
        stationService.save(station1);

        System.out.println("Станция 2 равна: " + station1.toString());

        Stage stage = new Stage();
        stage.setName(station.getName() + "-" + station1.getName());
        stage.setFirstStation(station);
        stage.setSecondStation(station1);

        CommunicationDistance communicationDistance = new CommunicationDistance();
        communicationDistance.setNumber((byte) 5);
        communicationDistanceService.save(communicationDistance);

        ArrayList arrayList = new ArrayList();
        arrayList.add(communicationDistance);

        stage.setCommunicationDistanceList(arrayList);

        stageService.save(stage);
        System.out.println(stage.toString());

        Sector sector = new Sector();
        sector.setTitle("Базовая станция 1 - Базовая станция 2");
        List<Stage> stageList = new ArrayList<>();
        stageList.add(stage);
        sector.setStageList(stageList);

        sectorService.save(sector);
        System.out.println(sector.toString());

        VagonLaboratory vagonLaboratory = new VagonLaboratory();
        vagonLaboratory.setName("Южная дорога");
        vagonLaboratoryService.save(vagonLaboratory);
    }
}
