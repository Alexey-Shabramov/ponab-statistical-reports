package com.uz.laboratory.statistical.entity.location;


import com.uz.laboratory.statistical.entity.Identifier;

import java.util.List;

public class Sector extends Identifier {
    private String sectorTitle;
    private List<Stage> stageList;

    public List<Stage> getStageList() {
        return stageList;
    }

    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }

    public String getSectorTitle() {
        return sectorTitle;
    }

    public void setSectorTitle(String sectorTitle) {
        this.sectorTitle = sectorTitle;
    }
}
