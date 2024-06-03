package com.example.thongtinservice.Controller;

import com.example.thongtinservice.DTO.SinhVienDTO;
import com.example.thongtinservice.Model.SinhVien;
import com.example.thongtinservice.Service.SinhVienService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/thong-tin/sinh-vien")
public class QuanTriSinhVienController {
    @Autowired
    SinhVienService sinhVienService;

    @GetMapping("/danh-sach-sv-lop")
    public ResponseEntity<List<SinhVienDTO>> danhSachSVLop(@RequestParam("ma-lop") String malop) {
        List<SinhVien> danhSach = sinhVienService.danhSachSVMaLop(malop);
        List<SinhVienDTO> DSSV = new ArrayList<>();

        for (SinhVien sv : danhSach)
        {

            DSSV.add(new SinhVienDTO(sv.getMasv(), sv.getHo(), sv.getTen(),sv.getPhai(),sv.getDiachi(),sv.getNgaysinh(),sv.getMalop().getMalop(),sv.getDanghihoc(),sv.getSdt(),sv.getHinhanh(),sv.getEmail()));
        }
        return new ResponseEntity<>(DSSV, HttpStatus.OK);
    }

    @RequestMapping(value = "/them-sinh-vien-moi", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> themSinhVien(@RequestParam("sv") String svString,
                                                            @RequestParam("img") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        SinhVienDTO sinhvien;
        try {
            sinhvien = objectMapper.readValue(svString, SinhVienDTO.class);
        } catch (Exception e) {
            result.put("status", "Invalid JSON data");
            return ResponseEntity.badRequest().body(result);
        }
        System.out.println(sinhvien.toString());
        String password = "123456";

        LocalDateTime currentDateTime = LocalDateTime.now();
        String fileName = sinhvien.getHinhanh().trim() + "_" +
                currentDateTime.getHour() + "h" +
                currentDateTime.getMinute() + "m" +
                currentDateTime.getSecond() + "s" + ".jpg";
        sinhvien.setHinhanh(fileName);
        Path root = Paths.get("thongtin-service/src/main/resources/img");
        try {
            Files.copy(file.getInputStream(), root.resolve(fileName));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        if (sinhVienService.themSinhVienMoi(sinhvien, password) == 0) {
            result.put("status", "Insert Fail!");
            return ResponseEntity.badRequest().body(result);
        } else {
            result.put("status", "OK");
            result.put("filename", fileName);
            return ResponseEntity.ok().body(result);
        }
    }

    @RequestMapping(value = "/update-sv", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateSV(@RequestParam("sv") String svString,
                                                            @RequestParam("img") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        SinhVienDTO sinhvien;
        try {
            sinhvien = objectMapper.readValue(svString, SinhVienDTO.class);
        } catch (Exception e) {
            result.put("status", "Invalid JSON data");
            return ResponseEntity.badRequest().body(result);
        }


        LocalDateTime currentDateTime = LocalDateTime.now();
        String fileName = sinhvien.getHinhanh().trim() + "_" +
                currentDateTime.getHour() + "h" +
                currentDateTime.getMinute() + "m" +
                currentDateTime.getSecond() + "s" + ".jpg";
        sinhvien.setHinhanh(fileName);
        Path root = Paths.get("thongtin-service/src/main/resources/img");
        try {
            Files.copy(file.getInputStream(), root.resolve(fileName));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        if (sinhVienService.updateSinhVien(sinhvien) == 0) {
            result.put("status", "Insert Fail!");
            return ResponseEntity.badRequest().body(result);
        } else {
            result.put("status", "OK");
            result.put("filename", fileName);
            return ResponseEntity.ok().body(result);
        }
    }

    @RequestMapping(value = "/xoa-sinh-vien", method = RequestMethod.GET)
    public ResponseEntity<?> xoaSinhVien(@RequestParam("ma-sv") String maSV) {
        if (sinhVienService.xoaSinhVien(maSV) == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Xóa sinh viên thất bại: Không tìm thấy sinh viên với mã này.");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Xóa sinh viên thành công");
        }
    }

//    @RequestMapping(value = "/update-sinh-vien", method = RequestMethod.POST)
//    public ResponseEntity<?> updateSinhVien(@Validated @RequestBody SinhVienDTO sinhvien){
//        if(sinhVienService.updateSinhVien(sinhvien) == 0){
//            return ResponseEntity.badRequest().build();
//        } else {
//            return ResponseEntity.ok().build();
//        }
//    }
    @GetMapping("/loc-ma-lop")
    public ResponseEntity<List<String>> locMaLop() {
        try {
            List<String> maLopList = sinhVienService.locMaLop();
            return new ResponseEntity<>(maLopList, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/da-nghi-hoc", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>  updateDaNghiHoc(@Validated  @RequestParam("masv") String masv)
    {
        if(sinhVienService.updateDaNghiHoc(masv) == 0){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }


    }

    @GetMapping(value = "get-img")
    public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("name") String name) {
        MediaType contentType = MediaType.IMAGE_JPEG;
        InputStream in = getClass().getResourceAsStream("/img/" + name);
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(in));
    }




}
