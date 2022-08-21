package com.vti.todo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String password;

    @Column(name= "full_name", nullable = false)
    private String fullName;

    private String lang;

}
