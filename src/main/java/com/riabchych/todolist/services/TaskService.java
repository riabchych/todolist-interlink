package com.riabchych.todolist.services;

import com.riabchych.todolist.models.Task;
import com.riabchych.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService implements DaoCRUD, TaskQueries {

    //To do
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // CRUD
    @Transactional
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task readTask(long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    @Override
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}