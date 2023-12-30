package com.example.demo.controller;


import com.example.demo.entity.MauSac;
import com.example.demo.entity.NSX;
import com.example.demo.service.NSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/NSX")
public class NSXController {

    @Autowired
    private NSXService service;

    @GetMapping()
    public String getAll(Model model, @RequestParam(defaultValue = "0") int page) {

        int pageSize = 2;

        List<NSX> list = service.getAll();
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        List<NSX> currentPageNSX = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

        model.addAttribute("dsNSX", currentPageNSX);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("pc", new NSX());
        return "NSX/Index";
    }

    @PostMapping("/add")
    public String addNSX(@Validated @ModelAttribute("pc") NSX NSX, BindingResult result, Model model, RedirectAttributes redirectAttributes
            , @RequestParam(defaultValue = "0") int page) {
        NSX.setTrangThai(true);
        if (result.hasErrors()) {
            int pageSize = 2;

            List<NSX> list = service.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<NSX> currentPageNSX = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsNSX", currentPageNSX);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("pc", NSX);
//            redirectAttributes.addFlashAttribute("dsNSX", dsNSX); // Giữ lại giá trị đã submit
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đúng thông tin!");
            return "NSX/Index";
        } else {
            service.addNSX(NSX);
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/NSX";
        }

    }

    @GetMapping("/detail/{id}")
    public String editNSXForm(@PathVariable("id") int Id, Model model) {
        NSX NSX = service.getById(Id);
        model.addAttribute("ms", NSX);
        return "NSX/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateNSX(@PathVariable("id") int id, @Validated @ModelAttribute("ms") NSX NSX, BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<NSX> dsNSX = service.getAll();
            model.addAttribute("pc", NSX);
            model.addAttribute("dsNSX", dsNSX); // Giữ lại giá trị đã submit

            return "NSX/Detail";
        } else {


            service.updateNSX(id, NSX);
            return "redirect:/NSX";
        }
    }

    @GetMapping("/delete")
    public String deleteNSX(@RequestParam("id") int id) {
        service.deleteNSX(id);
        return "redirect:/NSX";
    }


    @PostMapping("search")
    public String searchProductByCode(@RequestParam String ma, Model model, @RequestParam(defaultValue = "0") int page) {

        System.out.println(ma);

        if (ma == null || ma.isBlank()) {
            int pageSize = 2;

            List<NSX> list = service.getAll();
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<NSX> currentPageNSX = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsNSX", currentPageNSX);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);

            model.addAttribute("pc", new NSX());
        } else {
            int pageSize = 2;
            List<NSX> list = service.findNSXByMa(ma);
            int totalPages = (int) Math.ceil((double) list.size() / pageSize);
            List<NSX> currentPageNSX = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

            model.addAttribute("dsNSX", currentPageNSX);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);


            model.addAttribute("pc", new NSX());
        }
        return "NSX/Index";

    }

}
