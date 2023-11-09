package com.example.demo.service;

import com.example.demo.entity.LoaiSanPham;
import com.example.demo.repository.LoaiSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiSanPhamService {
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;

    public List<LoaiSanPham> getAll() {
        return loaiSanPhamRepository.findAll();
    }

    ;

    public LoaiSanPham addSanPham(LoaiSanPham loaiSanPham) {
        return loaiSanPhamRepository.save(loaiSanPham);
    }

    public LoaiSanPham getById(int id) {
        return loaiSanPhamRepository.findById(id).orElse(null);
    }

    public void updateSanPham(int Id, LoaiSanPham loaiSanPham) {
        Optional<LoaiSanPham> existingSanPham = loaiSanPhamRepository.findById(Id);

        if (existingSanPham.isPresent()) {
            LoaiSanPham updatedLoaiSanPham = existingSanPham.get();
            updatedLoaiSanPham.setMa(loaiSanPham.getMa());
            updatedLoaiSanPham.setTen(loaiSanPham.getTen());
            updatedLoaiSanPham.setNgayTao(loaiSanPham.getNgayTao());
            updatedLoaiSanPham.setTrangThai(loaiSanPham.getTrangThai());
            loaiSanPhamRepository.save(updatedLoaiSanPham);
        }
    }

    public void deleteSanPham(int id) {
        loaiSanPhamRepository.deleteById(id);
    }
}
