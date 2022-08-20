package com.vti.todo.controller;

import com.vti.todo.dto.request.AccountRequest;
import com.vti.todo.dto.request.LoginRequest;
import com.vti.todo.entity.Account;
import com.vti.todo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    @Autowired
    IAccountService service;

    @PostMapping("/register")
    public Account register(@RequestBody @Valid AccountRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        return service.login(request);
    }
    @GetMapping("/principal")
    public UserDetails getCurrentAccount(@AuthenticationPrincipal UserDetails principal) {
        return principal;
    }

}
