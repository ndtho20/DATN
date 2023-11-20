package com.example.demo.controller;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("trangchu")
    public String trangchu(Model model) {
        List<ChiTietSanPham> list = chiTietSanPhamService.getAll();
        model.addAttribute("items", list);
        return "layout/_trangchu";
    }

    @GetMapping("list")
    public String list(Model model, @RequestParam("cid") Optional<String> cid) {
        if (cid.isPresent()) {
            List<ChiTietSanPham> list = chiTietSanPhamService.findByCategoryId(cid.get());
            model.addAttribute("items", list);
        } else {
            List<ChiTietSanPham> list = chiTietSanPhamService.getAll();
            model.addAttribute("items", list);
        }
        return "product/list";
    }

    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        ChiTietSanPham item = chiTietSanPhamService.getById(id);
        model.addAttribute("item", item);
        List<String> relatedSizeNames = chiTietSanPhamService.findRelatedSizeNames(id);

        model.addAttribute("relatedSizeNames", relatedSizeNames);

        return "product/detail";
    }

    @GetMapping("baohanh")
    public String baoHanh() {
        return "layout/_chinh-sach-doi-tra";
    }
}
