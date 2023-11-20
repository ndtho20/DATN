package com.example.demo.controller.user;

import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.KhachHang;
import com.example.demo.service.GioHangChiTietService;
import com.example.demo.service.GioHangService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.KhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/user/gio-hang")
public class GioHangController {
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private GioHangChiTietService gioHangChiTietService;
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping()
    public String findGioHangDetailsByKhachHang(Model model) {
        KhachHang kh = khachHangService.getById(3);
        List<Object[]> dsSanPham = gioHangService.findGioHangDetailsByKhachHang(kh);
        model.addAttribute("dsSanPham", dsSanPham);
        return "User/GioHang/Index";

    }

    @PostMapping("/thanh-toan")
    @ResponseBody
    public String thanhToan(@RequestBody List<Integer> selectedProductIds, HttpSession session) {
        List<GioHangChiTiet> dsSelected = new ArrayList<>();
        for (Integer productId : selectedProductIds) {
            gioHangChiTietService.getGioHangChiTiet(productId)
                    .ifPresent(dsSelected::add);
        }
        session.setAttribute("selectedProductIds", selectedProductIds);
        return "";
    }

    @GetMapping("/checkout")
    public String thongTinDonHang(Model model, HttpSession session) {
        KhachHang kh = khachHangService.getById(3);
        model.addAttribute("kh", kh);
        List<Integer> selectedProductIds = (List<Integer>) session.getAttribute("selectedProductIds");
        List<Object[]> detailsList = gioHangChiTietService.findDetailsById(selectedProductIds);
        model.addAttribute("detailsList", detailsList);
        return "User/GioHang/Checkout";
    }

    private String generateRandomMaHoaDon() {
        // Tạo số ngẫu nhiên 10 chữ số
        Random random = new Random();
        int randomDigits = 1000000000 + random.nextInt(900000000);
        String maHoaDon = "HĐ" + randomDigits;
        return maHoaDon;
    }

    @PostMapping("/tra-sau")
    public String thanhToanTraSau(@RequestParam("diaChi") String diaChi,HttpSession session) {
        String maHD = generateRandomMaHoaDon();
        KhachHang kh = khachHangService.getById(3);
        List<Integer> selectedProductIds = (List<Integer>) session.getAttribute("selectedProductIds");
        List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietService.findByIds(selectedProductIds);
        hoaDonService.xuLyThanhToanOnline(maHD,diaChi,kh,gioHangChiTietList);
        System.out.println("Thanh Toán Thành Công");
        return "redirect:/";
    }


}
