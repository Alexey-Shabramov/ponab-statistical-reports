package com.uz.laboratory.statistical.dao.remark.remarkImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.remark.PonabRemarkDao;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.filter.StatisticsFilter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class PonabRemarkDaoImpl extends GenericDaoImpl<PonabRemark> implements PonabRemarkDao {
    @Override
    public Class getEntityClass() {
        return PonabRemark.class;
    }

    @Override
    public List<PonabRemark> getRemarkListByFilter(StatisticsFilter statisticsFilter) {
        Criteria criteria = getSession().createCriteria(getEntityClass(), "remark");
        criteria.createAlias("remark.inspectionTrip", "inspection")
                .createAlias("inspection.vagonLaboratory", "vagon")
                .createAlias("inspection.tripSector", "sector")
                .createAlias("sector.stageList", "stage");
        if (statisticsFilter.getSector() != null) {
            criteria.add(Restrictions.eq("sector.id", statisticsFilter.getSector().getId()));
        }
        if (statisticsFilter.getStage() != null) {
            criteria.add(Restrictions.eq("stage.id", statisticsFilter.getStage().getId()));
        }
        if (statisticsFilter.getVagonLaboratory() != null) {
            criteria.add(Restrictions.eq("vagon.id", statisticsFilter.getVagonLaboratory().getId()));
        }
        if (statisticsFilter.getDirectionOfMovement() != null) {
            criteria.add(Restrictions.eq("remark.even", statisticsFilter.getDirectionOfMovement()));
        }
        if (statisticsFilter.getVagonLaboratory() != null) {
            criteria.add(Restrictions.eq("vagon.id", statisticsFilter.getVagonLaboratory().getId()));
        }
        if (StringUtils.isNotBlank(statisticsFilter.getDate())) {
            criteria.add(Restrictions.like("inspection.beginDate", statisticsFilter.getDate() + "%"));
        }
        return criteria.list();
    }
}
