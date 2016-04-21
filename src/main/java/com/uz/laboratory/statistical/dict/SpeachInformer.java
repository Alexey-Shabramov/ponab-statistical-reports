package com.uz.laboratory.statistical.dict;


public enum SpeachInformer {
    ENABLED("Есть"),
    DISABLED("Нет");

    private String title;

    SpeachInformer(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}
