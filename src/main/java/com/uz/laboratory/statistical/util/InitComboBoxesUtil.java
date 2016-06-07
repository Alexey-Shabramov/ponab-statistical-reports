package com.uz.laboratory.statistical.util;


import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.service.als.TrackCircuitService;
import com.uz.laboratory.statistical.service.location.CommunicationDistanceService;
import com.uz.laboratory.statistical.service.location.SectorService;
import com.uz.laboratory.statistical.service.location.StageService;
import com.uz.laboratory.statistical.service.location.StationService;
import com.uz.laboratory.statistical.service.ponab.PonabSystemService;
import com.uz.laboratory.statistical.service.trip.VagonLaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitComboBoxesUtil {
    public static List<Sector> sectorList = new ArrayList<>();
    public static List<Stage> stageList = new ArrayList<>();
    public static List<Station> stationList = new ArrayList<>();
    public static List<VagonLaboratory> vagonLaboratoryList = new ArrayList<>();
    public static List<CommunicationDistance> communicationDistanceList = new ArrayList<>();
    public static List<TrackCircuit> trackCircuitList = new ArrayList<>();
    public static List<PonabSystem> ponabSystemList = new ArrayList<>();
    @Autowired
    private StationService stationService;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private StageService stageService;
    @Autowired
    private VagonLaboratoryService vagonLaboratoryService;
    @Autowired
    private CommunicationDistanceService communicationDistanceService;
    @Autowired
    private TrackCircuitService trackCircuitService;
    @Autowired
    private PonabSystemService ponabSystemService;

    public InitComboBoxesUtil() {
    }

    @PostConstruct
    public void initComboBoxes() {
        stationList.addAll(stationService.listAll());
        sectorList.addAll(sectorService.listAll());
        stageList.addAll(stageService.listAll());
        vagonLaboratoryList.addAll(vagonLaboratoryService.listAll());
        communicationDistanceList.addAll(communicationDistanceService.listAll());
        trackCircuitList.addAll(trackCircuitService.listAll());
        ponabSystemList.addAll(ponabSystemService.listAll());
    }
}
