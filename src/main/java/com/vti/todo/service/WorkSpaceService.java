package com.vti.todo.service;

import com.vti.todo.dto.request.WorkSpaceRequest;
import com.vti.todo.dto.response.WorkSpaceResponse;
import com.vti.todo.entity.Account;
import com.vti.todo.entity.WorkSpace;
import com.vti.todo.repository.IAccountRepository;
import com.vti.todo.repository.ITaskRepository;
import com.vti.todo.repository.ITaskStatusRepository;
import com.vti.todo.repository.IWorkSpaceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkSpaceService implements IWorkSpaceService {
    @Autowired
    IWorkSpaceRepository repository;
    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ITaskRepository taskRepository;
    @Override
    public WorkSpaceResponse createWorkSpaces(WorkSpaceRequest request) {
        WorkSpace workSpace = new WorkSpace();
        workSpace.setName(request.getWorkSpaceName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByEmail(authentication.getName());
        workSpace.setAccount(account);

        repository.save(workSpace);
        return new WorkSpaceResponse(workSpace.getId(), workSpace.getName(), 0);
    }

    @Override
    public List<WorkSpaceResponse> getWorkSpaces() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // Account account = accountRepository.findByEmail(authentication.getName());
        String email = authentication.getName();
         List<WorkSpaceResponse> responses = repository.findWorkSpaceByAccEmail(email);

//        for (WorkSpaceResponse w :responses) {
//            int numOfTask = taskRepository.countByWorkSpaceId(w.getId());
//            w.setNumberOfTask(numOfTask);
//        }
        responses.forEach(workSpaces -> {
           int numOfTask =  taskRepository.countByWorkSpaceId(workSpaces.getId());
            workSpaces.setNumberOfTask(numOfTask);
        });

        return responses;
    }
}
