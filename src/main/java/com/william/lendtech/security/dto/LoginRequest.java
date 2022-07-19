package com.william.lendtech.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "{login_username_not_empty}")
    private String username;

    @NotEmpty(message = "{login_password_not_empty}")
    private String password;
}
