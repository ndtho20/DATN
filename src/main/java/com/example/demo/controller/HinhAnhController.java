package com.example.demo.controller;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.FileUploadUtil;
import com.example.demo.entity.HinhAnh;
import com.example.demo.entity.PhongCach;
import com.example.demo.service.ChiTietSanPhamService;
import com.example.demo.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/hinhanh")
public class HinhAnhController {
    @Autowired
    private HinhAnhService hinhAnhService;
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @GetMapping()
    public String getAll(Model model) {
        List<HinhAnh> dsHinhAnh = hinhAnhService.getAll();
        model.addAttribute("dsHinhAnh", dsHinhAnh);
        model.addAttribute("pc", new HinhAnh());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.getAll());
        return "HinhAnh/Index";
    }

//    @GetMapping("/detail/{id}")
//    public String editHinhAnhForm(@PathVariable("id") Integer id, Model model) {
//        List<ChiTietSanPham> ctsp = chiTietSanPhamService.getAll();
//        HinhAnh hinhAnh = hinhAnhService.getById(id);
//        model.addAttribute("hinhAnh", hinhAnh);
//        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getById(hinhAnh.getChiTietSanPham().getIdChiTietSanPham());
//        model.addAttribute("chiTietSanPham", ctsp);
//
//        return "HinhAnh/Detail";
//    }


    @PostMapping("/add")
    public String addHinhAnh(@Validated @ModelAttribute("pc") HinhAnh hinhAnh, BindingResult result, Model model, RedirectAttributes redirectAttributes,   @RequestParam("idchitietsanpham") Integer idChiTietSanPham) {
        hinhAnh.setTrangThai(true);
        if (result.hasErrors()) {
            List<HinhAnh> dsHinhAnh = hinhAnhService.getAll();
            model.addAttribute("dsHinhAnh", dsHinhAnh);
            model.addAttribute("listChiTietSanPham", chiTietSanPhamService.getAll());
            model.addAttribute("pc", dsHinhAnh);
            redirectAttributes.addFlashAttribute("dsHinhAnh", dsHinhAnh); // Giữ lại giá trị đã submit
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đúng thông tin!");
            return "HinhAnh/Index";
        } else{
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.findChiTietSanPhamById(idChiTietSanPham);
            hinhAnh.setChiTietSanPham(chiTietSanPham);
            hinhAnhService.addHinhAnh(hinhAnh);
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/hinhanh";
        }
    }


    // Xử lý cập nhật hình ảnh
    @PostMapping("/update/{id}")
    public String updateHinhAnh(@PathVariable("id") Integer id,
                                @RequestParam("fileImage") MultipartFile fileImage,
                                @RequestParam("idchitietsanpham") Integer idChiTietSanPham,
                                RedirectAttributes ra) {

        try {
            // Lấy hình ảnh cần cập nhật từ cơ sở dữ liệu
            HinhAnh existingHinhAnh = hinhAnhService.getById(id);

            // Cập nhật thông tin hình ảnh
            existingHinhAnh.setChiTietSanPham(chiTietSanPhamService.findChiTietSanPhamById(idChiTietSanPham));

            // Kiểm tra nếu người dùng đã tải lên một file mới
            if (!fileImage.isEmpty()) {
                String fileName = fileUploadUtil.saveFile(fileImage);
                existingHinhAnh.setDuongDan(fileName);
            }

            // Lưu hình ảnh cập nhật vào cơ sở dữ liệu
            hinhAnhService.updateHinhAnh(id,existingHinhAnh);

            ra.addFlashAttribute("message", "Cập nhật hình ảnh thành công.");
        } catch (IOException e) {
            ra.addFlashAttribute("message", "Lỗi khi cập nhật hình ảnh: " + e.getMessage());
        }

        return "redirect:/hinhanh";
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        HinhAnh hinhAnh = hinhAnhService.getById(id);
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.getAll();

        model.addAttribute("hinhAnh", hinhAnh);
        model.addAttribute("listChiTietSanPham", listChiTietSanPham);

        return "HinhAnh/Detail";
    }
    @GetMapping("/delete")
    public String deleteHinhAnh(@RequestParam("id") Integer id) {
        hinhAnhService.deleteHinhAnh(id);
        return "redirect:/hinhanh";
    }
}