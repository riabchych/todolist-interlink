package com.riabchych.todolist.models;

public enum Status {
    PENDING(1), PAUSED(2), COMPLETED(3);
    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Status getValueI() {
        return this;
    }
}