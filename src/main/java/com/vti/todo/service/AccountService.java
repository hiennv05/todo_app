package com.vti.todo.service;

import com.vti.todo.dto.request.AccountRequest;
import com.vti.todo.dto.request.LoginRequest;
import com.vti.todo.dto.request.ResetPasswordRequest;
import com.vti.todo.entity.Account;
import com.vti.todo.entity.OtpAccount;
import com.vti.todo.repository.IAccountRepository;
import com.vti.todo.repository.OtpAccountRepository;
import com.vti.todo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

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

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    OtpAccountRepository otpAccountRepository;
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
    public void forgotPassword(String email) {

        Account account = repository.findByEmail(email);

        if (account != null) {

            Random rd = new SecureRandom();
            int random = rd.nextInt(999_999);// random tu 0 -> 999_999
            String randomStr = String.format("%06d", random);

            OtpAccount otp = new OtpAccount();
            otp.setOtp(randomStr);
            otp.setEmail(email);
            otp.setExpire(LocalDateTime.now().plusMinutes(30));
            otpAccountRepository.save(otp);

            sendMailService.sendForgotPassword(account.getEmail(), randomStr, account.getLang());
        }
    }

    @Transactional
    public ResponseEntity<?> resetPassword(ResetPasswordRequest request) {
        OtpAccount otp = otpAccountRepository.findByEmailAndOtp(request.getEmail(), request.getOtp());
        if (otp != null && otp.getExpire().isAfter(LocalDateTime.now())) { //nếu otp tồn tại và chưa hết hạn (> now)
            Account accountEntity = repository.findByEmail(request.getEmail());
            accountEntity.setPassword(passwordEncoder.encode(request.getPassword()));
            repository.save(accountEntity);
            otpAccountRepository.delete(otp);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
