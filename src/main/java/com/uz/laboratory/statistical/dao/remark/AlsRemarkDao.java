package com.uz.laboratory.statistical.dao.remark;

import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;

import java.util.List;


public interface AlsRemarkDao extends GenericDao {
    List getRemarkListByFilter(RemarkStatisticsFilter remarkStatisticsFilter);
}
