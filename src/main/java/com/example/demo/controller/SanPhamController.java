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
    public String getAll(Model model, @RequestParam(defaultValue = "0") int page) {

        int pageSize = 2;

        List<LoaiSanPham> list = loaiSanPhamService.getAll();
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        List<LoaiSanPham> currentPageLoaiSanPham = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

        model.addAttribute("dsSanPham", currentPageLoaiSanPham);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("sanpham", new LoaiSanPham());
        return "LoaiSanPham/Index";
    }

    @PostMapping("/add")
    public String addSanPham(@Validated @ModelAttribute("sanpham") LoaiSanPham loaiSanPham, BindingResult result, Model model, RedirectAttributes redirectAttributes
            , @RequestParam(defaultValue = "0") int page) {
        loaiSanPham.setTrangThai(true);
        if (result.hasErrors()) {
            int pageSize = 2;

            List<LoaiSanPham> list = loaiSanPhamService.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<LoaiSanPham> currentPageLoaiSanPham = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsSanPham", currentPageLoaiSanPham);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
//            model.addAttribute("dsSanPham", dsLoaiSanPham);
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
    public String updateSanPham(@PathVariable("id") int id, @Validated @ModelAttribute("sappham") LoaiSanPham loaiSanPham, BindingResult result, Model model) {
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

    @PostMapping("search")
    public String searchProductByCode(@RequestParam String ma, Model model, @RequestParam(defaultValue = "0") int page) {

        System.out.println(ma);

        if (ma == null || ma.isBlank()) {
            int pageSize = 2;

            List<LoaiSanPham> list = loaiSanPhamService.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<LoaiSanPham> currentPageLoaiSanPham = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsSanPham", currentPageLoaiSanPham);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);

            model.addAttribute("sanpham", new LoaiSanPham());
        } else {
            int pageSize = 2;
            List<LoaiSanPham> list = loaiSanPhamService.findLoaiSanPhamByMa(ma);
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<LoaiSanPham> currentPageLoaiSanPham = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsSanPham", currentPageLoaiSanPham);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);


            model.addAttribute("sanpham", new LoaiSanPham());
        }
        return "LoaiSanPham/Index";

    }

}