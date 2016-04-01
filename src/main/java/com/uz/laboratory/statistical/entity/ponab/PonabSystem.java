package com.uz.laboratory.statistical.entity.ponab;

import com.uz.laboratory.statistical.entity.Identifier;

public class PonabSystem extends Identifier {
    private String name;
    private Short systemOption;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getSystemOption() {
        return systemOption;
    }

    public void setSystemOption(Short systemOption) {
        this.systemOption = systemOption;
    }
}
