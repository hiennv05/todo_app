package com.vti.todo.validation;

import com.vti.todo.repository.IWorkSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkSpaceNotUniqueValidator implements ConstraintValidator<WorkSpaceNotUnique, String> {
    @Autowired
    IWorkSpaceRepository workSpaceRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return workSpaceRepository.isWorkSpaceNotExists(name);
    }
}
