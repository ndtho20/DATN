package com.example.demo.controller;


import com.example.demo.entity.NSX;
import com.example.demo.entity.PhongCach;
import com.example.demo.service.PhongCachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/PhongCach")
public class PhongCachController {

    @Autowired
    private PhongCachService service;

    @GetMapping()
    public String getAll(Model model, @RequestParam(defaultValue = "0") int page) {

        int pageSize = 2;

        List<PhongCach> list = service.getAll();
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        List<PhongCach> currentPagePhongCach = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

        model.addAttribute("dsPhongCach", currentPagePhongCach);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("pc", new PhongCach());
        return "PhongCach/Index";
    }

    @PostMapping("/add")
    public String addPhongCach(@Validated @ModelAttribute("pc") PhongCach phongCach, BindingResult result, Model model, RedirectAttributes redirectAttributes
            , @RequestParam(defaultValue = "0") int page) {
        phongCach.setTrangThai(true);
        if (result.hasErrors()) {
            int pageSize = 2;

            List<PhongCach> list = service.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<PhongCach> currentPagePhongCach = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsPhongCach", currentPagePhongCach);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("pc", phongCach);
//            redirectAttributes.addFlashAttribute("dsPhongCach", dsPhongCach); // Giữ lại giá trị đã submit
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đúng thông tin!");
            return "PhongCach/Index";
        } else {
            service.addPhongCach(phongCach);
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/PhongCach";
        }
    }

    @GetMapping("/detail/{id}")
    public String editPhongCachForm(@PathVariable("id") int Id, Model model) {
        PhongCach phongCach = service.getById(Id);
        model.addAttribute("ms", phongCach);
        return "PhongCach/Detail";
    }

    @PostMapping("/update/{id}")
    public String updatePhongCach(@PathVariable("id") int id, @Validated @ModelAttribute("ms") PhongCach phongCach, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<PhongCach> dsPhongCach = service.getAll();
            model.addAttribute("pc", dsPhongCach);
            model.addAttribute("dsPhongCach", dsPhongCach); // Giữ lại giá trị đã submit

            return "PhongCach/Detail";
        } else {


            service.updatePhongCach(id, phongCach);
            return "redirect:/PhongCach";
        }
    }

    @GetMapping("/delete")
    public String deletePhongCach(@RequestParam("id") int id) {
        service.deletePhongCach(id);
        return "redirect:/PhongCach";
    }

    @PostMapping("search")
    public String searchProductByCode(@RequestParam String ma, Model model, @RequestParam(defaultValue = "0") int page) {

        System.out.println(ma);

        if (ma == null || ma.isBlank()) {
            int pageSize = 2;

            List<PhongCach> list = service.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<PhongCach> currentPagePhongCach = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsPhongCach", currentPagePhongCach);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);

            model.addAttribute("pc", new PhongCach());
        } else {
            int pageSize = 2;
            List<PhongCach> list = service.findPhongCachByMa(ma);
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<PhongCach> currentPagePhongCach = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsPhongCach", currentPagePhongCach);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);

            model.addAttribute("pc", new PhongCach());
        }
        return "PhongCach/Index";

    }
}
