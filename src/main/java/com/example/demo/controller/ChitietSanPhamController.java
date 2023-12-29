package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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

    @Autowired
    private HinhAnhService hinhAnhService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

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
<<<<<<< HEAD
    public String addChiTietSanPham(@Validated @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, BindingResult result, Model model, RedirectAttributes redirectAttributes,
                                    @RequestParam("fileImages") MultipartFile[] fileImages) {
        chiTietSanPham.setTrangThai(true);
        if (result.hasErrors()) {
            List<ChiTietSanPham> dsHinhAnh = chiTietSanPhamService.getAll();
            model.addAttribute("listLoaiSanPham", loaiSanPhamService.getAll());
            model.addAttribute("listChatLieu", chatLieuService.getAll());
            model.addAttribute("listSize", sizeService.getAll());
            model.addAttribute("listPhongCach", phongCachService.getAll());
            model.addAttribute("listNSX", nsxService.getAll());
            model.addAttribute("listMauSac", mauSacService.getAll());


            model.addAttribute("dsChiTietSanPham", dsHinhAnh);
            model.addAttribute("chiTietSanPham", chiTietSanPham);
            redirectAttributes.addFlashAttribute("dsChiTietSanPham", dsHinhAnh); // Giữ lại giá trị đã submit
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đúng thông tin!");
            return "ChiTietSanPham/Index";
        } else{
            chiTietSanPhamService.addChiTietSanPham(chiTietSanPham);
            if (fileImages.length == 0) {
                redirectAttributes.addFlashAttribute("message", "Vui lòng chọn ít nhất một file hình ảnh.");
                return "redirect:/chitietsanpham";
            }

            try {
                for (MultipartFile fileImage : fileImages) {
                    // Lưu file vào thư mục tạm thời hoặc bất kỳ logic lưu trữ file nào bạn muốn
                    String fileName = fileUploadUtil.saveFile(fileImage);

                    // Tạo đối tượng HinhAnh và cập nhật thông tin
                    HinhAnh hinhAnh = new HinhAnh();
                    hinhAnh.setDuongDan(fileName);

                    // Sử dụng id đã lưu của ChiTietSanPham để thiết lập liên kết
                    hinhAnh.setChiTietSanPham(chiTietSanPham);

                    // Lưu đối tượng HinhAnh vào cơ sở dữ liệu
                    hinhAnhService.addHinhAnh(hinhAnh);
                }

                redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm và hình ảnh thành công.");
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("message", "Lỗi khi thêm hình ảnh: " + e.getMessage());
            }
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/chitietsanpham";
        }

    }
=======
        public String addChiTietSanPham(@Validated @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, BindingResult result, Model model, RedirectAttributes redirectAttributes,
        @RequestParam("fileImages") MultipartFile[] fileImages) {
            chiTietSanPham.setTrangThai(true);
            if (result.hasErrors()) {
                List<ChiTietSanPham> dsHinhAnh = chiTietSanPhamService.getAll();
                model.addAttribute("listLoaiSanPham", loaiSanPhamService.getAll());
                model.addAttribute("listChatLieu", chatLieuService.getAll());
                model.addAttribute("listSize", sizeService.getAll());
                model.addAttribute("listPhongCach", phongCachService.getAll());
                model.addAttribute("listNSX", nsxService.getAll());
                model.addAttribute("listMauSac", mauSacService.getAll());


                model.addAttribute("dsChiTietSanPham", dsHinhAnh);
                model.addAttribute("chiTietSanPham", chiTietSanPham);
                redirectAttributes.addFlashAttribute("dsChiTietSanPham", dsHinhAnh); // Giữ lại giá trị đã submit
                redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đúng thông tin!");
                return "ChiTietSanPham/Index";
            } else{
                chiTietSanPhamService.addChiTietSanPham(chiTietSanPham);
                if (fileImages.length == 0) {
                    redirectAttributes.addFlashAttribute("message", "Vui lòng chọn ít nhất một file hình ảnh.");
                    return "redirect:/chitietsanpham";
                }

                try {
                    for (MultipartFile fileImage : fileImages) {
                        // Lưu file vào thư mục tạm thời hoặc bất kỳ logic lưu trữ file nào bạn muốn
                        String fileName = fileUploadUtil.saveFile(fileImage);

                        // Tạo đối tượng HinhAnh và cập nhật thông tin
                        HinhAnh hinhAnh = new HinhAnh();
                        hinhAnh.setDuongDan(fileName);

                        // Sử dụng id đã lưu của ChiTietSanPham để thiết lập liên kết
                        hinhAnh.setChiTietSanPham(chiTietSanPham);

                        // Lưu đối tượng HinhAnh vào cơ sở dữ liệu
                        hinhAnhService.addHinhAnh(hinhAnh);
                    }

                    redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm và hình ảnh thành công.");
                } catch (IOException e) {
                    redirectAttributes.addFlashAttribute("message", "Lỗi khi thêm hình ảnh: " + e.getMessage());
                }
                redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
                return "redirect:/chitietsanpham";
            }

        }
>>>>>>> 74f929091249846da6bc569af8ec5a38b9babd9f

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
    public String updateChiTietSanPham(@PathVariable("id") Integer id, @Validated @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham,BindingResult result,Model model) {
        if (result.hasErrors()) {
            List<ChiTietSanPham> dsHinhAnh = chiTietSanPhamService.getAll();
            model.addAttribute("listLoaiSanPham", loaiSanPhamService.getAll());
            model.addAttribute("listChatLieu", chatLieuService.getAll());
            model.addAttribute("listSize", sizeService.getAll());
            model.addAttribute("listPhongCach", phongCachService.getAll());
            model.addAttribute("listNSX", nsxService.getAll());
            model.addAttribute("listMauSac", mauSacService.getAll());


            model.addAttribute("dsChiTietSanPham", dsHinhAnh);
            model.addAttribute("chiTietSanPham", chiTietSanPham);
            return "ChiTietSanPham/Detail";
        }else {
            chiTietSanPhamService.updateChiTietSanPham(id, chiTietSanPham);
            return "redirect:/chitietsanpham";
        }
    }

    @GetMapping("/delete")
    public String deleteChiTietSanPham(@RequestParam("id") Integer id) {
        chiTietSanPhamService.deleteChiTietSanPham(id);
        return "redirect:/chitietsanpham";
    }
}