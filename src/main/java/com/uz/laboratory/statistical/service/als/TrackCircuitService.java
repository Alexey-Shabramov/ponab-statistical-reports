package com.uz.laboratory.statistical.service.als;

import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.service.GenericService;

import java.util.List;


public interface TrackCircuitService extends GenericService {
    List<TrackCircuit> getAlsSystemsByStage(Stage stage);

    List<TrackCircuit> getAlsSystemsBySector(Sector sector);
}
