package com.example.demo.controller;

import com.example.demo.entity.PhongCach;
import com.example.demo.entity.Size;
import com.example.demo.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/Size")
public class SizeController {

    @Autowired
    private SizeService service;

    @GetMapping()
    public String getAll(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 2;

        List<Size> list = service.getAll();
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        List<Size> currentPageSize = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

        model.addAttribute("dsSize", currentPageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("pc", new Size());
        return "Size/Index";

    }

    @PostMapping("/add")
    public String addSize(@Validated @ModelAttribute("pc") Size size, BindingResult result, Model model, RedirectAttributes redirectAttributes
            , @RequestParam(defaultValue = "0") int page) {
        size.setTrangThai(true);
        if (result.hasErrors()) {
            int pageSize = 2;

            List<Size> list = service.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<Size> currentPageSize = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsSize", currentPageSize);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("pc", size);
//            redirectAttributes.addFlashAttribute("dsSize", dsSize); // Giữ lại giá trị đã submit
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đúng thông tin!");
            return "Size/Index";
        } else {
            service.addSize(size);
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/Size";
        }
    }

    @GetMapping("/detail/{id}")
    public String editSizeForm(@PathVariable("id") int Id, Model model) {
        Size size = service.getById(Id);
        model.addAttribute("ms", size);
        return "Size/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateSize(@PathVariable("id") int id, @Validated @ModelAttribute("ms") Size size, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Size> dsSize = service.getAll();
            model.addAttribute("pc", dsSize);
            model.addAttribute("dsSize", dsSize); // Giữ lại giá trị đã submit

            return "Size/Detail";
        } else {


            service.updateSize(id, size);
            return "redirect:/Size";
        }
    }


    @GetMapping("/delete")
    public String deleteSize(@RequestParam("id") int id) {
        service.deleteSize(id);
        return "redirect:/Size";
    }

    @PostMapping("search")
    public String searchProductByCode(@RequestParam String ma, Model model, @RequestParam(defaultValue = "0") int page) {

        System.out.println(ma);

        if (ma == null || ma.isBlank()) {
            int pageSize = 2;

            List<Size> list = service.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<Size> currentPageSize = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsSize", currentPageSize);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);

            model.addAttribute("pc", new Size());
        } else {
            int pageSize = 2;
            List<Size> list = service.findSizeByMa(ma);
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<Size> currentPageSize = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsSize", currentPageSize);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);


            model.addAttribute("pc", new Size());
        }
        return "Size/Index";

    }
}
