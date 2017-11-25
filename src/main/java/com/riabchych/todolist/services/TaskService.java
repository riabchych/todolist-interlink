package com.riabchych.todolist.services;

import com.riabchych.todolist.models.Task;
import com.riabchych.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskService implements DaoCRUD, ITaskService {

    //To do
    @Autowired
    private TaskRepository taskRepository;

    public TaskService() {
    }

    // CRUD
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task readTask(long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public Iterable<Task> getAllTasks() {
        Iterable pageOfTask = taskRepository.findAll();
        return pageOfTask;
    }

    @Override
    public Iterable<Task> getTasksforDate(LocalDate date) {
        Iterable<Task> pageOfTask = taskRepository.findTaskByDate(date);
        return pageOfTask;
    }

}