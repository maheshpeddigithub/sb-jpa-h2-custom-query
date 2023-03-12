package com.ssil.sb.jpa.h2.custom.query.repository;

import com.ssil.sb.jpa.h2.custom.query.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByActive(boolean active);

    @Query("UPDATE Account a SET a.balance = a.balance + ?1 WHERE a.id = ?2")
    @Modifying
    @Transactional
    void deposit(int amount, Long id);

    @Query("UPDATE Account a SET a.balance = a.balance - ?1 WHERE a.id = ?2")
    @Modifying
    @Transactional
    void withdraw(int amount, Long id);

}
