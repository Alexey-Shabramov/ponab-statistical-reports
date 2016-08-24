package com.uz.laboratory.statistical.dao.thermal.thermalImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.thermal.ThermalSettingsDao;
import com.uz.laboratory.statistical.entity.settings.ThermalSettings;


public class ThermalSettingsDaoImpl extends GenericDaoImpl<ThermalSettings> implements ThermalSettingsDao {
    @Override
    public Class getEntityClass() {
        return ThermalSettings.class;
    }
}
