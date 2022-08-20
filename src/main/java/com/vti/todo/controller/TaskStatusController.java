package com.vti.todo.controller;

import com.vti.todo.entity.TaskStatus;
import com.vti.todo.repository.ITaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task_status")
public class TaskStatusController {
    @Autowired
    ITaskStatusRepository repository;

    @GetMapping
    public List<TaskStatus> getTaskStatus() {
        return repository.findAll();
    }
}
