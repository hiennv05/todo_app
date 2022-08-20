package com.vti.todo.service;

import com.vti.todo.dto.request.TaskRequest;
import com.vti.todo.dto.response.TaskResponse;
import com.vti.todo.entity.TaskStatus;
import com.vti.todo.entity.Tasks;
import com.vti.todo.entity.WorkSpace;
import com.vti.todo.repository.ITaskRepository;
import com.vti.todo.repository.ITaskStatusRepository;
import com.vti.todo.repository.IWorkSpaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    ITaskRepository repository;

    @Autowired
    ITaskStatusRepository taskStatusRepository;

    @Autowired
    IWorkSpaceRepository workSpaceRepository;


    @Autowired
    ModelMapper modelMapper;


    public List<TaskResponse> getTaskByWorkSpace(Integer workspaceId) {
        List<TaskResponse> responses = new ArrayList<>();

        List<Tasks> taskByWorkSpace = repository.findByWorkSpaceId(workspaceId);

        taskByWorkSpace.forEach(tasks -> {
            TaskResponse response = new TaskResponse();
            BeanUtils.copyProperties(tasks, response);
            response.setWorkSpaceId(workspaceId);
            response.setStatusId(tasks.getStatus().getId());
            response.setStatusName(tasks.getStatus().getName());
            responses.add(response);
        });
        return responses;
    }

    public Optional<Tasks> updateTaskById(Integer id, TaskRequest request) {
        Optional<Tasks> oldTask = repository.findById(id);

        oldTask.ifPresent(tasks -> {
            tasks.setTitle(request.getTitle());
            tasks.setDescription(request.getDescription());

            TaskStatus taskStatus = taskStatusRepository.findById(request.getTaskStatusId()).get();
            tasks.setStatus(taskStatus);

            tasks.setStartDate(request.getStartDate());
            tasks.setDueDate(request.getDueDate());

            WorkSpace workSpace = workSpaceRepository.findById(request.getWorkSpaceId()).get();
            tasks.setWorkSpace(workSpace);

            repository.save(tasks);
        });
        return oldTask;
    }

    public Tasks createNewTask(TaskRequest request) {

        Tasks tasks = new Tasks();

        tasks.setId((int) repository.countAllTask() + 1);
        tasks.setTitle(request.getTitle());
        tasks.setDescription(request.getDescription());

        TaskStatus taskStatus = taskStatusRepository.findById(request.getTaskStatusId()).get();
        tasks.setStatus(taskStatus);

        tasks.setStartDate(request.getStartDate());
        tasks.setDueDate(request.getDueDate());

        WorkSpace workSpace = workSpaceRepository.findById(request.getWorkSpaceId()).get();
        tasks.setWorkSpace(workSpace);

        repository.save(tasks);

        return tasks;
    }

}
