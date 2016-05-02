package com.uz.laboratory.statistical.dto;


import com.uz.laboratory.statistical.dto.tableView.StatisticsRemarkTableDto;

import java.util.List;

public class RemarkTableListSaveDto {
    private List<StatisticsRemarkTableDto> statisticsRemarkTableDtos;

    public List<StatisticsRemarkTableDto> getStatisticsRemarkTableDtos() {
        return statisticsRemarkTableDtos;
    }

    public void setStatisticsRemarkTableDtos(List<StatisticsRemarkTableDto> statisticsRemarkTableDtos) {
        this.statisticsRemarkTableDtos = statisticsRemarkTableDtos;
    }
}
