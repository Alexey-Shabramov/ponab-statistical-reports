package com.uz.laboratory.statistical.util;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.StatisticsRemarkTableDto;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;

import java.util.ArrayList;
import java.util.List;


public class TableDtoConverter {
    public static List<StatisticsRemarkTableDto> convertAlsRemarkListToDto(List<AlsRemark> abstractRemarks) {
        List<StatisticsRemarkTableDto> data = new ArrayList<>();
        for (AlsRemark remark : abstractRemarks) {
            data.add(new StatisticsRemarkTableDto(
                    remark.getId(),
                    remark.getTrackCircuitName().getName(),
                    remark.getNote(),
                    remark.getTrackCircuitName().getStation() != null ? remark.getTrackCircuitName().getStation().getName() : remark.getTrackCircuitName().getStage().getName(),
                    remark.getCreationDate().toString(),
                    remark.getInspectionTrip().getVagonLaboratory().getName(),
                    remark.isRepeatable() ? Constants.REMARK_REPEATABLE_TRUE : Constants.REMARK_REPEATABLE_FALSE));
        }
        return data;
    }

    public static List<StatisticsRemarkTableDto> convertPonabRemarkListToDto(List<PonabRemark> abstractRemarks) {
        List<StatisticsRemarkTableDto> data = new ArrayList<>();
        for (PonabRemark remark : abstractRemarks) {
            data.add(new StatisticsRemarkTableDto(
                    remark.getId(),
                    remark.getPonabSystem().getTitle(),
                    remark.getNote(),
                    remark.getPonabSystem().getStage().getName(),
                    remark.getCreationDate().toString(),
                    remark.getInspectionTrip().getVagonLaboratory().getName(),
                    remark.isRepeatable() ? Constants.REMARK_REPEATABLE_TRUE : Constants.REMARK_REPEATABLE_FALSE));
        }
        return data;
    }
}
