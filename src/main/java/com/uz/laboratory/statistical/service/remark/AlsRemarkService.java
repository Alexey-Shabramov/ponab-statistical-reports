package com.uz.laboratory.statistical.service.remark;

import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.filter.StatisticsFilter;
import com.uz.laboratory.statistical.service.GenericService;

import java.util.List;


public interface AlsRemarkService extends GenericService {
    List<AlsRemark> getRemarkListByFilter(StatisticsFilter statisticsFilter);
}
