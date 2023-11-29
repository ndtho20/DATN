package com.example.demo.controller;

import com.example.demo.entity.Size;
import com.example.demo.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Size")
public class SizeController {

    @Autowired
    private SizeService service;

    @GetMapping()
    public String getAll(Model model) {
        List<Size> dsSize = service.getAll();
        model.addAttribute("dsSize", dsSize);
        model.addAttribute("s", new Size());
        return "Size/Index";
    }

    @PostMapping("/add")
    public String addSize(@ModelAttribute Size size, Model model) {
        size.setTrangThai(true);
        service.addSize(size);
        List<Size> dsSize = service.getAll();
        model.addAttribute("dsSize", dsSize);
        return "redirect:/Size";
    }

    @GetMapping("/detail/{id}")
    public String editSizeForm(@PathVariable("id") int Id, Model model) {
        Size size = service.getById(Id);
        model.addAttribute("size", size);
        return "Size/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateSize(@PathVariable("id") int id, @ModelAttribute Size size) {
        service.updateSize(id, size);
        return "redirect:/Size";
    }


    @GetMapping("/delete/{id}")
    public String deleteSize(@PathVariable("id") int id) {
        service.deleteSize(id);
        return "redirect:/Size";
    }
}
