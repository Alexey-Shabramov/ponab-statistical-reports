package com.uz.laboratory.statistical.dao.location.locationImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.location.CommunicationDistanceDao;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;


public class CommunicationDistanceDaoImpl extends GenericDaoImpl<CommunicationDistance> implements CommunicationDistanceDao {
    @Override
    public Class getEntityClass() {
        return CommunicationDistance.class;
    }
}
