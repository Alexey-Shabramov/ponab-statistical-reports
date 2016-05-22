package com.uz.laboratory.statistical.util;


import com.uz.laboratory.statistical.dto.als.AlsRemarkDto;
import com.uz.laboratory.statistical.dto.ponab.PonabRemarkDto;
import com.uz.laboratory.statistical.dto.tableView.StatisticsRemarkTableDto;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;

import java.util.ArrayList;
import java.util.List;

public class DtoUtil {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static String inspectionTripConverterTitleBuilder(InspectionTrip inspectionTrip) {
        try {
            return stringBuilder.append(inspectionTrip.getTripSector().getTitle())
                    .append(" - ")
                    .append("Вагон: ")
                    .append(inspectionTrip.getVagonLaboratory().getName())
                    .append(" - ")
                    .append(inspectionTrip.getBeginDate())
                    .toString();
        } finally {
            stringBuilder.setLength(0);
        }
    }

    public static String ponabSystemConverterTitleBuilder(PonabSystem ponabSystem) {
        try {
            return stringBuilder.append(ponabSystem.getTitle())
                    .append("(")
                    .append(ponabSystem.getOption())
                    .append(")")
                    .append(" - ")
                    .append(ponabSystem.getLocation())
                    .append(" - ")
                    .append(ponabSystem.isEvenDirectionOfMovement() ? "Четный" : "Нечетный")
                    .toString();
        } finally {
            stringBuilder.setLength(0);
        }
    }

    public static String alsSystemConverterTitleBuilder(TrackCircuit trackCircuit) {
        try {
            return stringBuilder.append(trackCircuit.getName())
                    .append(" - ")
                    .append(trackCircuit.getPicket())
                    .toString();
        } finally {
            stringBuilder.setLength(0);
        }
    }


    public static List<String> remarkDtoAsList(StatisticsRemarkTableDto statisticsRemarkTableDto) {
        List<String> remarkValuesList = new ArrayList<>();
        remarkValuesList.add(statisticsRemarkTableDto.getRemarkId());
        remarkValuesList.add(statisticsRemarkTableDto.getVagonColumn());
        remarkValuesList.add(statisticsRemarkTableDto.getDateColumn());
        remarkValuesList.add(statisticsRemarkTableDto.getObjectColumn());
        remarkValuesList.add(statisticsRemarkTableDto.getSectorColumn());
        remarkValuesList.add(statisticsRemarkTableDto.getStageColumn());
        remarkValuesList.add(statisticsRemarkTableDto.getRepeatColumn());
        remarkValuesList.add(statisticsRemarkTableDto.getNoteColumn());
        return remarkValuesList;
    }

    public static void convertPonabRemarkToDto(PonabRemark ponabRemark, PonabRemarkDto ponabRemarkDto) {
        ponabRemarkDto.setId(ponabRemark.getId());
        ponabRemarkDto.setCreationDate(ponabRemark.getCreationDate());
        ponabRemarkDto.setEven(ponabRemark.isEven());
        ponabRemarkDto.setInspectionTrip(ponabRemark.getInspectionTrip());
        ponabRemarkDto.setNote(ponabRemark.getNote());
        ponabRemarkDto.setPonabSystem(ponabRemark.getPonabSystem());
        ponabRemarkDto.setRepeatable(ponabRemark.isRepeatable());
    }

    public static void convertAlsRemarkToDto(AlsRemark alsRemark, AlsRemarkDto alsRemarkDto) {
        alsRemarkDto.setId(alsRemark.getId());
        alsRemarkDto.setRepeatable(alsRemark.isRepeatable());
        alsRemarkDto.setNote(alsRemark.getNote());
        alsRemarkDto.setInspectionTrip(alsRemark.getInspectionTrip());
        alsRemarkDto.setCreationDate(alsRemark.getCreationDate());
        alsRemarkDto.setTrackCircuit(alsRemark.getTrackCircuit());
    }

}
