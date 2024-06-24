package com.example.thongtinservice.Controller;

import com.example.thongtinservice.DTO.GiangVienDTO;
import com.example.thongtinservice.Service.QTGiangVienService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/thong-tin/giang-vien")
public class QuanTriGiangVienController {
    @Autowired
    QTGiangVienService giangVienService;
    @GetMapping(value = "/get-img")
    public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("name") String name) {
        MediaType contentType = MediaType.IMAGE_JPEG;
        InputStream in = getClass().getResourceAsStream("/img/" + name);
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(in));
    }
    @GetMapping("/loc-ma-khoa")
    public ResponseEntity<List<String>> locMaKhoa() {
        try {
            List<String> maKhoaList = giangVienService.locMaKhoa();
            return new ResponseEntity<>(maKhoaList, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/loc-gv-khoa")
    public ResponseEntity<List<GiangVienDTO>> locGVKhoa(@RequestParam("ma-khoa") String makhoa) {
        return  ResponseEntity.ok(giangVienService.danhSachGVMaKhoa(makhoa));
    }
    @RequestMapping(value = "/them-giang-vien", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> themGiangVien(@RequestParam("gv") String gvString,
                                                            @RequestParam("img") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        GiangVienDTO giangvien;
        try {
            giangvien = objectMapper.readValue(gvString, GiangVienDTO.class);
        } catch (Exception e) {
            result.put("status", "Invalid JSON data");
            return ResponseEntity.badRequest().body(result);
        }
        String password = "123456";

        LocalDateTime currentDateTime = LocalDateTime.now();
        String fileName = giangvien.getHinhanh().trim() + "_" +
                currentDateTime.getHour() + "h" +
                currentDateTime.getMinute() + "m" +
                currentDateTime.getSecond() + "s" + ".jpg";
        giangvien.setHinhanh(fileName);
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL resource = classLoader.getResource("img");
        Path root = Paths.get(resource.getPath());
        try {
            Files.copy(file.getInputStream(), root.resolve(fileName));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        if (giangVienService.themGiangVienMoi(giangvien, password) == 0) {
            result.put("status", "Insert Fail!");
            return ResponseEntity.badRequest().body(result);
        } else {
            result.put("status", "OK");
            result.put("filename", fileName);
            return ResponseEntity.ok().body(result);
        }
    }

    @RequestMapping(value = "/update-gv", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateGV(@RequestParam("gv") String gvString,
                                                        @RequestParam("img") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        GiangVienDTO giangvien;
        try {
            giangvien = objectMapper.readValue(gvString, GiangVienDTO.class);
        } catch (Exception e) {
            result.put("status", "Invalid JSON data");
            return ResponseEntity.badRequest().body(result);
        }


        LocalDateTime currentDateTime = LocalDateTime.now();
        String fileName = giangvien.getHinhanh().trim() + "_" +
                currentDateTime.getHour() + "h" +
                currentDateTime.getMinute() + "m" +
                currentDateTime.getSecond() + "s" + ".jpg";
        giangvien.setHinhanh(fileName);
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL resource = classLoader.getResource("img");
        Path root = Paths.get(resource.getPath());
        System.out.println(root);
        try {
            Files.copy(file.getInputStream(), root.resolve(fileName));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        if (giangVienService.updateGiangVien(giangvien) == 0) {
            result.put("status", "Insert Fail!");
            return ResponseEntity.badRequest().body(result);
        } else {
            result.put("status", "OK");
            result.put("filename", fileName);
            return ResponseEntity.ok().body(result);
        }
    }

    @RequestMapping(value = "/xoa-giang-vien", method = RequestMethod.GET)
    public ResponseEntity<?> xoaGiangVien(@RequestParam("ma-gv") String maGV) {
        if (giangVienService.xoaGiangVien(maGV) == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Xóa giảng viên thất bại.");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Xóa Giảng viên thành công");
        }
    }
}
