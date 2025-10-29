package com.example.individual01.controller;

import com.example.individual01.model.account;
import com.example.individual01.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AccountService service;

    public AuthController(AccountService service) {
        this.service = service;
    }

    // Trang login
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        if (error != null) model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
        if (logout != null) model.addAttribute("message", "Đăng xuất thành công!");
        model.addAttribute("account", new account());
        return "auth/login";
    }

    // Trang đăng ký
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("account", new account());
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute account acc, Model model) {
        try {
            service.create(acc.getAccountUsername(), acc.getAccountPassword(), acc.getAccountType());
            model.addAttribute("message", "Đăng ký thành công!");
            return "redirect:/auth/login";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi: " + e.getMessage());
            return "auth/register";
        }
    }

    // Sau khi đăng nhập, phân quyền chuyển trang
    @GetMapping("/redirect")
    public String redirectByRole(org.springframework.security.core.Authentication auth) {
        String role = auth.getAuthorities().toString();
        if (role.contains("ADMIN")) {
            return "redirect:/admin/info";
        } else {
            return "redirect:/user/info";
        }
    }
}
