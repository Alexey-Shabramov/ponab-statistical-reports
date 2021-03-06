package com.uz.laboratory.statistical.dao.remark.remarkImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.remark.AlsRemarkDao;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class AlsRemarkDaoImpl extends GenericDaoImpl<AlsRemark> implements AlsRemarkDao {
    @Override
    public Class getEntityClass() {
        return AlsRemark.class;
    }

    @Override
    public List getRemarkListByFilter(RemarkStatisticsFilter remarkStatisticsFilter) {
        Criteria criteria = getSession().createCriteria(getEntityClass(), "remark");
        criteria.createAlias("remark.inspectionTrip", "inspection")
                .createAlias("inspection.vagonLaboratory", "vagon")
                .createAlias("inspection.tripSector", "sector")
                .createAlias("sector.stageList", "stage")
                .createAlias("stage.distanceList", "distances");
        if (remarkStatisticsFilter.getSector() != null) {
            criteria.add(Restrictions.eq("sector.id", remarkStatisticsFilter.getSector().getId()));
        }
        if (remarkStatisticsFilter.getStage() != null) {
            criteria.add(Restrictions.eq("stage.id", remarkStatisticsFilter.getStage().getId()));
        }
        if (remarkStatisticsFilter.getCommunicationDistance() != null) {
            criteria.add(Restrictions.eq("distances.id", remarkStatisticsFilter.getCommunicationDistance().getId()));
        }
        if (remarkStatisticsFilter.getVagonLaboratory() != null) {
            criteria.add(Restrictions.eq("vagon.id", remarkStatisticsFilter.getVagonLaboratory().getId()));
        }
        if (remarkStatisticsFilter.getDirectionOfMovement() != null) {
            criteria.add(Restrictions.eq("remark.even", remarkStatisticsFilter.getDirectionOfMovement()));
        }
        if (remarkStatisticsFilter.getVagonLaboratory() != null) {
            criteria.add(Restrictions.eq("vagon.id", remarkStatisticsFilter.getVagonLaboratory().getId()));
        }
        if (remarkStatisticsFilter.getRepeatable() != null) {
            criteria.add(Restrictions.eq("remark.repeatable", remarkStatisticsFilter.getRepeatable()));
        }
        if (remarkStatisticsFilter.getDate() != null) {
            criteria.add(Restrictions.conjunction()
                    .add(Restrictions.ge("inspection.date", remarkStatisticsFilter.getDate()))
                    .add(Restrictions.lt("inspection.date", new Date(remarkStatisticsFilter.getDate().getTime() + TimeUnit.DAYS.toMillis(1)))));
        }
        return criteria.list();
    }
}
