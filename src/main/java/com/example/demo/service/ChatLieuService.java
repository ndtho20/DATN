package com.example.demo.service;


import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.NSX;
import com.example.demo.repository.ChatLieuRepository;
import com.example.demo.repository.ChiTietSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatLieuService {

    @Autowired
    private ChatLieuRepository repository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    public List<ChatLieu> getAll() {
        return repository.findAll();
    }

    public ChatLieu addChatLieu(ChatLieu ChatLieu) {
        return repository.save(ChatLieu);
    }

    public ChatLieu getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void updateChatLieu(int Id, ChatLieu ChatLieu) {
        Optional<ChatLieu> existingChatLieu = repository.findById(Id);

        if (existingChatLieu.isPresent()) {
            ChatLieu updatedChatLieu = existingChatLieu.get();
            updatedChatLieu.setMa(ChatLieu.getMa());
            updatedChatLieu.setTen(ChatLieu.getTen());
            updatedChatLieu.setNgayTao(ChatLieu.getNgayTao());
            updatedChatLieu.setTrangThai(ChatLieu.getTrangThai());
            repository.save(updatedChatLieu);
        }
    }

    public void deleteChatLieu(int id) {
        if (chiTietSanPhamRepository.countByChatLieuId(id) == 0) {
            repository.deleteById(id);
        } else {
            Optional<ChatLieu> existingNSX = repository.findById(id);
            ChatLieu updatedNSX = existingNSX.get();
            updatedNSX.setTrangThai(false);
            repository.save(updatedNSX);
        }
    }

    public List<ChatLieu> findChatLieuByMa(String ma) {
        return repository.findByMa(ma);
    }

}
