package com.uz.laboratory.statistical.entity.location;


import com.uz.laboratory.statistical.entity.Identifier;

public class Station extends Identifier {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
