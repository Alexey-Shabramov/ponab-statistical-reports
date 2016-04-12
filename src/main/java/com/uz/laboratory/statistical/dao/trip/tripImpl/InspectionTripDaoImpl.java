package com.uz.laboratory.statistical.dao.trip.tripImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.trip.InspectionTripDao;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.filter.StatisticsFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class InspectionTripDaoImpl extends GenericDaoImpl<InspectionTrip> implements InspectionTripDao {
    @Override
    public Class getEntityClass() {
        return InspectionTrip.class;
    }

    @Override
    public List<InspectionTrip> getInspectionTripByFilter(StatisticsFilter statisticsFilter) {
        Criteria criteria = getSession().createCriteria(getEntityClass(), "inspection");
        if (statisticsFilter.getSector() != null) {
            criteria.createAlias("inspection.sector", "sector")
                    .add(Restrictions.eq("sector.id", statisticsFilter.getSector().getId()));
        }
        if (statisticsFilter.getStage() != null) {
            criteria.createAlias("inspection.stage", "stage")
                    .add(Restrictions.eq("stage.id", statisticsFilter.getStage().getId()));
        }
        if (statisticsFilter.getStage() != null) {
            criteria.createAlias("inspection.vagonLaboratory", "vagonLaboratory")
                    .add(Restrictions.eq("vagonLaboratory.id", statisticsFilter.getVagonLaboratory().getId()));
        }
        return criteria.list();
    }
}
