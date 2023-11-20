package com.example.demo.controller.admin;


import com.example.demo.entity.ChatLieu;
import com.example.demo.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "Admin/ChatLieu/Index";
    }

    @PostMapping("/add")
    public String addChatLieu(@ModelAttribute ChatLieu ChatLieu, Model model) {
        ChatLieu.setTrangThai(true);
        service.addChatLieu(ChatLieu);
        List<ChatLieu> dsChatLieu = service.getAll();
        model.addAttribute("dsChatLieu", dsChatLieu);
        return "redirect:/ChatLieu";
    }

    @GetMapping("/detail/{id}")
    public String editChatLieuForm(@PathVariable("id") int Id, Model model) {
        ChatLieu ChatLieu = service.getById(Id);
        model.addAttribute("ChatLieu", ChatLieu);
        return "Admin/ChatLieu/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateChatLieu(@PathVariable("id") int id, @ModelAttribute ChatLieu ChatLieu) {
        service.updateChatLieu(id, ChatLieu);
        return "redirect:/ChatLieu";
    }

    @GetMapping("/delete/{id}")
    public String deleteChatLieu(@PathVariable("id") int id) {
        service.deleteChatLieu(id);
        return "redirect:/ChatLieu";
    }
}
