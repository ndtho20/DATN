package com.example.demo.controller;


import com.example.demo.entity.NSX;
import com.example.demo.service.NSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/NSX")
public class NSXController {

    @Autowired
    private NSXService service;

    @GetMapping()
    public String getAll(Model model) {
        List<NSX> dsNSX = service.getAll();
        model.addAttribute("dsNSX", dsNSX);
        model.addAttribute("pc", new NSX());
        return "NSX/Index";
    }

    @PostMapping("/add")
    public String addNSX(@ModelAttribute NSX NSX, Model model) {
        NSX.setTrangThai(true);
        service.addNSX(NSX);
        List<NSX> dsNSX = service.getAll();
        model.addAttribute("dsNSX", dsNSX);
        return "redirect:/NSX";
    }

    @GetMapping("/detail/{id}")
    public String editNSXForm(@PathVariable("id") int Id, Model model) {
        NSX NSX = service.getById(Id);
        model.addAttribute("NSX", NSX);
        return "NSX/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateNSX(@PathVariable("id") int id, @ModelAttribute NSX NSX) {
        service.updateNSX(id, NSX);
        return "redirect:/NSX";
    }

    @GetMapping("/delete/{id}")
    public String deleteNSX(@PathVariable("id") int id) {
        service.deleteNSX(id);
        return "redirect:/NSX";
    }
}
