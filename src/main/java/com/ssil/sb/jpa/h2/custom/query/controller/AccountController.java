package com.ssil.sb.jpa.h2.custom.query.controller;

import com.ssil.sb.jpa.h2.custom.query.model.Account;
import com.ssil.sb.jpa.h2.custom.query.model.Amount;
import com.ssil.sb.jpa.h2.custom.query.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(service.getAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable long id) {
        Optional<Account> account = service.getAccount(id);
        return account.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Account>> getActiveAccounts() {
        return new ResponseEntity<>(service.getActiveAccounts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        return new ResponseEntity<>(service.saveAccount(account), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable long id, @RequestBody Amount amount) {
        service.deposit(id, amount.getAmount());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable long id, @RequestBody Amount amount) {
        service.withdraw(id, amount.getAmount());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable long id) {
        service.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
