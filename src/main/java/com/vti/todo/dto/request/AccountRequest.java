package com.vti.todo.dto.request;

import com.vti.todo.validation.EmailNotUnique;
import com.vti.todo.validation.Password;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
public class AccountRequest {
    @NotNull
    @Email
    @EmailNotUnique
    @NotBlank
    private String email;

    @Size(min = 6, max = 12)
    @NotNull
    @NotBlank
    @Password
    private String password;

    @NotNull
    @NotBlank
    private String fullName;

}
