package com.uz.laboratory.statistical.entity.trip;

import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "inspection_trip")
@DynamicUpdate(value = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "tripSector", column = @Column(name = "id_sector")),
        @AttributeOverride(name = "vagonLaboratory", column = @Column(name = "id_vagon_laboratory")),
        @AttributeOverride(name = "beginDate", column = @Column(name = "begin_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "end_date"))
})
public class InspectionTrip extends AbstractTrip {
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "id_als_remark")
        private List<AlsRemark> alsRemarkList;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "id_ponab_remark")
        private List<PonabRemark> ponabRemarkList;

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
}
