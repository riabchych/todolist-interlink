package com.riabchych.todolist.repositories;

import com.riabchych.todolist.models.TaskList;
import org.springframework.data.repository.CrudRepository;

public interface TaskListRepository extends CrudRepository<TaskList, Long> {
    TaskList findById(long taskListId);
}