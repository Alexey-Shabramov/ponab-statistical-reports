package com.uz.laboratory.statistical.service.remark.remarkImpl;


import com.uz.laboratory.statistical.dao.remark.remarkImpl.PonabRemarkDaoImpl;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;
import com.uz.laboratory.statistical.service.remark.PonabRemarkService;
import com.uz.laboratory.statistical.service.serviceImpl.GenericServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PonabRemarkServiceImpl extends GenericServiceImpl<PonabRemark, PonabRemarkDaoImpl> implements PonabRemarkService {
    @Override
    @Transactional
    public List<PonabRemark> getRemarkListByFilter(RemarkStatisticsFilter remarkStatisticsFilter) {
        return dao.getRemarkListByFilter(remarkStatisticsFilter);
    }
}
