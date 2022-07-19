package com.william.lendtech.security.dto;

import com.william.lendtech.transaction.TransactionType;
import com.william.lendtech.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 * Email: william.k.makau@gmail.com
 */
@Getter
@Setter
@Data
public class TransactionDto {
    private Long id;
    private double amount;
    private Date transactionDateTime;
    private UserDto creditUser;
    private UserDto debitUser;
    private TransactionType transactionType;
    private String description;
}
