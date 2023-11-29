package com.example.demo.controller;


import com.example.demo.entity.HoaDon;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping()
    public String getAll(Model model) {
        List<HoaDon> dsHoaDon = hoaDonService.getAll();
        model.addAttribute("dsHoaDon", dsHoaDon);
        return "HoaDon/Index";
    }

    @GetMapping("/{trangThai}")
    public String getHoaDonByTrangThai(@PathVariable int trangThai, Model model) {
        List<HoaDon> dsHoaDon = hoaDonService.getHoaDonByTrangThai(trangThai);
        model.addAttribute("dsHoaDon", dsHoaDon);
        return "HoaDon/Index";
    }

    @PostMapping("/updateTrangThai/{hoaDonId}")
    public String updateTrangThaiHoaDon(@PathVariable int hoaDonId, @RequestParam int newTrangThai) {
        hoaDonService.updateTrangThaiHoaDon(hoaDonId, newTrangThai);
        return "redirect:/admin/hoa-don/0";
    }

}
