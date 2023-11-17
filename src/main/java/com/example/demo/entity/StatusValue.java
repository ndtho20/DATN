package com.example.demo.entity;

public class StatusValue {
    private int value;

    private String label;

    public StatusValue(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }
}
