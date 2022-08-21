package com.vti.todo.controller;

import com.vti.todo.dto.request.TaskRequest;
import com.vti.todo.dto.response.TaskResponse;
import com.vti.todo.entity.Tasks;
import com.vti.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    TaskService service;

    @GetMapping("/workspace/{workspaceId}")
    List<TaskResponse> getTaskByWorkSpace(@PathVariable Integer workspaceId){
        return service.getTaskByWorkSpace(workspaceId);
    };

    //Todo update task and
    // create task (need validate!)
    @PostMapping("/{id}")
    public void updateTaskById(@PathVariable Integer id,
                               @RequestBody @Valid TaskRequest request) {
        service.updateTaskById(id, request);
    }

    @PostMapping("")
    public Tasks createNewTask(@RequestBody @Valid TaskRequest request) {
        return service.createNewTask(request);
    }
}
