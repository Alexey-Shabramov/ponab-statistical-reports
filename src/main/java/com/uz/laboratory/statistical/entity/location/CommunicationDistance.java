package com.uz.laboratory.statistical.entity.location;

import com.uz.laboratory.statistical.entity.Identifier;

public class CommunicationDistance extends Identifier {
    private Byte distanceNumber;

    public Byte getDistanceNumber() {
        return distanceNumber;
    }

    public void setDistanceNumber(Byte distanceNumber) {
        this.distanceNumber = distanceNumber;
    }
}
