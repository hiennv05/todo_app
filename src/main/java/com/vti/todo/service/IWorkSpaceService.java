package com.vti.todo.service;

import com.vti.todo.dto.request.WorkSpaceRequest;
import com.vti.todo.dto.response.WorkSpaceResponse;
import com.vti.todo.entity.WorkSpace;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IWorkSpaceService {
    WorkSpaceResponse createWorkSpaces(WorkSpaceRequest request);

    List<WorkSpaceResponse> getWorkSpaces();
}
