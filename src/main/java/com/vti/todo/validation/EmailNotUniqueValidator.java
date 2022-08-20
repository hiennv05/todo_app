package com.vti.todo.validation;

import com.vti.todo.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailNotUniqueValidator implements ConstraintValidator<EmailNotUnique, String> {
    @Autowired
    IAccountRepository accountRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return accountRepository.isEmailNotExists(email);
    }
}
