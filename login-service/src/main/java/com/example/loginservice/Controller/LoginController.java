package com.example.loginservice.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginservice.DTO.ApiResponse;
import com.example.loginservice.JWT.JwtTokenProvider;
import com.example.loginservice.Service.AuthService;
import com.example.loginservice.Service.TaiKhoanService;

@RestController
@RequestMapping("/api/user/")
public class LoginController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping("login")
    public Map<String, String> testLogin(@RequestBody Map<String, String> user) {
        String result = authService.login(user.get("username"), user.get("password"));
        String role = taiKhoanService.getRoleUser(user.get("username"));
        Map<String, String> result1 = new HashMap<>();
        result1.put("token", result);
        result1.put("user", user.get("username"));
        result1.put("role", role);
        return result1;
    }

    @PostMapping("valid")
    public Map<String, Boolean> valid(@RequestBody Map<String, String> token) {
        System.out.println("check valid token");
        Boolean isValid = jwtTokenProvider.validateToken(token.get("token"));
        System.out.println(isValid);
        System.out.println("_-_-_-_-_");
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", isValid);
        return map;
    }

    @GetMapping("check")
    public String test1() {
        return "check token";
    }

    @PostMapping("doi-mat-khau")
    public String test2() {
        return "check token";
    }

    @GetMapping("quen-mat-khau")
    public ApiResponse quenMatKhau(@RequestParam("username") String username,
            @RequestParam("email") String email) {
        return taiKhoanService.quenMatKhau(email, username);
    }

    @PostMapping("thay-doi-mat-khau")
    public ApiResponse thayDoiMatKhau(@RequestBody Map<String, String> userInfo) {
        return taiKhoanService.thayDoiMatKhau(userInfo.get("username"), userInfo.get("password"),
                userInfo.get("newpassword"));
    }
}
