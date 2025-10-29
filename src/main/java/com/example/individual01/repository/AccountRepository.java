package com.example.individual01.repository;

import com.example.individual01.model.account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<account, Integer> {
    Optional<account> findByAccountUsername(String accountUsername);
    boolean existsByAccountUsername(String accountUsername);
}
