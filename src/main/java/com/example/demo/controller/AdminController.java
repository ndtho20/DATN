package com.example.demo.controller;

import com.example.demo.entity.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class AdminController {
    @GetMapping()
    public String getAll(Model model) {
        return "admin/index";
    }
}
