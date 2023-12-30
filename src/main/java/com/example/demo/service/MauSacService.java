package com.example.demo.service;


import com.example.demo.entity.MauSac;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacService {

    @Autowired
    private MauSacRepository repository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    public List<MauSac> getAll() {
        return repository.findAll();
    }

    public MauSac addMauSac(MauSac MauSac) {
        return repository.save(MauSac);
    }

    public MauSac getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void updateMauSac(int Id, MauSac MauSac) {
        Optional<MauSac> existingMauSac = repository.findById(Id);

        if (existingMauSac.isPresent()) {
            MauSac updatedMauSac = existingMauSac.get();
            updatedMauSac.setMa(MauSac.getMa());
            updatedMauSac.setTen(MauSac.getTen());
            updatedMauSac.setNgayTao(MauSac.getNgayTao());
            updatedMauSac.setTrangThai(MauSac.getTrangThai());
            repository.save(updatedMauSac);
        }
    }

    public void deleteMauSac(int id) {
        if (chiTietSanPhamRepository.countByMauSacId(id) == 0) {
            repository.deleteById(id);
        } else {
            Optional<MauSac> existingNSX = repository.findById(id);
            MauSac updatedNSX = existingNSX.get();
            updatedNSX.setTrangThai(false);
            repository.save(updatedNSX);
        }
    }

    public List<MauSac> findMauSacByMa(String ma) {
        return repository.findByMa(ma);
    }

}
