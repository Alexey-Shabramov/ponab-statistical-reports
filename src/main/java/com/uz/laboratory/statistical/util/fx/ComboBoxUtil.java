package com.uz.laboratory.statistical.util.fx;


import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;
import com.uz.laboratory.statistical.util.DtoUtil;
import javafx.util.StringConverter;

public class ComboBoxUtil {
    public static final StringConverter<Stage> stageConverter = new StringConverter<Stage>() {
        @Override
        public String toString(Stage object) {
            return object.getName();
        }

        @Override
        public Stage fromString(String string) {
            return null;
        }
    };

    public static final StringConverter<Sector> sectorConverter = new StringConverter<Sector>() {
        @Override
        public String toString(Sector object) {
            return object.getTitle();
        }

        @Override
        public Sector fromString(String string) {
            return null;
        }
    };

    public static final StringConverter<InspectionTrip> inspectionTripConverter = new StringConverter<InspectionTrip>() {
        @Override
        public String toString(InspectionTrip object) {
            return DtoUtil.inspectionTripConverterTitleBuilder(object);
        }

        @Override
        public InspectionTrip fromString(String string) {
            return null;
        }
    };

    public static final StringConverter<TrackCircuit> alsSystemConverter = new StringConverter<TrackCircuit>() {
        @Override
        public String toString(TrackCircuit object) {
            return DtoUtil.alsSystemConverterTitleBuilder(object);
        }

        @Override
        public TrackCircuit fromString(String string) {
            return null;
        }
    };

    public static final StringConverter<CommunicationDistance> communicationDistanceConverter = new StringConverter<CommunicationDistance>() {
        @Override
        public String toString(CommunicationDistance object) {
            return object.getNumber().toString();
        }

        @Override
        public CommunicationDistance fromString(String string) {
            return null;
        }
    };

    public static final StringConverter<VagonLaboratory> vagonLaboratoryConverter = new StringConverter<VagonLaboratory>() {
        @Override
        public String toString(VagonLaboratory object) {
            return object.getName();
        }

        @Override
        public VagonLaboratory fromString(String string) {
            return null;
        }
    };

    public static final StringConverter<PonabSystem> ponabSystemConverter = new StringConverter<PonabSystem>() {
        @Override
        public String toString(PonabSystem object) {
            return DtoUtil.ponabSystemConverterTitleBuilder(object);
        }

        @Override
        public PonabSystem fromString(String string) {
            return null;
        }
    };

    public static final StringConverter<Station> stationConverter = new StringConverter<Station>() {
        @Override
        public String toString(Station object) {
            return object.getName();
        }

        @Override
        public Station fromString(String string) {
            return null;
        }
    };
}
