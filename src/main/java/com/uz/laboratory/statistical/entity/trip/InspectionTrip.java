package com.uz.laboratory.statistical.entity.trip;

import com.uz.laboratory.statistical.entity.Identifier;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inspection_trip")
@DynamicUpdate(value = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
})
public class InspectionTrip extends Identifier {
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_sector")
        private Sector tripSector;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_vagon_laboratory")
        private VagonLaboratory vagonLaboratory;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "date", nullable = true)
        private Date date;

        @Column(name = "planned_trip")
        private boolean plannedTrip;

        @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "inspectionTrip")
        private List<AlsRemark> alsRemarkList;

        @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "inspectionTrip")
        private List<PonabRemark> ponabRemarkList;

        public boolean isPlannedTrip() {
                return plannedTrip;
        }

        public void setPlannedTrip(boolean plannedTrip) {
                this.plannedTrip = plannedTrip;
        }

        public Sector getTripSector() {
                return tripSector;
        }

        public void setTripSector(Sector tripSector) {
                this.tripSector = tripSector;
        }

        public VagonLaboratory getVagonLaboratory() {
                return vagonLaboratory;
        }

        public void setVagonLaboratory(VagonLaboratory vagonLaboratory) {
                this.vagonLaboratory = vagonLaboratory;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public List<AlsRemark> getAlsRemarkList() {
                return alsRemarkList;
        }

        public void setAlsRemarkList(List<AlsRemark> alsRemarkList) {
                this.alsRemarkList = alsRemarkList;
        }

        public List<PonabRemark> getPonabRemarkList() {
                return ponabRemarkList;
        }

        public void setPonabRemarkList(List<PonabRemark> ponabRemarkList) {
                this.ponabRemarkList = ponabRemarkList;
        }

        @Override
        public String toString() {
                return "InspectionTrip{" +
                        "id=" + getId() +
                        "tripSector=" + tripSector +
                        ", vagonLaboratory=" + vagonLaboratory +
                        ", date=" + date +
                        ", plannedTrip=" + plannedTrip +
                        ", alsRemarkList=" + alsRemarkList +
                        ", ponabRemarkList=" + ponabRemarkList +
                        '}';
        }
}
