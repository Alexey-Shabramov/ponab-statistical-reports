package com.uz.laboratory.statistical.controller;

import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.service.als.TrackCircuitService;
import com.uz.laboratory.statistical.service.location.CommunicationDistanceService;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.service.location.StageService;
import com.uz.laboratory.statistical.service.location.locationImpl.StationServiceImpl;
import com.uz.laboratory.statistical.service.ponab.PonabSystemService;
import com.uz.laboratory.statistical.service.remark.AlsRemarkService;
import com.uz.laboratory.statistical.service.trip.InspectionTripService;
import com.uz.laboratory.statistical.service.trip.VagonLaboratoryService;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private InspectionTripService inspectionTripService;

    @Autowired
    private TrackCircuitService trackCircuitService;

    @Autowired
    private AlsRemarkService alsRemarkService;

    @Autowired
    private PonabSystemService ponabSystemService;

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

        stage.setDistanceList(arrayList);

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
        InspectionTrip inspectionTrip = new InspectionTrip();
        inspectionTrip.setVagonLaboratory(vagonLaboratory);
        inspectionTrip.setTripSector(sector);
        inspectionTrip.setBeginDate(new Date());
        inspectionTripService.save(inspectionTrip);

        TrackCircuit trackCircuit = new TrackCircuit();
        trackCircuit.setCommunicationDistance(communicationDistance);
        trackCircuit.setName("Сп.3");
        trackCircuit.setStage(stage);
        trackCircuitService.save(trackCircuit);

        AlsRemark alsRemark = new AlsRemark();
        alsRemark.setEven(true);
        alsRemark.setNote("Штата там не работает!");
        alsRemark.setTrack_circuit_name(trackCircuit);
        alsRemark.setInspectionTrip(inspectionTrip);
        alsRemark.setCreationDate(new Date());
        alsRemark.setRepeatable(false);
        alsRemarkService.save(alsRemark);

        AlsRemark baba = new AlsRemark();
        baba.setEven(true);
        baba.setNote("Штата там не работает!");
        baba.setTrack_circuit_name(trackCircuit);
        baba.setInspectionTrip(inspectionTrip);
        baba.setCreationDate(new Date());
        baba.setRepeatable(false);
        alsRemarkService.save(baba);

        AlsRemark dozer = new AlsRemark();
        dozer.setEven(true);
        dozer.setNote("Штата там не работает!");
        dozer.setTrack_circuit_name(trackCircuit);
        dozer.setInspectionTrip(inspectionTrip);
        dozer.setCreationDate(new Date());
        dozer.setRepeatable(false);
        alsRemarkService.save(dozer);

        AlsRemark gopneg = new AlsRemark();
        gopneg.setEven(true);
        gopneg.setNote("Штата там не работает!");
        gopneg.setTrack_circuit_name(trackCircuit);
        gopneg.setInspectionTrip(inspectionTrip);
        gopneg.setCreationDate(new Date());
        gopneg.setRepeatable(false);
        alsRemarkService.save(gopneg);

        PonabSystem ponabSystem = new PonabSystem();
        ponabSystem.setOption("160");
        ponabSystem.setSector(sector);
        ponabSystem.setStage(stage);
        ponabSystem.setEvenDirectionOfMovement(true);
        ponabSystem.setLocation("51км + 200м");
        ponabSystem.setSpeachInformer(true);
        ponabSystem.setTitle("АСДК-Б");
        ponabSystemService.save(ponabSystem);
    }
}
