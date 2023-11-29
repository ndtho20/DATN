package com.example.demo.config;

import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NhanVien nhanVien = nhanVienRepository.findNhanVienByEmail(username);
        if (nhanVien != null) {
            return new CustomUser(nhanVien, "ROLE_ADMIN");
        }
        KhachHang khachHang = khachHangRepository.findKhachHangByEmail(username);
        if (khachHang != null) {
            return new CustomUser(khachHang, "ROLE_USER");
        }

        throw new UsernameNotFoundException("User not found");
    }
}
