package com.vti.todo.service;

import com.vti.todo.dto.request.AccountRequest;
import com.vti.todo.dto.request.LoginRequest;
import com.vti.todo.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService {

    Account register(AccountRequest request);

    ResponseEntity<String> login(LoginRequest request);
}
