package com.uz.laboratory.statistical.dict;

public enum DirectionsOfMovement {
    EVEN("Четное"),
    UNEVEN("Нечетное");

    private String label;

    DirectionsOfMovement(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
