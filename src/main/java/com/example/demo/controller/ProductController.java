package com.example.demo.controller;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.KhachHang;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.service.ChiTietSanPhamService;
import com.example.demo.service.GioHangChiTietService;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private GioHangChiTietService gioHangChiTietService;
    @Autowired
    private KhachHangRepository khachHangRepository;

    @GetMapping("home")
    public String trangchu(Model model) {
        List<ChiTietSanPham> list = chiTietSanPhamService.getAll();
        model.addAttribute("items", list);
        return "Layout/_trangchu";
    }

    @GetMapping("/product/list")
    public String list(Model model, @RequestParam("cid") Optional<String> cid) {
        if (cid.isPresent()) {
            List<ChiTietSanPham> list = chiTietSanPhamService.findByCategoryId(cid.get());
            model.addAttribute("items", list);
        } else {
            List<ChiTietSanPham> list = chiTietSanPhamService.getAll();
            model.addAttribute("items", list);
        }
        return "clients/shop-style-2";
    }

    @GetMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        ChiTietSanPham item = chiTietSanPhamService.getById(id);
        model.addAttribute("item", item);
        List<String> relatedSizeNames = chiTietSanPhamService.findRelatedSizeNames(id);
        model.addAttribute("relatedSizeNames", relatedSizeNames);
        return "product/detail";
    }

    @PostMapping("/product/addToCart")
    public String addOrUpdateCartItem(@RequestParam("chiTietSanPhamId") Integer chiTietSanPhamId, Principal p) {
        String email = p.getName();
        KhachHang kh = khachHangRepository.findKhachHangByEmail(email);
        Integer gioHangId = kh.getGioHang().getIdGioHang();
        gioHangChiTietService.addOrUpdateCartItem(gioHangId, chiTietSanPhamId);
        return "redirect:/user/gio-hang";
    }

    @GetMapping("/product/baohanh")
    public String baoHanh() {
        return "layout/_chinh-sach-doi-tra";
    }
}
