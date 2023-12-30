package com.example.demo.service;


import com.example.demo.entity.PhongCach;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.PhongCachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhongCachService {

    @Autowired
    private PhongCachRepository repository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    public List<PhongCach> getAll() {
        return repository.findAll();
    }

    public PhongCach addPhongCach(PhongCach phongCach) {
        return repository.save(phongCach);
    }

    public PhongCach getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void updatePhongCach(int Id, PhongCach PhongCach) {
        Optional<PhongCach> existingPhongCach = repository.findById(Id);

        if (existingPhongCach.isPresent()) {
            PhongCach updatedPhongCach = existingPhongCach.get();
            updatedPhongCach.setMa(PhongCach.getMa());
            updatedPhongCach.setTen(PhongCach.getTen());
            updatedPhongCach.setNgayTao(PhongCach.getNgayTao());
            updatedPhongCach.setTrangThai(PhongCach.getTrangThai());
            repository.save(updatedPhongCach);
        }
    }

    public void deletePhongCach(int id) {
        if (chiTietSanPhamRepository.countByPhongCachId(id) == 0) {
            repository.deleteById(id);
        } else {
            Optional<PhongCach> existingNSX = repository.findById(id);
            PhongCach updatedNSX = existingNSX.get();
            updatedNSX.setTrangThai(false);
            repository.save(updatedNSX);
        }
    }

    public List<PhongCach> findPhongCachByMa(String ma) {
        return repository.findByMa(ma);
    }

}
