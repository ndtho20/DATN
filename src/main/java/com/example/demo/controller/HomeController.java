package com.example.demo.controller;

import com.example.demo.entity.KhachHang;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "Layout/index";
    }

    @GetMapping("/login")
    public String login() {
        return "Layout/Login";
    }

    @GetMapping("/register")
    public String register() {
        return "Layout/Register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute KhachHang user, HttpSession session, Model m, HttpServletRequest request) {

        // System.out.println(user);
        String url = request.getRequestURL().toString();
        url = url.replace(request.getServletPath(), "");
        KhachHang u = userService.saveUser(user, url);
        if (u != null) {
            session.setAttribute("msg", "Register successfully");
        } else {
            session.setAttribute("msg", "Something wrong server");
        }

        return "redirect:/register";
    }

    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code, Model m) {
        boolean f = userService.verifyAccount(code);
        if (f) {
            m.addAttribute("msg", "Sucessfully your account is verified");
        } else {
            m.addAttribute("msg", "may be your vefication code is incorrect or already veified ");
        }

        return "Layout/message";
    }
}

