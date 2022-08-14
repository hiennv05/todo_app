package com.vti.todo.service;

import com.vti.todo.dto.request.AccountRequest;
import com.vti.todo.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService {

    Account register(AccountRequest request);
}
