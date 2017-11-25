package com.riabchych.todolist.services;

import com.riabchych.todolist.models.Task;

import java.time.LocalDate;

public interface ITaskService {
    Iterable<Task> getAllTasks();

    Iterable<Task> getTasksforDate(LocalDate date);
}
