package com.example.demo.controller;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/khachhang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping()
    public String getAll(Model model, @RequestParam(defaultValue = "0") int page) {

        int pageSize = 2;

        List<KhachHang> list = khachHangService.getAll();
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        List<KhachHang> currentPageKhachHang = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

        model.addAttribute("dsKhachHang", currentPageKhachHang);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("kh", new KhachHang());
        return "KhachHang/Index";
    }

    @PostMapping("/add")
    public String addKhachHang(@Validated @ModelAttribute("kh") KhachHang khachHang, BindingResult result, Model model, RedirectAttributes redirectAttributes
            , @RequestParam(defaultValue = "0") int page) {
        khachHang.setTrangThai(true);
        if (result.hasErrors()) {
            int pageSize = 2;

            List<KhachHang> list = khachHangService.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<KhachHang> currentPageKhachHang = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsKhachHang", currentPageKhachHang);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
//            model.addAttribute("dsKhachHang", dsKhachHang);
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đúng thông tin!");
            model.addAttribute("kh", khachHang);
            return "KhachHang/Index";
        } else {
            khachHangService.addKhachHang(khachHang);
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/khachhang";
        }
    }

    @GetMapping("/detail/{id}")
    public String editKhachHangForm(@PathVariable("id") int Id, Model model) {
        KhachHang khachHang = khachHangService.getById(Id);
        model.addAttribute("kh", khachHang);
        return "KhachHang/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateKhachHang(@PathVariable("id") int id, @Validated @ModelAttribute("kh") KhachHang khachHang, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<KhachHang> dsHinhAnh = khachHangService.getAll();
            model.addAttribute("dsKhachHang", dsHinhAnh);
            model.addAttribute("kh", khachHang);
            return "KhachHang/Detail";
        }

        khachHangService.updateKhachHang(id, khachHang);
        return "redirect:/khachhang";
    }


    @GetMapping("/delete")
    public String deleteKhachHang(@RequestParam("id") int id) {
        khachHangService.deleteKhachHang(id);
        return "redirect:/khachhang";
    }

    @GetMapping("/thongtinkhachhang/{id}")
    public String info(@PathVariable("id") int Id, Model model) {
        KhachHang nhanVien = khachHangService.getById(Id);
        model.addAttribute("khachHang", nhanVien);
        return "clients/profile-info";
    }

    @PostMapping("/updatethongtinkhachhang/{id}")
    public String updatePassword(@PathVariable("id") int id,
                                 @RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("ten") String ten,
                                 @RequestParam("ngaySinh") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngaySinh,
                                 @RequestParam("email") String email,
                                 @RequestParam("soDienThoai") String soDienThoai,
                                 Model model, RedirectAttributes redirectAttributes) {

        KhachHang existingKhachHang = khachHangService.getById(id); // Lấy thông tin khách hàng từ cơ sở dữ liệu

        if (existingKhachHang != null && existingKhachHang.getMatKhau().equals(currentPassword)) {
            // Xác thực mật khẩu hiện tại
            if (!newPassword.isEmpty()) {
                // Nếu newPassword không rỗng, thì cập nhật mật khẩu mới
                // Kiểm tra tính hợp lệ của mật khẩu mới (ví dụ: đủ độ dài, đủ loại ký tự, v.v.)
                // Ví dụ: độ dài ít nhất 8 ký tự và chứa ít nhất một chữ hoa và một chữ số
                if (isValidPassword(newPassword)) {
                    // Nếu mật khẩu mới hợp lệ, cập nhật mật khẩu trong cơ sở dữ liệu
                    existingKhachHang.setMatKhau(newPassword);
                    existingKhachHang.setSoDienThoai(soDienThoai);
                    existingKhachHang.setEmail(email);
                    existingKhachHang.setNgaySinh(ngaySinh);
                    existingKhachHang.setTen(ten);
                    khachHangService.updateKhachHang(id, existingKhachHang);
                    redirectAttributes.addFlashAttribute("successMessage", "Thông tin khách hàng đã được cập nhật thành công!");
                    return "redirect:/khachhang/thongtinkhachhang/{id}";
                } else {
                    KhachHang nhanVien = khachHangService.getById(id);
                    model.addAttribute("khachHang", nhanVien);
                    // Nếu mật khẩu mới không hợp lệ, hiển thị thông báo lỗi
                    model.addAttribute("error", "New password is invalid");
                    return "clients/profile-info";
                }
            } else {

                existingKhachHang.setSoDienThoai(soDienThoai);
                existingKhachHang.setEmail(email);
                existingKhachHang.setNgaySinh(ngaySinh);
                existingKhachHang.setTen(ten);
                khachHangService.updateKhachHang(id, existingKhachHang);
                redirectAttributes.addFlashAttribute("successMessage", "Thông tin khách hàng đã được cập nhật thành công!");
                return "redirect:/khachhang/thongtinkhachhang/{id}";
            }
        } else {
            KhachHang nhanVien = khachHangService.getById(id);
            model.addAttribute("khachHang", nhanVien);
            // Nếu mật khẩu hiện tại không khớp, hiển thị thông báo lỗi
            model.addAttribute("error", "Current password is incorrect");
            return "clients/profile-info";
        }

    }

    private boolean isValidPassword(String password) {
        // Viết logic kiểm tra tính hợp lệ của mật khẩu mới ở đây
        // Ví dụ: kiểm tra độ dài, yêu cầu chứa ký tự đặc biệt, chữ hoa, chữ thường, số, v.v.
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*");
    }

    @PostMapping("search")
    public String searchProductByCode(@RequestParam String ten, Model model, @RequestParam(defaultValue = "0") int page) {

        System.out.println(ten);

        if (ten == null || ten.isBlank()) {
            int pageSize = 2;

            List<KhachHang> list = khachHangService.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<KhachHang> currentPageKhachHang = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsKhachHang", currentPageKhachHang);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);

            model.addAttribute("kh", new KhachHang());
        } else {
            int pageSize = 2;
            List<KhachHang> list = khachHangService.findKhachHangByTen(ten);
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<KhachHang> currentPageKhachHang = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsKhachHang", currentPageKhachHang);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);

            model.addAttribute("kh", new KhachHang());
        }
        return "KhachHang/Index";

    }

}
