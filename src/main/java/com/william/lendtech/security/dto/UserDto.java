package com.william.lendtech.security.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 * Email: william.k.makau@gmail.com
 */
@Getter
@Setter
@Data
public class UserDto {
    private long id;

    private String firstName;
    private String lastName;
    private String username;
}
