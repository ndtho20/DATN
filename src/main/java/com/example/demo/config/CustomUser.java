package com.example.demo.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CustomUser implements UserDetails {

    private Object user;
    private String role;

    public CustomUser(Object user, String role) {
        if (user instanceof NhanVien) {
            this.user = (NhanVien) user;
        } else if (user instanceof KhachHang) {
            this.user = (KhachHang) user;
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }

        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        if (user instanceof NhanVien) {
            return ((NhanVien) user).getMatKhau();
        } else if (user instanceof KhachHang) {
            return ((KhachHang) user).getMatKhau();
        }
        return null; // Xử lý thêm nếu cần
    }

    @Override
    public String getUsername() {
        if (user instanceof NhanVien) {
            return ((NhanVien) user).getEmail();
        } else if (user instanceof KhachHang) {
            return ((KhachHang) user).getEmail();
        }
        return null; // Xử lý thêm nếu cần
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (user instanceof NhanVien) {
            return ((NhanVien) user).getTrangThai();
        } else if (user instanceof KhachHang) {
            return ((KhachHang) user).getTrangThai();
        }
        return false; // Xử lý thêm nếu cần
    }
}