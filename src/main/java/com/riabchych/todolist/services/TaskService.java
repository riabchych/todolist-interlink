package com.riabchych.todolist.services;

import com.riabchych.todolist.models.Priority;
import com.riabchych.todolist.models.Status;
import com.riabchych.todolist.models.Task;
import com.riabchych.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TaskService implements TaskCRUD, TaskQueries {

    @Autowired
    private TaskRepository taskRepository;

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
    public Task updateTask(Task task) {
        task.setUpdatedAt(new Date());
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    // Queries
    @Override
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Iterable<Task> filterByPriority(Priority priority) {
        return taskRepository.findTasksByPriority(priority.getValue());
    }

    @Override
    public Iterable<Task> getAllTasksOrderByPriority() {
        return taskRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public Iterable<Task> getAllTasksOrderByStatus() {
        return taskRepository.findAllByOrderByStatusAsc();
    }

    @Override
    public Iterable<Task> filterByCompleted(boolean isCompleted) {
        return taskRepository.findTasksByIsCompleted(isCompleted);
    }

    @Override
    public Task changeTaskPriority(long id, Priority priority) {
        Task t = taskRepository.findById(id);
        if (t != null) {
            t.setPriority(priority);
            return taskRepository.save(t);
        }
        return null;
    }

    @Override
    public Task changeTaskStatus(long id, Status status) {
        Task t = taskRepository.findById(id);
        if (t != null) {
            t.setStatus(status);
            if (Status.COMPLETED == status) {
                t.setCompleted(true);
            } else {
                t.setCompleted(false);
            }
            return taskRepository.save(t);
        }
        return null;
    }

}