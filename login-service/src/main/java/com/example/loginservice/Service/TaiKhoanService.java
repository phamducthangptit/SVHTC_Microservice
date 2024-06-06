package com.example.loginservice.Service;

import java.util.List;
import java.util.Map;

import org.bouncycastle.jcajce.provider.keystore.BC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginservice.DTO.ApiResponse;
import com.example.loginservice.Repository.TaiKhoanRepository;

@Service
public class TaiKhoanService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private EmailService emailService;

    public Map<String, Object> loadUser(String username) {
        return taiKhoanRepository.loadUser(username);
    }

    public void updatePassword(String username, String password) {
        PasswordEncoder encode = new BCryptPasswordEncoder();
        String password1 = encode.encode(password);
        taiKhoanRepository.updatePassword(username, password1);
    }

    public String getEmail(Map<String, Object> user) {

        if (((String) user.get("TENQUYEN")).equals("SINHVIEN")) {
            return taiKhoanRepository.getEmailSV((String) user.get("USERNAME"));
        } else {
            return taiKhoanRepository.getEmailGV((String) user.get("USERNAME"));
        }
    }

    public ApiResponse thayDoiMatKhau(String username, String password, String newPassword) {
        Map<String, Object> user = taiKhoanRepository.loadUser(username);
        PasswordEncoder encode = new BCryptPasswordEncoder();
        if (!(encode.matches(password, (String) user.get("PASSWORD")))) {
            return new ApiResponse<Object>(203, "Mật khẩu cũ không chính xác không chính xác!", null);
        }
        updatePassword(username, newPassword);
        return new ApiResponse<Object>(200, "Cập nhật mật khẩu thành công!", null);
    }

    public ApiResponse quenMatKhau(String email, String username) {
        Map<String, Object> user = taiKhoanRepository.loadUser(username);
        if (user.get("USERNAME") == null) {
            return new ApiResponse<Object>(204, "Tài khoản không tồn tại!", null);
        }
        String email1 = getEmail(user);
        if (email1.equals(email)) {
            String resetPassword = emailService.generateCode();
            updatePassword(username, resetPassword);
            emailService.sendEmail(email1, "Mật khẩu mới: " + resetPassword);
            return new ApiResponse<Object>(200, "Mật khẩu mới đã được gửi đến email của bạn!", null);
        }
        return new ApiResponse<Object>(203, "Email không chính xác!", null);
    }
}
