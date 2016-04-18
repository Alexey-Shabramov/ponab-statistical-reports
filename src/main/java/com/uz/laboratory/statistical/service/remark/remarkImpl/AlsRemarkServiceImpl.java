package com.uz.laboratory.statistical.service.remark.remarkImpl;


import com.uz.laboratory.statistical.dao.remark.remarkImpl.AlsRemarkDaoImpl;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.filter.StatisticsFilter;
import com.uz.laboratory.statistical.service.remark.AlsRemarkService;
import com.uz.laboratory.statistical.service.serviceImpl.GenericServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AlsRemarkServiceImpl extends GenericServiceImpl<AlsRemark, AlsRemarkDaoImpl> implements AlsRemarkService {
    @Override
    @Transactional
    public List<AlsRemark> getRemarkListByFilter(StatisticsFilter statisticsFilter) {
        return dao.getRemarkListByFilter(statisticsFilter);
    }
}
