package com.riabchych.todolist.repositories;

import com.riabchych.todolist.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findById(long taskId);

    Iterable<Task> findTasksByPriority(int priority);

    Iterable<Task> findAllByOrderByPriorityAsc();

    Iterable<Task> findAllByOrderByStatusAsc();

    Iterable<Task> findTasksByIsCompleted(boolean isCompleted);
}
