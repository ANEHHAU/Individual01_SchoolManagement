package com.example.individual01.service.impl;

import com.example.individual01.model.account;
import com.example.individual01.repository.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository repo;

    public CustomUserDetailsService(AccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        account acc = repo.findByAccountUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user: " + username));

        return User.builder()
                .username(acc.getAccountUsername())
                .password(acc.getAccountPassword())
                .roles(acc.getAccountType().toUpperCase()) // Ví dụ: USER / ADMIN
                .build();
    }
}
