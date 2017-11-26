package com.riabchych.todolist.services;

import com.riabchych.todolist.models.Priority;
import com.riabchych.todolist.models.Status;
import com.riabchych.todolist.models.Task;

public interface TaskQueries {
    Iterable<Task> getAllTasks();

    Iterable<Task> filterByPriority(Priority priority);

    Iterable<Task> getAllTasksOrderByPriority();

    Iterable<Task> getAllTasksOrderByStatus();

    Iterable<Task> filterByCompleted(boolean isCompleted);

    Task changeTaskPriority(long id, Priority priority);

    Task changeTaskStatus(long id, Status status);
}
