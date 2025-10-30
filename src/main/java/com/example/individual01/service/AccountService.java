package com.example.individual01.service;

import com.example.individual01.model.account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<account> findAll();
    Optional<account> findById(int id);
    Optional<account> findByUsername(String username);

    // Tạo mới (mã hóa mật khẩu)
    account create(String username, String rawPassword, String accountType);

    // Cập nhật thông tin (không đổi mật khẩu)
    account update(int id, String username, String accountType);

    // Đổi mật khẩu (mã hóa)
    void changePassword(int id, String rawPassword);

    void delete(int id);

    
}
