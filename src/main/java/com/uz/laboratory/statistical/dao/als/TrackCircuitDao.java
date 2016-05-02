package com.uz.laboratory.statistical.dao.als;

import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;

import java.util.List;


public interface TrackCircuitDao extends GenericDao {
    List<TrackCircuit> getAlsSystemsByStage(Stage stage);

    List<TrackCircuit> getAlsSystemsBySector(Sector sector);
}
