package com.example.demo.service;


import com.example.demo.entity.Size;
import com.example.demo.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService {

    @Autowired
    private SizeRepository repository;


    public List<Size> getAll() {
        return repository.findAll();
    }

    public Size addSize(Size size) {
        return repository.save(size);
    }

    public Size getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void updateSize(int Id, Size Size) {
        Optional<Size> existingSize = repository.findById(Id);

        if (existingSize.isPresent()) {
            Size updatedSize = existingSize.get();
            updatedSize.setMa(Size.getMa());
            updatedSize.setTen(Size.getTen());
            updatedSize.setNgayTao(Size.getNgayTao());
            updatedSize.setTrangThai(Size.getTrangThai());
            repository.save(updatedSize);
        }
    }

    public void deleteSize(int id) {
        repository.deleteById(id);
    }

}
