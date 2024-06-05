package com.example.thongtinservice.Controller;

import com.example.thongtinservice.RequestDTO.DiemRequest;
import com.example.thongtinservice.ResponseDTO.DiemResponse;
import com.example.thongtinservice.ResponseService.LopTinChiResponse;
import com.example.thongtinservice.Service.NhapDiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thong-tin/giang-vien")
public class GiangVienController {
    @Autowired
    private NhapDiemService nhapDiemService;

    @GetMapping("/danh-sach-ltc")
    public ResponseEntity<?> danhSachLtcTheoMaGv(@RequestParam("ma-gv") String maGV) {
        List<LopTinChiResponse> result = nhapDiemService.danhSachLtcTheoMaGV(maGV);
        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/danh-sach-sinh-vien-ltc")
    public ResponseEntity<?> danhSachSinhVienLtc(@RequestParam("ma-ltc") int maLTC,
                                                 @RequestParam("nien-khoa") String nienKhoa,
                                                 @RequestParam("hoc-ki") int hocKi,
                                                 @RequestParam("ma-mh") String maMH) {
        List<DiemResponse> result = nhapDiemService.danhSachDiemSinhVienLtc(maLTC, nienKhoa, hocKi, maMH);
        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/cap-nhat-diem")
    public ResponseEntity<?> capNhatDiemSv(@RequestBody DiemRequest diemRequest){
        try{
            nhapDiemService.updateDiem(diemRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
