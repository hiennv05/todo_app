package com.vti.todo.service;

import com.vti.todo.dto.request.AccountRequest;
import com.vti.todo.dto.request.LoginRequest;
import com.vti.todo.entity.Account;
import com.vti.todo.repository.IAccountRepository;
import com.vti.todo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public Account register(AccountRequest request) {

        Account newAccount = new Account();
        newAccount.setEmail(request.getEmail());
        String encode = passwordEncoder.encode(request.getPassword());
        newAccount.setPassword(encode);
        newAccount.setFullName(request.getFullName());

        repository.save(newAccount);
        return newAccount;
    }

    @Override
    public ResponseEntity<String> login(LoginRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            String token = jwtTokenProvider.createToken(request.getEmail(), userDetails.getAuthorities());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
