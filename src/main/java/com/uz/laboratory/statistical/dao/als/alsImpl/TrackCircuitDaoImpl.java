package com.uz.laboratory.statistical.dao.als.alsImpl;

import com.uz.laboratory.statistical.dao.als.TrackCircuitDao;
import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class TrackCircuitDaoImpl extends GenericDaoImpl<TrackCircuit> implements TrackCircuitDao {
    @Override
    public Class getEntityClass() {
        return TrackCircuit.class;
    }

    @Override
    public List<TrackCircuit> getAlsSystemsByStage(Stage stage) {
        return getSession().createCriteria(getEntityClass(), "system")
                .createAlias("system.stage", "stage")
                .add(Restrictions.eq("stage.id", stage.getId()))
                .list();
    }

    @Override
    public List<TrackCircuit> getAlsSystemsBySector(Sector sector) {
        return getSession().createCriteria(getEntityClass(), "system")
                .createAlias("system.sector", "sector")
                .add(Restrictions.eq("sector.id", sector.getId()))
                .list();
    }
}
