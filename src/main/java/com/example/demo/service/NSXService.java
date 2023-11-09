package com.example.demo.service;


import com.example.demo.entity.NSX;
import com.example.demo.repository.NSXRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NSXService {

    @Autowired
    private NSXRepository repository;

    public List<NSX> getAll() {
        return repository.findAll();
    }

    public NSX addNSX(NSX NSX) {
        return repository.save(NSX);
    }

    public NSX getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void updateNSX(int Id, NSX NSX) {
        Optional<NSX> existingNSX = repository.findById(Id);

        if (existingNSX.isPresent()) {
            NSX updatedNSX = existingNSX.get();
            updatedNSX.setMa(NSX.getMa());
            updatedNSX.setTen(NSX.getTen());
            updatedNSX.setNgayTao(NSX.getNgayTao());
            updatedNSX.setTrangThai(NSX.getTrangThai());
            repository.save(updatedNSX);
        }
    }

    public void deleteNSX(int id) {
        repository.deleteById(id);
    }
}
