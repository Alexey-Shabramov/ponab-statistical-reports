package com.uz.laboratory.statistical.dict;


public enum PonabOptions {
    FIRST("100"),
    SECOND("120"),
    THIRD("140"),
    FOURHT("160");

    private String title;

    PonabOptions(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}
