package com.uz.laboratory.statistical.util.fx;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dict.TrackCircuitTypes;
import com.uz.laboratory.statistical.dto.tableView.AlsDevicesTableDto;
import com.uz.laboratory.statistical.dto.tableView.PonabDevicesTableDto;
import com.uz.laboratory.statistical.dto.tableView.StatisticsRemarkTableDto;
import com.uz.laboratory.statistical.dto.tableView.TripsTableDto;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;


public class TableDtoConverter {
    private static final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("dd.MM.yyyy").toFormatter();


    public static List<StatisticsRemarkTableDto> convertAlsRemarkListToDto(List<AlsRemark> abstractRemarks) {
        List<StatisticsRemarkTableDto> data = new ArrayList<>();
        for (AlsRemark remark : abstractRemarks) {
            data.add(new StatisticsRemarkTableDto(
                    remark.getId(),
                    remark.getTrackCircuit().getName(),
                    remark.getNote(),
                    remark.getInspectionTrip().getTripSector().getTitle(),
                    remark.getTrackCircuit().getStation() != null ? remark.getTrackCircuit().getStation().getName() : remark.getTrackCircuit().getStage().getName(),
                    remark.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter),
                    remark.getInspectionTrip().getVagonLaboratory().getName(),
                    remark.isRepeatable() ? Constants.REMARK_REPEATABLE_TRUE : Constants.REMARK_REPEATABLE_FALSE));
        }
        return data;
    }

    public static List<TripsTableDto> convertInspectionTripsListToDto(List<InspectionTrip> inspectionTrips) {
        List<TripsTableDto> data = new ArrayList<>();
        for (InspectionTrip inspectionTrip : inspectionTrips) {
            data.add(new TripsTableDto(
                    inspectionTrip.getId().toString(),
                    inspectionTrip.getTripSector().getTitle(),
                    inspectionTrip.getVagonLaboratory().getName(),
                    inspectionTrip.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter)));
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
                    remark.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter),
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

    public static List<AlsDevicesTableDto> convertAlsDeviceListToDto(List<TrackCircuit> trackCircuitList) {
        List<AlsDevicesTableDto> data = new ArrayList<>();
        for (TrackCircuit system : trackCircuitList) {
            data.add(new AlsDevicesTableDto(
                    system.getId().toString(),
                    system.getName(),
                    system.getSector().getTitle(),
                    system.isStationalCircuit() ? system.getStation().getName() : system.getStage().getName(),
                    system.isEven() ? Constants.EVEN : Constants.UNEVEN,
                    system.isStationalCircuit() ? TrackCircuitTypes.STATION.toString() : TrackCircuitTypes.STAGE.toString(),
                    system.getPicket() != null ? system.getPicket().toString() : "-"));
        }
        return data;
    }

    public static StatisticsRemarkTableDto convertEditedPonabRemarkToTableDto(StatisticsRemarkTableDto statisticsRemarkTableDto, PonabRemark ponabRemark) {
        statisticsRemarkTableDto.setRemarkId(ponabRemark.getId().toString());
        statisticsRemarkTableDto.setObjectColumn(ponabRemark.getPonabSystem().getTitle());
        statisticsRemarkTableDto.setNoteColumn(ponabRemark.getNote());
        statisticsRemarkTableDto.setStageColumn(ponabRemark.getPonabSystem().getStage().getName());
        statisticsRemarkTableDto.setDateColumn(ponabRemark.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter));
        statisticsRemarkTableDto.setVagonColumn(ponabRemark.getInspectionTrip().getVagonLaboratory().getName());
        statisticsRemarkTableDto.setRepeatColumn(ponabRemark.isRepeatable() ? "+" : "-");
        return statisticsRemarkTableDto;
    }

    public static StatisticsRemarkTableDto convertEditedAlsRemarkToTableDto(StatisticsRemarkTableDto statisticsRemarkTableDto, AlsRemark alsRemark) {
        statisticsRemarkTableDto.setRemarkId(alsRemark.getId().toString());
        statisticsRemarkTableDto.setObjectColumn(alsRemark.getTrackCircuit().getName());
        statisticsRemarkTableDto.setNoteColumn(alsRemark.getNote());
        statisticsRemarkTableDto.setStageColumn(alsRemark.getTrackCircuit().getStage().getName());
        statisticsRemarkTableDto.setDateColumn(alsRemark.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter));
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

    public static AlsDevicesTableDto convertEditedAlsSystemToTableDto(AlsDevicesTableDto alsDevicesTableDto, TrackCircuit trackCircuit) {
        alsDevicesTableDto.setDeviceId(trackCircuit.getId().toString());
        alsDevicesTableDto.setSectorTitle(trackCircuit.getSector().getTitle());
        alsDevicesTableDto.setStageOrStationTitle(trackCircuit.isStationalCircuit() ? trackCircuit.getStation().getName() : trackCircuit.getStage().getName());
        alsDevicesTableDto.setDirectionOfMovement(trackCircuit.isEven() ? Constants.EVEN : Constants.UNEVEN);
        alsDevicesTableDto.setPicketNumber(trackCircuit.getPicket() != null ? trackCircuit.getPicket().toString() : null);
        alsDevicesTableDto.setTrackCircuitName(trackCircuit.getName());
        alsDevicesTableDto.setTrackCircuitType(trackCircuit.isStationalCircuit() ? TrackCircuitTypes.STATION.toString() : TrackCircuitTypes.STAGE.toString());
        return alsDevicesTableDto;
    }

    public static TripsTableDto convertEditedTripsToTableDto(TripsTableDto tripsTableDto, InspectionTrip inspectionTrip) {
        tripsTableDto.setDeviceId(inspectionTrip.getId().toString());
        tripsTableDto.setSectorTitle(inspectionTrip.getTripSector().getTitle());
        tripsTableDto.setDate(inspectionTrip.getDate().toString());
        tripsTableDto.setVagonLaboratoryTitle(inspectionTrip.getVagonLaboratory().getName());
        return tripsTableDto;
    }


}
