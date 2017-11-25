package com.riabchych.todolist.controllers;

import com.riabchych.todolist.models.Task;
import com.riabchych.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/v1/")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(
            value = "/task/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity<Task> get(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskService.readTask(id), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/task",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity<Iterable<Task>> list() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskService.getAllTasks(), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/task",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Task> create(@Valid @RequestBody Task task) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskService.createTask(task), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/task/{id}",
            method = RequestMethod.PUT,
            consumes = "application/json"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@RequestBody Task updatedTask, @PathVariable("id") long id) throws IOException {
        if (id != updatedTask.getId()) {
            taskService.deleteTask(id);
        }
        taskService.updateTask(updatedTask);
    }

    @RequestMapping(
            value = "/task/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        taskService.deleteTask(id);
    }

}