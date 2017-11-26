package com.riabchych.todolist.models;

import java.io.Serializable;

public class StatusResponse implements Serializable {

    private Status status;

    public StatusResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
