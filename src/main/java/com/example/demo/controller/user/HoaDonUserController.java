package com.example.demo.controller.user;


import com.example.demo.entity.HoaDon;
import com.example.demo.entity.KhachHang;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.KhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

import java.util.List;


@Controller
@RequestMapping("/user/don-hang")
public class HoaDonUserController {

    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping()
    public String getHoaDonByTrangThai(Model model) {
        KhachHang khachHang = khachHangService.getById(3);
        List<HoaDon> dsHoaDon;
        dsHoaDon = hoaDonService.getHoaDonByKhachHang(khachHang);
        List<List<Object[]>> dsChiTietHoaDon = new ArrayList<>();
        for (HoaDon hoaDon : dsHoaDon) {
            int idHoaDon = hoaDon.getIdHoaDon();
            List<Object[]> chiTietDonHang = hoaDonService.getChiTietDonHang(idHoaDon);
            dsChiTietHoaDon.add(chiTietDonHang);
        }
        model.addAttribute("dsHoaDon", dsHoaDon);
        model.addAttribute("dsChiTietHoaDon", dsChiTietHoaDon);
        model.addAttribute("khongCoDonHang", dsHoaDon.isEmpty());
        return "User/DonHang/Index";
    }

    @GetMapping("/{trangThai}")
    public String getHoaDonByTrangThai(@PathVariable(name = "trangThai") Integer trangThai, Model model) {
        KhachHang khachHang = khachHangService.getById(3);
        List<HoaDon> dsHoaDon;
        if (trangThai != null) {
            dsHoaDon = hoaDonService.getHoaDonByKhachHangandTrangThai(khachHang, trangThai);
        } else {
            dsHoaDon = hoaDonService.getHoaDonByKhachHang(khachHang);
        }
        List<List<Object[]>> dsChiTietHoaDon = new ArrayList<>();
        for (HoaDon hoaDon : dsHoaDon) {
            int idHoaDon = hoaDon.getIdHoaDon();
            List<Object[]> chiTietDonHang = hoaDonService.getChiTietDonHang(idHoaDon);
            dsChiTietHoaDon.add(chiTietDonHang);
        }
        model.addAttribute("dsHoaDon", dsHoaDon);
        model.addAttribute("dsChiTietHoaDon", dsChiTietHoaDon);
        model.addAttribute("khongCoDonHang", dsHoaDon.isEmpty());
        return "User/DonHang/Index";
    }
}
