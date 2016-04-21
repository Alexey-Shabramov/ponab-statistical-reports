package com.uz.laboratory.statistical.dict;


public enum PonabSystems {
    PONAB("ПОНАБ"),
    ASDK("АСДК-Б"),
    DISK("ДИСК-Б"),
    KTSM("КТСМ-02");

    private String title;

    PonabSystems(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}
