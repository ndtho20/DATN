package com.example.demo.Interceptor;

import com.example.demo.service.ChiTietSanPhamService;
import com.example.demo.service.LoaiSanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Component
public class GloballInterceptor implements HandlerInterceptor {
    @Autowired
    LoaiSanPhamService loaiSanPhamService;
    ChiTietSanPhamService chiTietSanPhamService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        request.setAttribute("cates", loaiSanPhamService.getAll());
        request.setAttribute("sizes", Arrays.asList("XS", "S", "M", "L", "XL"));
        request.setAttribute("colors", Arrays.asList("MS1", "MS2", "MS3", "MS4", "MS5"));


    }
}
