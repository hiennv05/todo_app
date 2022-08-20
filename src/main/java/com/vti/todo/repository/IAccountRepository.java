package com.vti.todo.repository;

import com.vti.todo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT (count(a) = 0) FROM Account a WHERE email = ?1")
    boolean isEmailNotExists(String email);

    Account findByEmail(String email);
}
