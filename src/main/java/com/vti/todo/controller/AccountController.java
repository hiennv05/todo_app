package com.vti.todo.controller;

import com.vti.todo.dto.request.AccountRequest;
import com.vti.todo.entity.Account;
import com.vti.todo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    @Autowired
    IAccountService service;

    @PostMapping("/register")
    public Account register(@RequestBody AccountRequest request) {
        return service.register(request);
    }

}
