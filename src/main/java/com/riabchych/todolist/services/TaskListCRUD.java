package com.riabchych.todolist.services;

import com.riabchych.todolist.models.TaskList;

public interface TaskListCRUD {
    TaskList createTaskList(TaskList taskList);

    TaskList readTaskList(long id);

    TaskList updateTaskList(TaskList taskList);

    void deleteTaskList(Long id);
}
