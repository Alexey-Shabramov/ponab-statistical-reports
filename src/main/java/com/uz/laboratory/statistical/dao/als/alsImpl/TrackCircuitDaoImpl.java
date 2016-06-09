package com.uz.laboratory.statistical.dao.als.alsImpl;

import com.uz.laboratory.statistical.dao.als.TrackCircuitDao;
import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.filter.AlsDevicesFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class TrackCircuitDaoImpl extends GenericDaoImpl<TrackCircuit> implements TrackCircuitDao {
    @Override
    public Class getEntityClass() {
        return TrackCircuit.class;
    }

    @Override
    public List<TrackCircuit> getAlsSystemsByStation(Station station) {
        return getSession().createCriteria(getEntityClass(), "system")
                .createAlias("system.station", "station")
                .add(Restrictions.eq("station.id", station.getId()))
                .list();
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

    @Override
    public List<TrackCircuit> getCircuitListByFilter(AlsDevicesFilter alsDevicesFilter) {
        Criteria criteria = getSession().createCriteria(getEntityClass(), "system");
        criteria.createAlias("system.sector", "sector")
                .createAlias("system.stage", "stage")
                .createAlias("system.station", "station")
                .createAlias("system.communicationDistance", "distance");
        if (alsDevicesFilter.getSector() != null) {
            criteria.add(Restrictions.eq("sector.id", alsDevicesFilter.getSector().getId()));
        }
        if (alsDevicesFilter.getStage() != null) {
            criteria.add(Restrictions.eq("stage.id", alsDevicesFilter.getStage().getId()));
        } else if (alsDevicesFilter.getStation() != null) {
            criteria.add(Restrictions.eq("station.id", alsDevicesFilter.getStation().getId()));
        }
        if (alsDevicesFilter.getStationalTrackCircuit() != null) {
            criteria.add(Restrictions.eq("system.stationalCircuit", alsDevicesFilter.getStationalTrackCircuit()));
        }
        if (alsDevicesFilter.getCommunicationDistance() != null) {
            criteria.add(Restrictions.eq("distance.id", alsDevicesFilter.getCommunicationDistance().getId()));
        }
        if (alsDevicesFilter.getEven() != null) {
            criteria.add(Restrictions.eq("system.even", alsDevicesFilter.getEven()));
        }
        if (alsDevicesFilter.getPicket() != null) {
            criteria.add(Restrictions.eq("system.picket", alsDevicesFilter.getPicket()));
        }
        return criteria.list();
    }
}
