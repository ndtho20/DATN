package com.example.demo.controller.admin;

import com.example.demo.entity.MauSac;
import com.example.demo.service.MauSacService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/MauSac")
public class MauSacController {

    @Autowired
    private MauSacService service;

    @GetMapping()
    public String getAll(Model model) {
        List<MauSac> dsMauSac = service.getAll();
        model.addAttribute("dsMauSac", dsMauSac);
        model.addAttribute("pc", new MauSac());
        return "Admin/MauSac/Index";
    }

    @PostMapping("/add")
    public String addMauSac(@ModelAttribute MauSac MauSac, Model model) {
        MauSac.setTrangThai(true);
        service.addMauSac(MauSac);
        List<MauSac> dsMauSac = service.getAll();
        model.addAttribute("dsMauSac", dsMauSac);
        return "redirect:/MauSac";
    }

    @GetMapping("/detail/{id}")
    public String editMauSacForm(@PathVariable("id") int Id, Model model) {
        MauSac MauSac = service.getById(Id);
        model.addAttribute("MauSac", MauSac);
        return "Admin/MauSac/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateMauSac(@PathVariable("id") int id, @ModelAttribute MauSac MauSac) {
        service.updateMauSac(id, MauSac);
        return "redirect:/MauSac";
    }


    @GetMapping("/delete/{id}")
    public String deleteMauSac(@PathVariable("id") int id) {
        service.deleteMauSac(id);
        return "redirect:/MauSac";
    }
}
