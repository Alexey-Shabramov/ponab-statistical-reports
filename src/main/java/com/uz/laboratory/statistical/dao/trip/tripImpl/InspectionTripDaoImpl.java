package com.uz.laboratory.statistical.dao.trip.tripImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.trip.InspectionTripDao;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.filter.TripFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class InspectionTripDaoImpl extends GenericDaoImpl<InspectionTrip> implements InspectionTripDao {
    @Override
    public Class getEntityClass() {
        return InspectionTrip.class;
    }

    @Override
    public List getInspectionListByFilter(TripFilter tripFilter) {
        Criteria criteria = getSession().createCriteria(getEntityClass(), "trip");
        criteria.createAlias("trip.tripSector", "sector")
                .createAlias("trip.vagonLaboratory", "vagon");
        if (tripFilter.getTripSector() != null) {
            criteria.add(Restrictions.eq("sector.id", tripFilter.getTripSector().getId()));
        }
        if (tripFilter.getVagonLaboratory() != null) {
            criteria.add(Restrictions.eq("vagon.id", tripFilter.getVagonLaboratory().getId()));
        }
        if (tripFilter.getBeginDate() != null) {
            criteria.add(Restrictions.ge("trip.date", tripFilter.getBeginDate()));
        }
        if (tripFilter.getEndDate() != null) {
            criteria.add(Restrictions.lt("trip.date", tripFilter.getEndDate()));
        }
        if (tripFilter.getPlannedTrip() != null) {
            criteria.add(Restrictions.eq("trip.plannedTrip", tripFilter.getPlannedTrip()));
        }
        return criteria.list();
    }

    @Override
    public List getInspectionTripsBySector(Sector sector) {
        return getSession().createCriteria(getEntityClass(), "trip")
                .createAlias("trip.tripSector", "sector")
                .add(Restrictions.eq("sector.id", sector.getId()))
                .add(Restrictions.eq("trip.plannedTrip", false))
                .list();
    }

    @Override
    public List listAllEndedInspectionTripList() {
        return getSession().createCriteria(getEntityClass(), "trip")
                .add(Restrictions.eq("trip.plannedTrip", false))
                .list();
    }
}
