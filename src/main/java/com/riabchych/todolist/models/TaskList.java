package com.riabchych.todolist.models;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lists")
public class TaskList implements Serializable {

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    @NotBlank
    private String title;

    @Column()
    @NotBlank
    private String color;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}