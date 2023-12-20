package com.example.demo.controller;


import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.PhongCach;
import com.example.demo.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ChatLieu")
public class ChatLieuController {

    @Autowired
    private ChatLieuService service;

    @GetMapping()
    public String getAll(Model model) {
        List<ChatLieu> dsChatLieu = service.getAll();
        model.addAttribute("dsChatLieu", dsChatLieu);
        model.addAttribute("pc", new ChatLieu());
        return "ChatLieu/Index";
    }

    @PostMapping("/add")
    public String addChatLieu(@Validated @ModelAttribute("pc") ChatLieu chatLieu, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        chatLieu.setTrangThai(true);
        if (result.hasErrors()) {
            List<ChatLieu> dsChatLieu = service.getAll();
            model.addAttribute("dsChatLieu", dsChatLieu);
            model.addAttribute("pc", chatLieu);
            redirectAttributes.addFlashAttribute("dsChatLieu", dsChatLieu); // Giữ lại giá trị đã submit
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đúng thông tin!");
            return "ChatLieu/Index";
        } else {
            service.addChatLieu(chatLieu);
            redirectAttributes.addFlashAttribute("successMessage", "Dữ liệu đã được thêm thành công!");
            return "redirect:/ChatLieu";
        }
    }

    @GetMapping("/detail/{id}")
    public String editChatLieuForm(@PathVariable("id") int Id, Model model) {
        ChatLieu ChatLieu = service.getById(Id);
        model.addAttribute("ms", ChatLieu);
        return "ChatLieu/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateChatLieu(@PathVariable("id") int id, @Validated @ModelAttribute("ms") ChatLieu chatLieu, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ChatLieu> dsPhongCach = service.getAll();
            model.addAttribute("pc", dsPhongCach);
            model.addAttribute("dsChatLieu", dsPhongCach); // Giữ lại giá trị đã submit

            return "ChatLieu/Detail";
        } else {


            service.updateChatLieu(id, chatLieu);
            return "redirect:/ChatLieu";
        }
    }

    @GetMapping("/delete")
    public String deleteChatLieu(@RequestParam("id") int id) {
        service.deleteChatLieu(id);
        return "redirect:/ChatLieu";
    }
}
