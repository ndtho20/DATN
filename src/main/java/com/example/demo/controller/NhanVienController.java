package com.example.demo.controller;


import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping()
    public String getAll(Model model) {
        List<NhanVien> dsNhanVien = nhanVienService.getAll();
        model.addAttribute("dsNhanVien", dsNhanVien);
        model.addAttribute("nv", new NhanVien());
        return "NhanVien/Index";
    }

    @PostMapping("/add")
    public String addNhanVien(@ModelAttribute NhanVien nhanVien, Model model) {
        nhanVien.setTrangThai(false);
        nhanVienService.addNhanVien(nhanVien);
        List<NhanVien> dsNhanVien = nhanVienService.getAll();
        model.addAttribute("dsNhanVien", dsNhanVien);
        return "redirect:/nhanvien";
    }

    @GetMapping("/detail/{id}")
    public String editNhanVienForm(@PathVariable("id") int Id, Model model) {
        NhanVien nhanVien = nhanVienService.getById(Id);
        model.addAttribute("nhanVien", nhanVien);
        return "NhanVien/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateNhanVien(@PathVariable("id") int id, @ModelAttribute NhanVien nhanVien) {
        nhanVienService.updateNhanVien(id, nhanVien);
        return "redirect:/nhanvien";
    }


    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable("id") int id) {
        nhanVienService.deleteNhanVien(id);
        return "redirect:/nhanvien";
    }

}
