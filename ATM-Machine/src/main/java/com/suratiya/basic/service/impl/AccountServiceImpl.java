package com.suratiya.basic.service.impl;

import com.suratiya.basic.dto.AccountDto;
import com.suratiya.basic.entity.Account;
import com.suratiya.basic.mapper.AccountMapper;
import com.suratiya.basic.repository.AccountRepository;
import com.suratiya.basic.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount) ;
    }

    @Override
    public AccountDto getAccountById(Long id) {

         Account account= accountRepository.findById(id).orElseThrow(()->new RuntimeException("Acoount does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long id, Double amount) {
        Account account= accountRepository.
                findById(id).orElseThrow(()->new RuntimeException("Acoount does not exists"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account saveAccount=accountRepository.save(account);

        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto withdrawlAmount(Long id, Double amount) {
        Account account= accountRepository.
                findById(id).orElseThrow(()->new RuntimeException("Acoount does not exists"));
        if (account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total=account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRepository.
                findById(id).orElseThrow(()->new RuntimeException("Acoount does not exists"));
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts= accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public List<String> getMiniStatement(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return account.getMiniStatement();
    }

    private void addToMiniStatement(Account account, String transaction) {
        List<String> miniStatement = account.getMiniStatement();
        if (miniStatement.size() >= 5) {  // Keep only the last 5 transactions
            miniStatement.remove(0);
        }
        miniStatement.add(transaction);
    }


}
