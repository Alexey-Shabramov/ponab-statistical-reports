package com.uz.laboratory.statistical.dict;


public enum RemarkRepeat {
    REPEAT_TRUE("+"),
    REPEAT_FALSE("-");

    private String title;

    RemarkRepeat(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}
