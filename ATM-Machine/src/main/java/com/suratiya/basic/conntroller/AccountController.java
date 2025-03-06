package com.suratiya.basic.conntroller;

import com.suratiya.basic.dto.AccountDto;
import com.suratiya.basic.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById( @PathVariable Long id){

        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAmount( @PathVariable Long id,@RequestBody Map<String,Double> request){
        Double amount=request.get("amount");
        AccountDto accountDto= accountService.depositAmount(id,amount);
        return  ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawlAmount(@PathVariable Long id,
                                                      @RequestBody Map<String,Double> request ){
        double amount= request.get("amount");
        AccountDto accountDto= accountService.withdrawlAmount(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount( @PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted Successfully!");
    }



    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts=accountService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}/mini-statement")
    public List<String> getMiniStatement(@PathVariable Long id) {
        return accountService.getMiniStatement(id);
    }


}
