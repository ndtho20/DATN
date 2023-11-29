package com.example.demo.controller;


import com.example.demo.entity.LoaiSanPham;
import com.example.demo.service.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/SanPham")
public class SanPhamController {
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    @GetMapping()
    public String getAll(Model model) {
        List<LoaiSanPham> dsLoaiSanPham = loaiSanPhamService.getAll();
        model.addAttribute("dsSanPham", dsLoaiSanPham);
        model.addAttribute("pc", new LoaiSanPham());
        return "LoaiSanPham/Index";
    }

    @PostMapping("/add")
    public String addSanPham(@ModelAttribute LoaiSanPham loaiSanPham, Model model) {
        loaiSanPham.setTrangThai(true);
        loaiSanPhamService.addSanPham(loaiSanPham);
        List<LoaiSanPham> dsLoaiSanPham = loaiSanPhamService.getAll();
        model.addAttribute("dsSanPham", dsLoaiSanPham);
        return "redirect:/SanPham";
    }

    @GetMapping("/detail/{id}")
    public String editSanPhamForm(@PathVariable("id") int Id, Model model) {
        LoaiSanPham loaiSanPham = loaiSanPhamService.getById(Id);
        model.addAttribute("SanPham", loaiSanPham);
        return "LoaiSanPham/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateSanPham(@PathVariable("id") int id, @ModelAttribute LoaiSanPham loaiSanPham) {
        loaiSanPhamService.updateSanPham(id, loaiSanPham);
        return "redirect:/SanPham";
    }


    @GetMapping("/delete/{id}")
    public String deleteSanPham(@PathVariable("id") int id) {
        loaiSanPhamService.deleteSanPham(id);
        return "redirect:/SanPham";
    }

}