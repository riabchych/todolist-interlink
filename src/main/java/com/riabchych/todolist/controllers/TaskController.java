package com.riabchych.todolist.controllers;

import com.riabchych.todolist.models.Priority;
import com.riabchych.todolist.models.PriorityResponse;
import com.riabchych.todolist.models.StatusResponse;
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

    @Autowired
    private TaskService taskService;

    public TaskController() {
        this.taskService = new TaskService();
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
            value = "/task/sort/priority",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity<Iterable<Task>> listOrderByPriority() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskService.getAllTasksOrderByPriority(), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/task/sort/status",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity<Iterable<Task>> listOrderByStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskService.getAllTasksOrderByStatus(), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/task/filter/{filter}/{value}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity<Iterable<Task>> filter(@PathVariable String filter, @PathVariable String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        Iterable<Task> result = null;
        switch (filter) {
            case "priority":
                result = taskService.filterByPriority(Priority.valueOf(value.toUpperCase()));
                break;
            case "completed":
                result = taskService.filterByCompleted(Boolean.valueOf(value));
                break;
            default:
                result = taskService.getAllTasks();
                break;
        }
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
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
            consumes = "application/json",
            produces = "application/json"
    )
    @Transactional
    public ResponseEntity<Task> update(@Valid @RequestBody Task updatedTask, @PathVariable("id") long id) throws IOException {
        if (id != updatedTask.getId()) {
            taskService.deleteTask(id);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskService.updateTask(updatedTask), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/task/{id}/status",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json"
    )
    @Transactional
    public ResponseEntity<Task> updateStatus(@PathVariable("id") long id, @RequestBody StatusResponse status) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskService.changeTaskStatus(id, status.getStatus()), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/task/{id}/priority",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json"
    )
    @Transactional
    public ResponseEntity<Task> updatePriority(@PathVariable("id") long id, @RequestBody PriorityResponse priority) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskService.changeTaskPriority(id, priority.getPriority()), headers, HttpStatus.OK);
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