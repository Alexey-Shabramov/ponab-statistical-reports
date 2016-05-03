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
                    remark.getTrackCircuit().getName(),
                    remark.getNote(),
                    remark.getInspectionTrip().getTripSector().getTitle(),
                    remark.getTrackCircuit().getStation() != null ? remark.getTrackCircuit().getStation().getName() : remark.getTrackCircuit().getStage().getName(),
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
                    remark.getInspectionTrip().getTripSector().getTitle(),
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

    public static StatisticsRemarkTableDto convertEditedAlsRemarkToTableDto(StatisticsRemarkTableDto statisticsRemarkTableDto, AlsRemark alsRemark) {
        statisticsRemarkTableDto.setRemarkId(alsRemark.getId().toString());
        statisticsRemarkTableDto.setObjectColumn(alsRemark.getTrackCircuit().getName());
        statisticsRemarkTableDto.setNoteColumn(alsRemark.getNote());
        statisticsRemarkTableDto.setStageColumn(alsRemark.getTrackCircuit().getStage().getName());
        statisticsRemarkTableDto.setDateColumn(alsRemark.getCreationDate().toString());
        statisticsRemarkTableDto.setVagonColumn(alsRemark.getInspectionTrip().getVagonLaboratory().getName());
        statisticsRemarkTableDto.setRepeatColumn(alsRemark.isRepeatable() ? "+" : "-");
        return statisticsRemarkTableDto;
    }

    public static PonabDevicesTableDto convertEditedPonabSystemToTableDto(PonabDevicesTableDto ponabDevicesTableDto, PonabSystem ponabSystem) {
        ponabDevicesTableDto.setDeviceId(ponabSystem.getId().toString());
        ponabDevicesTableDto.setOption(ponabSystem.getOption());
        ponabDevicesTableDto.setStageTitle(ponabSystem.getStage().getName());
        ponabDevicesTableDto.setSectorTitle(ponabSystem.getSector().getTitle());
        ponabDevicesTableDto.setDirectionOfMovement(ponabSystem.isEvenDirectionOfMovement() ? Constants.EVEN : Constants.UNEVEN);
        ponabDevicesTableDto.setSystemTitle(ponabSystem.getTitle());
        ponabDevicesTableDto.setSpeachInformator(ponabSystem.isSpeachInformer() ? "+" : "-");
        return ponabDevicesTableDto;
    }
}
