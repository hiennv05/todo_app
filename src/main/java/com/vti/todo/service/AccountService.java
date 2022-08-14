package com.vti.todo.service;

import com.vti.todo.dto.request.AccountRequest;
import com.vti.todo.entity.Account;
import com.vti.todo.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository repository;

    @Override
    public Account register(AccountRequest request) {

        Account newAccount = new Account();
        newAccount.setEmail(request.getEmail());
        newAccount.setPassword(request.getPassword());
        newAccount.setFullName(request.getFullName());

        repository.save(newAccount);
        return newAccount;
    }
}
