package com.riabchych.todolist.services;

import com.riabchych.todolist.models.TaskList;

public interface TaskListQueries {
    Iterable<TaskList> getAllTaskLists();

    TaskList changeTaskListTitle(long id, String title);

    TaskList changeTaskListColor(long id, String color);
}
