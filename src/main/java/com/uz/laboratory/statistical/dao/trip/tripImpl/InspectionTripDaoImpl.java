package com.uz.laboratory.statistical.dao.trip.tripImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.trip.InspectionTripDao;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class InspectionTripDaoImpl extends GenericDaoImpl<InspectionTrip> implements InspectionTripDao {
    @Override
    public Class getEntityClass() {
        return InspectionTrip.class;
    }

    @Override
    public List getInspectionTripByFilter(RemarkStatisticsFilter remarkStatisticsFilter) {
        Criteria criteria = getSession().createCriteria(getEntityClass(), "inspection");
        if (remarkStatisticsFilter.getSector() != null) {
            criteria.createAlias("inspection.sector", "sector")
                    .add(Restrictions.eq("sector.id", remarkStatisticsFilter.getSector().getId()));
        }
        if (remarkStatisticsFilter.getStage() != null) {
            criteria.createAlias("inspection.stage", "stage")
                    .add(Restrictions.eq("stage.id", remarkStatisticsFilter.getStage().getId()));
        }
        if (remarkStatisticsFilter.getStage() != null) {
            criteria.createAlias("inspection.vagonLaboratory", "vagonLaboratory")
                    .add(Restrictions.eq("vagonLaboratory.id", remarkStatisticsFilter.getVagonLaboratory().getId()));
        }
        return criteria.list();
    }

    @Override
    public List getInspectionTripsBySector(Sector sector) {
        return getSession().createCriteria(getEntityClass(), "trip")
                .createAlias("trip.tripSector", "sector")
                .add(Restrictions.eq("sector.id", sector.getId()))
                .list();
    }
}
