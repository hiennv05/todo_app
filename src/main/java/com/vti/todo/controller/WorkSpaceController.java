package com.vti.todo.controller;

import com.vti.todo.dto.request.WorkSpaceRequest;
import com.vti.todo.dto.response.WorkSpaceResponse;
import com.vti.todo.entity.WorkSpace;
import com.vti.todo.service.IWorkSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/workspaces")
public class WorkSpaceController {
    @Autowired
    IWorkSpaceService service;

    @PostMapping
    public WorkSpaceResponse createWorkSpaces (@RequestBody @Valid WorkSpaceRequest request) {
        return service.createWorkSpaces(request);
    }

    @GetMapping
    public List<WorkSpaceResponse> getWorkSpaces() {
        return service.getWorkSpaces();
    }
}
