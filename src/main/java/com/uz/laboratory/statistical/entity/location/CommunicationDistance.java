package com.uz.laboratory.statistical.entity.location;

import com.uz.laboratory.statistical.entity.Identifier;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "communication_distance")
@DynamicUpdate(value = true)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class CommunicationDistance extends Identifier {
    @Column(name = "number")
    private Byte number;

    public Byte getNumber() {
        return number;
    }

    public void setNumber(Byte number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CommunicationDistance{" +
                "id=" + getId() +
                "number=" + number +
                '}';
    }
}
