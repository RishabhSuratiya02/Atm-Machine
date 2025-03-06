package com.suratiya.basic.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String accountHolderName;
    private Double balance;
    private List<String> miniStatement;


}

