package com.example.loptinchiservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lop-tin-chi/")
public class MonHocController {

    @GetMapping("check")
    public String checkService() {
        System.out.print("Check api lop tin chi");
        return "check 32124441123";
    }
}
