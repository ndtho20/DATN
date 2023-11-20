package com.example.demo.service;

import com.example.demo.entity.KhachHang;
import com.example.demo.repository.KhachHangRepository;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;


    public KhachHang saveUser(KhachHang user,String url) {

        String password = passwordEncoder.encode(user.getMatKhau());
        user.setMatKhau(password);
        user.setTrangThai(false);
        user.setNgayTao(new Date());
        user.setVerificationCode(UUID.randomUUID().toString());
        KhachHang newuser = khachHangRepository.save(user);
        if (newuser != null) {
            sendEmail(newuser, url);
        }
        return newuser;
    }

    public void sendEmail(KhachHang user, String url) {
        String from = "ndtho2003@gmail.com";
        String to = user.getEmail();
        String subject = "Account Verfication";
        String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + "DiptsZyx";

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from, "DiptsZyx");
            helper.setTo(to);
            helper.setSubject(subject);

            content = content.replace("[[name]]", user.getTen());
            String siteUrl = url + "/verify?code=" + user.getVerificationCode();
            System.out.println(siteUrl);
            content = content.replace("[[URL]]", siteUrl);
            helper.setText(content, true);
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyAccount(String verificationCode) {
        KhachHang user = khachHangRepository.findKhachHangByVerificationCode(verificationCode);
        if (user == null) {
            return false;
        } else {
            user.setTrangThai(true);
            user.setVerificationCode(null);
            khachHangRepository.save(user);
            return true;
        }
    }

    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }
}
