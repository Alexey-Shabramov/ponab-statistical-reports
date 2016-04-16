package com.uz.laboratory.statistical.dao.remark;

import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.filter.StatisticsFilter;

import java.util.List;


public interface PonabRemarkDao extends GenericDao {
    List<PonabRemark> getRemarkListByFilter(StatisticsFilter statisticsFilter);
}
