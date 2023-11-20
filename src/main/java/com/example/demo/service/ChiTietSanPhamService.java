package com.example.demo.service;


import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.repository.ChiTietSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietSanPhamService {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    public List<ChiTietSanPham> getAll() {
        return chiTietSanPhamRepository.findAll();
    }

    public List<ChiTietSanPham> searchSanPham(String ten) {
        return chiTietSanPhamRepository.findChiTietSanPhamByTenSanPhamContaining(ten);
    }

    public ChiTietSanPham findChiTietSanPhamById(Integer idChiTietSanPham) {
        return chiTietSanPhamRepository.findChiTietSanPhamByIdChiTietSanPham(idChiTietSanPham);
    }


    public ChiTietSanPham getById(Integer id) {
        return chiTietSanPhamRepository.findById(id).orElse(null);
    }

    public void addChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        chiTietSanPhamRepository.save(chiTietSanPham);
    }

    public void updateChiTietSanPham(Integer id, ChiTietSanPham chiTietSanPham) {
        ChiTietSanPham existingChiTietSanPham = chiTietSanPhamRepository.findById(id).orElse(null);
        if (existingChiTietSanPham != null) {
            // Cập nhật thông tin của chi tiết sản phẩm
            existingChiTietSanPham.setTenSanPham(chiTietSanPham.getTenSanPham());
            existingChiTietSanPham.setLoaiSanPham(chiTietSanPham.getLoaiSanPham());
            existingChiTietSanPham.setChatLieu(chiTietSanPham.getChatLieu());
            existingChiTietSanPham.setSize(chiTietSanPham.getSize());
            existingChiTietSanPham.setPhongCach(chiTietSanPham.getPhongCach());
            existingChiTietSanPham.setNsx(chiTietSanPham.getNsx());
            existingChiTietSanPham.setMauSac(chiTietSanPham.getMauSac());
            existingChiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong());
            existingChiTietSanPham.setGiaBan(chiTietSanPham.getGiaBan());
            existingChiTietSanPham.setTrangThai(chiTietSanPham.getTrangThai());
            // Cập nhật các trường khác
            // ...
            chiTietSanPhamRepository.save(existingChiTietSanPham);
        }
    }

    public void deleteChiTietSanPham(Integer id) {
        chiTietSanPhamRepository.deleteById(id);
    }
}


