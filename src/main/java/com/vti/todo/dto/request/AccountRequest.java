package com.vti.todo.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountRequest {

    private Integer id;

    private String email;

    private String password;

    private String fullName;

}
