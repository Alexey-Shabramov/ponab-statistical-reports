package com.uz.laboratory.statistical.service.remark;

import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;
import com.uz.laboratory.statistical.service.GenericService;

import java.util.List;


public interface PonabRemarkService extends GenericService {
    List<PonabRemark> getRemarkListByFilter(RemarkStatisticsFilter remarkStatisticsFilter);
}
