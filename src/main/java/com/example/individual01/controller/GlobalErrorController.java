package com.example.individual01.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class GlobalErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public GlobalErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, WebRequest webRequest, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Lấy thông tin lỗi chi tiết để debug (nếu cần)
        var attrs = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        model.addAttribute("path", attrs.get("path"));
        model.addAttribute("message", attrs.get("error"));

        if (status != null) {
            int code = Integer.parseInt(status.toString());
            switch (code) {
                case 403:
                    return "error/403";
                case 404:
                    return "error/404";
                case 500:
                    return "error/500";
                default:
                    return "error/500";
            }
        }
        return "error/500";
    }
}
