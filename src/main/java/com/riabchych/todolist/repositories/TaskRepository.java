package com.riabchych.todolist.repositories;

import com.riabchych.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Iterable<Task> findTaskByDate(LocalDate date);
}
