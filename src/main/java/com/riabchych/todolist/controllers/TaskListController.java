package com.riabchych.todolist.controllers;

import com.riabchych.todolist.models.TaskList;
import com.riabchych.todolist.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/v1/")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;

    public TaskListController() {
        this.taskListService = new TaskListService();
    }

    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity<Iterable<TaskList>> list() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskListService.getAllTaskLists(), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/list/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity<TaskList> get(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskListService.readTaskList(id), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<TaskList> create(@Valid @RequestBody TaskList taskList) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskListService.createTaskList(taskList), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/list/{id}",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json"
    )
    @Transactional
    public ResponseEntity<TaskList> update(@PathVariable("id") long id, @RequestBody TaskList taskList) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskListService.updateTaskList(taskList), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/list/{id}/color",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json"
    )
    @Transactional
    public ResponseEntity<TaskList> updateColor(@PathVariable("id") long id, @RequestBody TaskList taskList) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskListService.changeTaskListColor(id, taskList.getColor()), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/list/{id}/title",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json"
    )
    @Transactional
    public ResponseEntity<TaskList> updateTitle(@PathVariable("id") long id, @RequestBody TaskList taskList) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Patch", "application/json-patch+json");
        return new ResponseEntity<>(taskListService.changeTaskListTitle(id, taskList.getTitle()), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/list/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<TaskList> delete(@PathVariable Long id) {
        taskListService.deleteTaskList(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}