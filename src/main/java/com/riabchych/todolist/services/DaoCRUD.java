package com.riabchych.todolist.services;

import com.riabchych.todolist.models.Task;

public interface DaoCRUD {
    Task createTask(Task task);

    Task readTask(long id);

    void updateTask(Task task);

    void deleteTask(Long id);
}
