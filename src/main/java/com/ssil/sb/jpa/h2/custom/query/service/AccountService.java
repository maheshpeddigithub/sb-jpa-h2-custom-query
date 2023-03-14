package com.ssil.sb.jpa.h2.custom.query.service;

import com.ssil.sb.jpa.h2.custom.query.model.Account;
import com.ssil.sb.jpa.h2.custom.query.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> getAccounts() {
        return repository.findAll();
    }

    public Optional<Account> getAccount(long id) {
        return repository.findById(id);
    }

    public List<Account> getActiveAccounts() {
        return repository.findAllActive();
    }

    public List<Account> getCreatedBetween(Date startDate, Date endDate) {
        return repository.findAllCreatedBetween(startDate, endDate);
    }

    public Account saveAccount(Account account) {
        return repository.save(account);
    }

    public void deposit(long id, int amount) {
        repository.deposit(amount, id);
    }

    public void withdraw(long id, int amount) {
        repository.withdraw(amount, id);
    }

    public void deleteAccount(long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
