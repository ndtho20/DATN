package com.example.demo.controller;

import com.example.demo.entity.MauSac;
import com.example.demo.service.MauSacService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/MauSac")
public class MauSacController {

    @Autowired
    private MauSacService service;

    @GetMapping()
    public String getAll(Model model) {
        List<MauSac> dsMauSac = service.getAll();
        model.addAttribute("dsMauSac", dsMauSac);
        model.addAttribute("pc", new MauSac());
        return "MauSac/Index";
    }

    @PostMapping("/add")
    public String addMauSac(@Validated @ModelAttribute("pc") MauSac MauSac, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        MauSac.setTrangThai(true);
        if (result.hasErrors()) {
            List<MauSac> dsMauSac = service.getAll();
            model.addAttribute("dsMauSac", dsMauSac);
            model.addAttribute("pc", MauSac);
            redirectAttributes.addFlashAttribute("dsMauSac", dsMauSac); // Giữ lại giá trị đã submit
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đúng thông tin!");
            return "MauSac/Index";
        } else {
            service.addMauSac(MauSac);
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/MauSac";
        }
    }

    @GetMapping("/detail/{id}")
    public String editMauSacForm(@PathVariable("id") int Id, Model model) {
        MauSac MauSac = service.getById(Id);
        model.addAttribute("ms", MauSac);
        return "MauSac/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateMauSac(@PathVariable("id") int id, @Validated @ModelAttribute("ms") MauSac MauSac, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<MauSac> dsMauSac = service.getAll();
            model.addAttribute("pc", MauSac);
            model.addAttribute("dsMauSac", dsMauSac); // Giữ lại giá trị đã submit

            return "MauSac/Detail";
        } else {


            service.updateMauSac(id, MauSac);
            return "redirect:/MauSac";
        }
    }


    @GetMapping("/delete")
    public String deleteMauSac(@RequestParam("id") int id) {
        service.deleteMauSac(id);
        return "redirect:/MauSac";
    }
}
