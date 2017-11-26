package com.riabchych.todolist.models;

import java.io.Serializable;

public class PriorityResponse implements Serializable {

    private Priority priority;

    public PriorityResponse(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
