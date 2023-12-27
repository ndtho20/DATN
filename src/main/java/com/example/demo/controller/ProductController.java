package com.example.demo.controller;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.KhachHang;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.service.ChiTietSanPhamService;
import com.example.demo.service.GioHangChiTietService;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return "clients/home-4";

    }
    @GetMapping("/product/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "2") int size,
                       @RequestParam(value = "cid", required = false) Integer categoryId) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietSanPham> productPage;
        List<ChiTietSanPham> list;

        if (categoryId != null) {
            list = chiTietSanPhamService.findByCategoryId(categoryId);
            model.addAttribute("items", list);
        } else {
            productPage = chiTietSanPhamService.findAll(pageable);
            model.addAttribute("items", productPage);
        }
        return "clients/shop-style-2";
    }
//    @GetMapping("/product/list")
//    public String list(Model model,
//                       @RequestParam(value = "cid", required = false) Integer categoryId,
//                       @RequestParam(value = "color", required = false) String color,
//                       @RequestParam(value = "size", required = false) String size,
//                       @RequestParam(value = "page", defaultValue = "0") int page,
//                       @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
//
//        // Lọc sản phẩm dựa trên các tham số được chọn từ view
//        List<ChiTietSanPham> list;
//
//        if (categoryId != null && !categoryId.describeConstable().isEmpty()) {
//            if (color != null && !color.isEmpty() && size != null && !size.isEmpty()) {
//                // Lọc theo cả danh mục, màu sắc và kích thước
//                list = chiTietSanPhamService.findByCategoryAndMauSacAndSize(categoryId, color, size);
//            } else if (color != null && !color.isEmpty()) {
//                // Lọc theo danh mục và màu sắc
//                list = chiTietSanPhamService.findByCategoryAndMauSac(categoryId, color);
//            } else if (size != null && !size.isEmpty()) {
//                // Lọc theo danh mục và kích thước
//                list = chiTietSanPhamService.findByCategoryAndSize (categoryId, size);
//            } else {
//                // Lọc chỉ theo danh mục
//                list = chiTietSanPhamService.findByCategoryId(categoryId);
//            }
//        } else {
//            if (color != null && !color.isEmpty() && size != null && !size.isEmpty()) {
//                // Lọc theo cả màu sắc và kích thước
//                list = chiTietSanPhamService.findByMauSacAndSize(color, size);
//            } else if (color != null && !color.isEmpty()) {
//                // Lọc chỉ theo màu sắc
//                list = chiTietSanPhamService.findByMauSac(color);
//            } else if (size != null && !size.isEmpty()) {
//                // Lọc chỉ theo kích thước
//                list = chiTietSanPhamService.findBySize(size);
//            } else {
//                // Không có điều kiện lọc được chọn
//                list = chiTietSanPhamService.getAll();
//            }
//        }
//
//        model.addAttribute("items", list);
//
//        return "clients/shop-style-2";
//    }

    @GetMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        ChiTietSanPham item = chiTietSanPhamService.getById(id);
        model.addAttribute("item", item);
        List<String> relatedSizeNames = chiTietSanPhamService.findRelatedSizeNames(id);
        model.addAttribute("relatedSizeNames", relatedSizeNames);
        return "clients/shop-single-v1";
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
