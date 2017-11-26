package com.riabchych.todolist.services;

import com.riabchych.todolist.models.TaskList;
import com.riabchych.todolist.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskListService implements TaskListCRUD, TaskListQueries {

    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    @Transactional
    public TaskList createTaskList(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    @Override
    public TaskList readTaskList(long id) {
        return taskListRepository.findById(id);
    }

    @Override
    @Transactional
    public TaskList updateTaskList(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    @Override
    @Transactional
    public void deleteTaskList(Long id) {
        taskListRepository.delete(id);
    }

    @Override
    public Iterable<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList changeTaskListTitle(long id, String title) {
        TaskList ts = taskListRepository.findById(id);
        if (ts != null) {
            ts.setTitle(title);
            return taskListRepository.save(ts);
        }
        return null;
    }

    @Override
    public TaskList changeTaskListColor(long id, String color) {
        TaskList ts = taskListRepository.findById(id);
        if (ts != null) {
            ts.setColor(color);
            return taskListRepository.save(ts);
        }
        return null;
    }
}
