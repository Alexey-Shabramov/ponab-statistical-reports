package com.uz.laboratory.statistical.dao.remark;

import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.filter.StatisticsFilter;

import java.util.List;


public interface AlsRemarkDao extends GenericDao {
    List getRemarkListByFilter(StatisticsFilter statisticsFilter);
}
