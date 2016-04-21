package com.uz.laboratory.statistical.service.ponab;

import com.uz.laboratory.statistical.filter.PonabDevicesFilter;
import com.uz.laboratory.statistical.service.GenericService;

import java.util.List;


public interface PonabSystemService extends GenericService {
    List getRemarkListByFilter(PonabDevicesFilter ponabDevicesFilter);
}
