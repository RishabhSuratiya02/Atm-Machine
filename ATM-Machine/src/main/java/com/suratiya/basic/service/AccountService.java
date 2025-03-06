package com.suratiya.basic.service;

import com.suratiya.basic.dto.AccountDto;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto depositAmount(Long id , Double amount);
    AccountDto withdrawlAmount(Long id, Double amount);
    void deleteAccount(Long id);
    List <AccountDto> getAllAccount();
    List<String> getMiniStatement(Long id);


}
