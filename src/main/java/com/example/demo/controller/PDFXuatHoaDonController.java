package com.example.demo.controller;

import com.example.demo.repository.HoaDonRepository;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/pdf")
public class PDFXuatHoaDonController {
////
//    private final ServletContext servletContext;
//    private final TemplateEngine templateEngine;
//
//    public PDFXuatHoaDonController(ServletContext servletContext, TemplateEngine templateEngine) {
//        this.servletContext = servletContext;
//        this.templateEngine = templateEngine;
//    }

    @Autowired
    private HoaDonRepository repository;


    @RequestMapping(path = "/xem-hoa-don/{idHoaDon}")
    public String getXemHoaDon(Model model, @PathVariable(name = "idHoaDon") Integer idHoaDon) {
        List<Object[]> listHoaDon = repository.findXuatHoaDon(idHoaDon);
        model.addAttribute("listHoaDon", listHoaDon);
        return "Admin/HoaDon/xem-hoa-don";
    }

//    @RequestMapping(path = "/down/{idHoaDon}")
//    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = "idHoaDon") Integer idHoaDon) throws IOException {
//
//        /* Do Business Logic*/
//
//        List<Object[]> listHoaDon = repository.findXuatHoaDon(idHoaDon);
//
//        /* Create HTML using Thymeleaf template Engine */
//
//        WebContext context = new WebContext(request, response, servletContext);
//        context.setVariable("orderEntry", listHoaDon);
//        String orderHtml = templateEngine.process("order", context);
//
//        /* Setup Source and target I/O streams */
////
//        ByteArrayOutputStream target = new ByteArrayOutputStream();
//        ConverterProperties converterProperties = new ConverterProperties();
//        converterProperties.setBaseUri("http://localhost:8080");
////        /* Call convert method */
//        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);
////
//        /* extract output as bytes */
//        byte[] bytes = target.toByteArray();
//
//
//        /* Send the response as downloadable PDF */
////
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(bytes);
//
//    }

}
