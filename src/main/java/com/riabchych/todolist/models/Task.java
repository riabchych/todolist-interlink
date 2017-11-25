package com.riabchych.todolist.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
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
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column()
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column()
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate createdAt;

    @Column()
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate updatedAt;

    @Column(nullable = true, updatable = true)
    private LocalDate deadline;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isCompleted;

    public Task() {
        this.title = "";
        this.description = "";
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.deadline = LocalDate.now();
        this.isCompleted = false;
    }

    public Task(String title, String description, LocalDate createdAt, LocalDate updatedAt, LocalDate deadline, boolean isCompleted) {
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isCompleted=" + isCompleted +
                '}';
    }
}