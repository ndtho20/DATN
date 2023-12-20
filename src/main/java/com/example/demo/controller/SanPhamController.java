package com.example.demo.controller;


import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.LoaiSanPham;
import com.example.demo.service.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("sanpham", new LoaiSanPham());
        return "LoaiSanPham/Index";
    }

    @PostMapping("/add")
    public String addSanPham(@Validated @ModelAttribute("sanpham") LoaiSanPham loaiSanPham, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        loaiSanPham.setTrangThai(true);
        if (result.hasErrors()) {
            List<LoaiSanPham> dsLoaiSanPham = loaiSanPhamService.getAll();
            model.addAttribute("dsSanPham", dsLoaiSanPham);
            model.addAttribute("sanpham", loaiSanPham);
            return "LoaiSanPham/Index";
        } else {
            loaiSanPhamService.addSanPham(loaiSanPham);
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/SanPham";
        }
    }

    @GetMapping("/detail/{id}")
    public String editSanPhamForm(@PathVariable("id") int Id, Model model) {
        LoaiSanPham loaiSanPham = loaiSanPhamService.getById(Id);
        model.addAttribute("sanpham", loaiSanPham);
        return "LoaiSanPham/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateSanPham(@PathVariable("id") int id, @Validated @ModelAttribute("sappham") LoaiSanPham loaiSanPham,BindingResult result,Model model) {
        if (result.hasErrors()) {
            List<LoaiSanPham> dsLoaiSanPham = loaiSanPhamService.getAll();
            model.addAttribute("dsSanPham", dsLoaiSanPham);
            model.addAttribute("sanpham", loaiSanPham);
            return "LoaiSanPham/Detail";
        } else {
            loaiSanPhamService.updateSanPham(id, loaiSanPham);
            return "redirect:/SanPham";
        }
    }


    @GetMapping("/delete")
    public String deleteSanPham(@RequestParam("id") int id) {
        loaiSanPhamService.deleteSanPham(id);
        return "redirect:/SanPham";
    }

}