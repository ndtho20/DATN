package com.example.demo.controller;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.StatusValue;
import com.example.demo.service.HoaDonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/thong-ke")
public class ThongKeController {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    HttpServletRequest request;

    @GetMapping({"/hoa-don"})
    public String listFirstPage(@RequestParam(name = "status", required = false) Integer status, Model model) {

        HttpSession session = this.request.getSession();
//        if (session.getAttribute("admin") == null)
//            return "redirect:/login-admin";
        if (status != null) {
            return listByPageStatus(1, status.intValue(), model, "ngayTao", "desc", null);
        }
        return listByPage(1, model, "ngayTao", "desc", null);
    }

    @GetMapping("/hoa-don/{pageNum}")
    private String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model, @Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {
        Page<HoaDon> page = this.hoaDonService.listByPage(pageNum, sortField, sortDir, keyword);
        List<HoaDon> listDonHang = page.getContent();
        long startCount = ((pageNum - 1) * 10 + 1);
        long endCount = startCount + 10L - 1L;

        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }
        List<StatusValue> statusValues = Arrays.asList(new StatusValue[]{new StatusValue(0, "Chờ giao hàng"), new StatusValue(1, "Đang chuẩn bị"), new StatusValue(2, "Đang giao hàng"), new StatusValue(3, "Hoàn thành"), new StatusValue(4, "đã hủy"), new StatusValue(5, "Yêu cầu hoàn trả"), new StatusValue(6, "Đã hoàn trả")});
        model.addAttribute("statusValues", statusValues);
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("currentPage", Integer.valueOf(pageNum));
        model.addAttribute("totalPages", Integer.valueOf(page.getTotalPages()));
        model.addAttribute("startCount", Long.valueOf(startCount));
        model.addAttribute("endCount", Long.valueOf(endCount));
        model.addAttribute("totalItem", Long.valueOf(page.getTotalElements()));
        model.addAttribute("listDonHang", listDonHang);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", reverseSortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sumAll", this.hoaDonService.tongSoDonHang());
        model.addAttribute("sumDHShipping", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(2)));
        model.addAttribute("sumDHDone", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(3)));
        model.addAttribute("sumDHCancel", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(4)));
        model.addAttribute("sumDHReturn", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(6)));

        return "ThongKe/ThongKe";
    }

    @GetMapping({"/hoa-don/{status}/{pageNum}"})
    private String listByPageStatus(@PathVariable(name = "pageNum") int pageNum, @PathVariable(name = "status") int status, Model model, @Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {
        Page<HoaDon> page;
        List<HoaDon> listDonHang;
        HttpSession session = this.request.getSession();

        if (status == 10) {
            page = this.hoaDonService.listByPage(pageNum, sortField, sortDir, keyword);
            listDonHang = page.getContent();
        } else {
            page = this.hoaDonService.listByPageStatus(pageNum, sortField, sortDir, keyword, status);
            listDonHang = page.getContent();
        }
        long startCount = ((pageNum - 1) * 10 + 1);
        long endCount = Math.min(startCount + 10L - 1L, page.getTotalElements());
        List<StatusValue> statusValues = Arrays.asList(new StatusValue[]{new StatusValue(0, "Chờ giao hàng"), new StatusValue(1, "Đang chuẩn bị"), new StatusValue(2, "Đang giao hàng"), new StatusValue(3, "Hoàn thành"), new StatusValue(4, "đã hủy"), new StatusValue(5, "Yêu cầu hoàn trả"), new StatusValue(6, "Đã hoàn trả")});
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("statusValues", statusValues);
        model.addAttribute("currentPage", Integer.valueOf(pageNum));
        model.addAttribute("totalPages", Integer.valueOf(page.getTotalPages()));
        model.addAttribute("startCount", Long.valueOf(startCount));
        model.addAttribute("endCount", Long.valueOf(endCount));
        model.addAttribute("totalItem", Long.valueOf(page.getTotalElements()));
        model.addAttribute("listDonHang", listDonHang);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", reverseSortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sumAll", this.hoaDonService.tongSoDonHang());
        model.addAttribute("sumDHShipping", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(2)));
        model.addAttribute("sumDHDone", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(3)));
        model.addAttribute("sumDHCancel", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(4)));
        model.addAttribute("sumDHReturn", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(6)));
        model.addAttribute("selectedStatus", Integer.valueOf(status));
        return "ThongKe/ThongKe";
    }
}
