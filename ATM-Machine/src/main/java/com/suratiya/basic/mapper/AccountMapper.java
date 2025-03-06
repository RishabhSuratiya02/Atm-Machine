package com.suratiya.basic.mapper;

import com.suratiya.basic.dto.AccountDto;
import com.suratiya.basic.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account= new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                accountDto.getMiniStatement()

        );
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto= new AccountDto(
       account.getId(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getMiniStatement()
        );
        return accountDto;
    }
}
