package com.uz.laboratory.statistical.service.remark.remarkImpl;


import com.uz.laboratory.statistical.dao.remark.remarkImpl.PonabRemarkDaoImpl;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.filter.StatisticsFilter;
import com.uz.laboratory.statistical.service.remark.PonabRemarkService;
import com.uz.laboratory.statistical.service.serviceImpl.GenericServiceImpl;

import java.util.List;

public class PonabRemarkServiceImpl extends GenericServiceImpl<PonabRemark, PonabRemarkDaoImpl> implements PonabRemarkService {
    @Override
    public List<PonabRemark> getRemarkListByFilter(StatisticsFilter statisticsFilter) {
        return dao.getRemarkListByFilter(statisticsFilter);
    }
}
