package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chitietsanpham")
public class ChitietSanPhamController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;


    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    @Autowired
    private ChatLieuService chatLieuService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private PhongCachService phongCachService;

    @Autowired
    private NSXService nsxService;

    @Autowired
    private MauSacService mauSacService;

    @GetMapping()
    public String getAll(Model model) {
        List<ChiTietSanPham> dsChiTietSanPham = chiTietSanPhamService.getAll();
        model.addAttribute("dsChiTietSanPham", dsChiTietSanPham);
        model.addAttribute("listLoaiSanPham", loaiSanPhamService.getAll());
        model.addAttribute("listChatLieu", chatLieuService.getAll());
        model.addAttribute("listSize", sizeService.getAll());
        model.addAttribute("listPhongCach", phongCachService.getAll());
        model.addAttribute("listNSX", nsxService.getAll());
        model.addAttribute("listMauSac", mauSacService.getAll());
        model.addAttribute("chiTietSanPham", new ChiTietSanPham());
        return "ChiTietSanPham/Index";
    }

    @PostMapping("/add")
    public String addChiTietSanPham(@ModelAttribute ChiTietSanPham chiTietSanPham, Model model) {
        chiTietSanPham.setTrangThai(true);
        chiTietSanPhamService.addChiTietSanPham(chiTietSanPham);
        List<ChiTietSanPham> dsChiTietSanPham = chiTietSanPhamService.getAll();
        model.addAttribute("dsChiTietSanPham", dsChiTietSanPham);
        return "redirect:/chitietsanpham";
    }

    @GetMapping("/detail/{id}")
    public String editChiTietSanPhamForm(@PathVariable("id") Integer id, Model model) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getById(id);
        List<LoaiSanPham> listLoaiSanPham = loaiSanPhamService.getAll();
        List<ChatLieu> listChatLieu = chatLieuService.getAll();
        List<Size> listSize = sizeService.getAll();
        List<PhongCach> listPhongCach = phongCachService.getAll();
        List<NSX> listNsx = nsxService.getAll();
        List<MauSac> listMauSac = mauSacService.getAll();

        model.addAttribute("chiTietSanPham", chiTietSanPham);
        model.addAttribute("listLoaiSanPham", listLoaiSanPham);
        model.addAttribute("listChatLieu", listChatLieu);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listPhongCach", listPhongCach);
        model.addAttribute("listNsx", listNsx);
        model.addAttribute("listMauSac", listMauSac);
        return "ChiTietSanPham/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateChiTietSanPham(@PathVariable("id") Integer id, @ModelAttribute ChiTietSanPham chiTietSanPham) {
        chiTietSanPhamService.updateChiTietSanPham(id, chiTietSanPham);
        return "redirect:/chitietsanpham";
    }

    @GetMapping("/delete/{id}")
    public String deleteChiTietSanPham(@PathVariable("id") Integer id) {
        chiTietSanPhamService.deleteChiTietSanPham(id);
        return "redirect:/chitietsanpham";
    }
}

