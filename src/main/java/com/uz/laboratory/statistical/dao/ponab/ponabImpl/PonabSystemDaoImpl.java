package com.uz.laboratory.statistical.dao.ponab.ponabImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.ponab.PonabSystemDao;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.filter.PonabDevicesFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class PonabSystemDaoImpl extends GenericDaoImpl<PonabSystem> implements PonabSystemDao {
    @Override
    public Class getEntityClass() {
        return PonabSystem.class;
    }

    @Override
    public List getRemarkListByFilter(PonabDevicesFilter ponabDevicesFilter) {
        Criteria criteria = getSession().createCriteria(getEntityClass(), "system");
        criteria.createAlias("system.sector", "sector")
                .createAlias("sector.stageList", "stagesList")
                .createAlias("system.stage", "stage")
                .createAlias("stage.distanceList", "distances");
        if (ponabDevicesFilter.getSector() != null) {
            criteria.add(Restrictions.eq("sector.id", ponabDevicesFilter.getSector().getId()));
        }
        if (ponabDevicesFilter.getStage() != null) {
            criteria.add(Restrictions.eq("stage.id", ponabDevicesFilter.getStage().getId()));
        } else if (ponabDevicesFilter.getCommunicationDistance() != null) {
            criteria.add(Restrictions.eq("distances.id", ponabDevicesFilter.getCommunicationDistance().getId()));
        }
        if (ponabDevicesFilter.getPonabSystem() != null) {
            criteria.add(Restrictions.eq("system.title", ponabDevicesFilter.getPonabSystem().toString()));
        }
        if (ponabDevicesFilter.getOption() != null) {
            criteria.add(Restrictions.eq("system.option", ponabDevicesFilter.getOption()));
        }
        if (ponabDevicesFilter.getEvenDirectionOfMovement() != null) {
            criteria.add(Restrictions.eq("system.evenDirectionOfMovement", ponabDevicesFilter.getEvenDirectionOfMovement()));
        }
        if (ponabDevicesFilter.getSpeachInformer() != null) {
            criteria.add(Restrictions.eq("system.speachInformer", ponabDevicesFilter.getSpeachInformer()));
        }
        return criteria.list();
    }

    @Override
    public List getPonabSystemsByStage(Stage stage) {
        return getSession().createCriteria(getEntityClass(), "system")
                .createAlias("system.stage", "stage")
                .add(Restrictions.eq("stage.id", stage.getId()))
                .list();
    }

    @Override
    public List getPonabSystemsBySector(Sector sector) {
        return getSession().createCriteria(getEntityClass(), "system")
                .createAlias("system.sector", "sector")
                .add(Restrictions.eq("sector.id", sector.getId()))
                .list();
    }
}
