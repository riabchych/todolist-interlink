package com.riabchych.todolist.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tasks")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Task implements Serializable {

    @Column()
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    @NotBlank
    private String title;

    @Column()
    private String description;

    @Column()
    private int priority;

    @Column()
    private int status;

    @Column()
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column()
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column(nullable = true, updatable = true)
    private Date deadline;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isCompleted;

    @ManyToOne
    private TaskList taskList;

    public Task() {
        this.title = "";
        this.description = "";
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.deadline = new Date();
        this.isCompleted = false;
    }

    public Task(String title, String description, Date createdAt, Date updatedAt, Date deadline, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority.getValue();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status.getValue();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}