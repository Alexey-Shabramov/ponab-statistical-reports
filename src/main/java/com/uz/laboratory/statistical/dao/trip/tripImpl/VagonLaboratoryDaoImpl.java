package com.uz.laboratory.statistical.dao.trip.tripImpl;


import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.trip.VagonLaboratoryDao;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;

public class VagonLaboratoryDaoImpl extends GenericDaoImpl<VagonLaboratory> implements VagonLaboratoryDao {
    @Override
    public Class getEntityClass() {
        return VagonLaboratory.class;
    }
}
