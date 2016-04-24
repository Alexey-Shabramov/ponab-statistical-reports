package com.uz.laboratory.statistical.dao.ponab;


import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.filter.PonabDevicesFilter;

import java.util.List;


public interface PonabSystemDao extends GenericDao {
    List getRemarkListByFilter(PonabDevicesFilter ponabDevicesFilter);

    List getPonabSystemsByStage(Stage stage);

    List getPonabSystemsBySector(Sector sector);

}
