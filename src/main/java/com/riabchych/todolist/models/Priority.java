package com.riabchych.todolist.models;

public enum Priority {
    LOW(1), MEDIUM(2), HIGH(3);
    private int value;

    Priority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Priority getValueI() {
        return this;
    }
}