package com.riabchych.todolist.services;

import com.riabchych.todolist.models.Task;

public interface TaskCRUD {
    Task createTask(Task task);

    Task readTask(long id);

    Task updateTask(Task task);

    void deleteTask(Long id);
}
