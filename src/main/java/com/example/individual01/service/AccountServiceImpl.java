package com.example.individual01.service.impl;

import com.example.individual01.model.account;
import com.example.individual01.repository.AccountRepository;
import com.example.individual01.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;

    public AccountServiceImpl(AccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<account> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<account> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Optional<account> findByUsername(String username) {
        return repo.findByAccountUsername(username);
    }

    @Override
    public account create(String username, String rawPassword, String accountType) {
        if (repo.existsByAccountUsername(username)) {
            throw new IllegalArgumentException("Username đã tồn tại: " + username);
        }
        account acc = new account();
        acc.setAccountUsername(username);
        acc.setAccountType(accountType);
        // dùng setter đã mã hóa
        acc.setPassword(rawPassword);
        return repo.save(acc);
    }

    @Override
    public account update(int id, String username, String accountType) {
        account acc = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy accountId=" + id));
        acc.setAccountUsername(username);
        acc.setAccountType(accountType);
        return repo.save(acc);
    }

    @Override
    public void changePassword(int id, String rawPassword) {
        account acc = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy accountId=" + id));
        // setter này sẽ tự BCrypt
        acc.setPassword(rawPassword);
        repo.save(acc);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}
