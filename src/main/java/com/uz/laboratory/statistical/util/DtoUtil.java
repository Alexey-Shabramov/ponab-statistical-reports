package com.uz.laboratory.statistical.util;


import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;

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
}
