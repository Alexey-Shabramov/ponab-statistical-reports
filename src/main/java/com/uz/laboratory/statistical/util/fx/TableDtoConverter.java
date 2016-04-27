package com.uz.laboratory.statistical.util.fx;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.tableView.PonabDevicesTableDto;
import com.uz.laboratory.statistical.dto.tableView.StatisticsRemarkTableDto;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
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
                    remark.getTrack_circuit_name().getName(),
                    remark.getNote(),
                    remark.getTrack_circuit_name().getStation() != null ? remark.getTrack_circuit_name().getStation().getName() : remark.getTrack_circuit_name().getStage().getName(),
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

    public static List<PonabDevicesTableDto> convertPonabDeviceListToDto(List<PonabSystem> ponabSystemList) {
        List<PonabDevicesTableDto> data = new ArrayList<>();
        for (PonabSystem system : ponabSystemList) {
            data.add(new PonabDevicesTableDto(
                            system.getId(),
                            system.getSector().getTitle(),
                            system.getStage().getName(),
                            system.getTitle(),
                            system.getOption(),
                            system.isSpeachInformer() ? Constants.SPEACH_INFORMATOR_TRUE : Constants.SPEACH_INFORMATOR_FALSE,
                            system.isEvenDirectionOfMovement() ? Constants.EVEN : Constants.UNEVEN
                    )
            );
        }
        return data;
    }

    public static StatisticsRemarkTableDto convertEditedPonabRemarkToTableDto(StatisticsRemarkTableDto statisticsRemarkTableDto, PonabRemark ponabRemark) {
        statisticsRemarkTableDto.setRemarkId(ponabRemark.getId().toString());
        statisticsRemarkTableDto.setObjectColumn(ponabRemark.getPonabSystem().getTitle());
        statisticsRemarkTableDto.setNoteColumn(ponabRemark.getNote());
        statisticsRemarkTableDto.setStageColumn(ponabRemark.getPonabSystem().getStage().getName());
        statisticsRemarkTableDto.setDateColumn(ponabRemark.getCreationDate().toString());
        statisticsRemarkTableDto.setVagonColumn(ponabRemark.getInspectionTrip().getVagonLaboratory().getName());
        statisticsRemarkTableDto.setRepeatColumn(ponabRemark.isRepeatable() ? "+" : "-");
        return statisticsRemarkTableDto;
    }
}
