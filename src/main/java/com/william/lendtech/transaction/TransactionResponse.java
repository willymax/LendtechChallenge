package com.william.lendtech.transaction;

import com.william.lendtech.security.dto.TransactionDto;
import lombok.Setter;

import java.util.List;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 * Email: william.k.makau@gmail.com
 */

@Setter
public class TransactionResponse {
    private List<TransactionDto> data;
    private int pageNo;
    private int pageSize;
    private long total;
    private int totalPages;
    private boolean last;
}
