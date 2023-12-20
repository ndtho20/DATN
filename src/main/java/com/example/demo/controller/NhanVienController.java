package com.example.demo.controller;


import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addNhanVien(@Validated @ModelAttribute("nv") NhanVien nhanvien, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        nhanvien.setTrangThai(false);
        if (result.hasErrors()) {
            List<NhanVien> dsNhanVien = nhanVienService.getAll();
            model.addAttribute("dsNhanVien", dsNhanVien);
            model.addAttribute("nv", nhanvien);
            return "NhanVien/Index";
        } else {
            nhanVienService.addNhanVien(nhanvien);
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/nhanvien";
        }
    }

    @GetMapping("/detail/{id}")
    public String editNhanVienForm(@PathVariable("id") int Id, Model model) {
        NhanVien nhanVien = nhanVienService.getById(Id);
        model.addAttribute("nv", nhanVien);
        return "NhanVien/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateNhanVien(@PathVariable("id") int id, @Validated @ModelAttribute("nv") NhanVien nhanVien, BindingResult result,Model model ) {
        if (result.hasErrors()) {
            List<NhanVien> dsNhanVien = nhanVienService.getAll();
            model.addAttribute("dsNhanVien", dsNhanVien);
            model.addAttribute("nv", nhanVien);
            return "NhanVien/Detail";
        }
        nhanVienService.updateNhanVien(id, nhanVien);
        return "redirect:/nhanvien";
    }


    @GetMapping("/delete")
    public String deleteNhanVien(@RequestParam("id") int id) {
        nhanVienService.deleteNhanVien(id);
        return "redirect:/nhanvien";
    }

}
