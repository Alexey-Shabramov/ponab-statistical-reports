package com.uz.laboratory.statistical.entity.location;


import com.uz.laboratory.statistical.entity.Identifier;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "station")
@DynamicUpdate(value = true)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Station extends Identifier {


    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
