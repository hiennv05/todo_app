package com.vti.todo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "task_status")
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
