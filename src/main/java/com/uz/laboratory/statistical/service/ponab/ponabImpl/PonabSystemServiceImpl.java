package com.uz.laboratory.statistical.service.ponab.ponabImpl;


import com.uz.laboratory.statistical.dao.ponab.ponabImpl.PonabSystemDaoImpl;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.filter.PonabDevicesFilter;
import com.uz.laboratory.statistical.service.ponab.PonabSystemService;
import com.uz.laboratory.statistical.service.serviceImpl.GenericServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PonabSystemServiceImpl extends GenericServiceImpl<PonabSystem, PonabSystemDaoImpl> implements PonabSystemService {
    @Override
    @Transactional
    public List getRemarkListByFilter(PonabDevicesFilter ponabDevicesFilter) {
        return dao.getRemarkListByFilter(ponabDevicesFilter);
    }

    @Override
    @Transactional
    public List getPonabSystemsByStage(Stage stage) {
        return dao.getPonabSystemsByStage(stage);
    }

    @Override
    @Transactional
    public List getPonabSystemsBySector(Sector sector) {
        return dao.getPonabSystemsBySector(sector);
    }
}
