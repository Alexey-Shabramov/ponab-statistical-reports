package com.uz.laboratory.statistical.dict;


public enum Systems {
    ALSN("АЛС"),
    PONAB("ПАВПБ");

    private String title;

    Systems(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}
