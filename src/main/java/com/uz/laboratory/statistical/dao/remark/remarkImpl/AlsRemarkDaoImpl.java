package com.uz.laboratory.statistical.dao.remark.remarkImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.remark.AlsRemarkDao;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.filter.StatisticsFilter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class AlsRemarkDaoImpl extends GenericDaoImpl<AlsRemark> implements AlsRemarkDao {
    @Override
    public Class getEntityClass() {
        return AlsRemark.class;
    }

    @Override
    public List getRemarkListByFilter(StatisticsFilter statisticsFilter) {
        Criteria criteria = getSession().createCriteria(getEntityClass(), "alsRemark");
        if (statisticsFilter.getSector() != null) {
            criteria.createAlias("alsRemark.inspectionTrip.tripSector", "sector")
                    .add(Restrictions.eq("sector.id", statisticsFilter.getSector().getId()));
        }
        if (statisticsFilter.getStage() != null) {
            criteria.createAlias("sector.stageList", "stage")
                    .add(Restrictions.eq("stage.id", statisticsFilter.getStage().getId()));
        }
        if (statisticsFilter.getVagonLaboratory() != null) {
            criteria.createAlias("alsRemark.inspectionTrip.vagonLaboratory", "vagon")
                    .add(Restrictions.eq("vagon.id", statisticsFilter.getStage().getId()));
        }
        if (statisticsFilter.getDirectionOfMovement() != null) {
            criteria.createAlias("alsRemark.even", "directionOfMovement")
                    .add(Restrictions.eq("directionOfMovement", statisticsFilter.getDirectionOfMovement()));
        }
        if (StringUtils.isNotBlank(statisticsFilter.getDate())) {
            criteria.createAlias("alsRemark.inspectionTrip.beginDate", "date")
                    .add(Restrictions.like("date", statisticsFilter.getDate()));
        }
        return criteria.list();
    }
}
