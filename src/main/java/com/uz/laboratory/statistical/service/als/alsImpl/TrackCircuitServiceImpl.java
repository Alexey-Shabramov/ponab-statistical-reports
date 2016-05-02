package com.uz.laboratory.statistical.service.als.alsImpl;

import com.uz.laboratory.statistical.dao.als.alsImpl.TrackCircuitDaoImpl;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.service.als.TrackCircuitService;
import com.uz.laboratory.statistical.service.serviceImpl.GenericServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class TrackCircuitServiceImpl extends GenericServiceImpl<TrackCircuit, TrackCircuitDaoImpl> implements TrackCircuitService {
    @Override
    @Transactional
    public List<TrackCircuit> getAlsSystemsByStage(Stage stage) {
        return dao.getAlsSystemsByStage(stage);
    }

    @Override
    @Transactional
    public List<TrackCircuit> getAlsSystemsBySector(Sector sector) {
        return dao.getAlsSystemsBySector(sector);
    }
}
