package com.uz.laboratory.statistical.dao.remark.remarkImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.remark.PonabRemarkDao;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class PonabRemarkDaoImpl extends GenericDaoImpl<PonabRemark> implements PonabRemarkDao {
    @Override
    public Class getEntityClass() {
        return PonabRemark.class;
    }

    @Override
    public List<PonabRemark> getRemarkListByFilter(RemarkStatisticsFilter remarkStatisticsFilter) {
        Criteria criteria = getSession().createCriteria(getEntityClass(), "remark");
        criteria.createAlias("remark.inspectionTrip", "inspection")
                .createAlias("inspection.vagonLaboratory", "vagon")
                .createAlias("inspection.tripSector", "sector")
                .createAlias("sector.stageList", "stage");
        if (remarkStatisticsFilter.getSector() != null) {
            criteria.add(Restrictions.eq("sector.id", remarkStatisticsFilter.getSector().getId()));
        }
        if (remarkStatisticsFilter.getStage() != null) {
            criteria.add(Restrictions.eq("stage.id", remarkStatisticsFilter.getStage().getId()));
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
        if (remarkStatisticsFilter.getDate() != null) {
            criteria.add(Restrictions.like("inspection.beginDate", remarkStatisticsFilter.getDate() + "%"));
        }
        return criteria.list();
    }
}
