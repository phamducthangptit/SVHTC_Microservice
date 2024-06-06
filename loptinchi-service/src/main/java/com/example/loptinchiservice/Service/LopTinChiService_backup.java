package com.example.loptinchiservice.Service;

import com.example.loptinchiservice.DTO.ApiResponse;
import com.example.loptinchiservice.DTO.GiangVienDTO;
import com.example.loptinchiservice.DTO.LTCDTO;
import com.example.loptinchiservice.DTO.LopTinChiDTO;
import com.example.loptinchiservice.DTO.MonHocDTO;
import com.example.loptinchiservice.Model.GiangVien;
import com.example.loptinchiservice.Model.LopTinChi;
import com.example.loptinchiservice.Model.MonHoc;
import com.example.loptinchiservice.Repository.LopTinChiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;

import com.example.loptinchiservice.Repository.LopTinChiRepository;
import com.example.loptinchiservice.ResponseDTO.LopTinChiResponse;
import com.example.loptinchiservice.ResponseDTO.ResponseMucPhi;
import com.example.loptinchiservice.ResponseDTO.SinhVienHocPhi;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LopTinChiService {
    @Autowired
    LopTinChiRepository lopTinChiRepository;

    @Autowired
    private WebClient.Builder webClient;

    public GiangVienDTO mapGVDTO(Map<String, Object> giangvien) {
        GiangVienDTO giangVienDTO = new GiangVienDTO();
        giangVienDTO.setMagv((String) giangvien.get("MAGV"));
        giangVienDTO.setHo((String) giangvien.get("HO"));
        giangVienDTO.setTen((String) giangvien.get("TEN"));
        return giangVienDTO;
    }

    public List<GiangVienDTO> locGVKhoa(String makhoa) {
        List<GiangVienDTO> gvlist = new ArrayList<>();
        List<Map<String, Object>> data = lopTinChiRepository.locGiangVien(makhoa);
        for (int i = 0; i < data.size(); i++) {
            GiangVienDTO temp = mapGVDTO(data.get(i));
            gvlist.add(temp);
        }
        return gvlist;
    }

    public List<String> danhSachLopKhoa(String makhoa) {
        return lopTinChiRepository.danhSachLopKhoa(makhoa);
    }

    public List<String> locMaKhoa() {
        return lopTinChiRepository.locMaKhoa();
    }

    public List<String> locNienKhoa() {
        return lopTinChiRepository.locNienKhoa();
    }

    public MonHocDTO mapMonHocDTO(Map<String, Object> monhoc) {
        MonHocDTO monHocDTO = new MonHocDTO();
        monHocDTO.setMamh((String) monhoc.get("MAMH"));
        monHocDTO.setTenmh((String) monhoc.get("TENMH"));
        return monHocDTO;
    }

    public List<MonHocDTO> locMonHoc() {
        List<Map<String, Object>> data = lopTinChiRepository.locMonHoc();
        List<MonHocDTO> monHocList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            MonHocDTO temp = mapMonHocDTO(data.get(i));
            monHocList.add(temp);
        }
        return monHocList;
    }

    public LopTinChiDTO mapLTCDTO(Map<String, Object> ltc) {
        LopTinChiDTO lopTinChiDTO = new LopTinChiDTO();
        lopTinChiDTO.setMaltc((Integer) ltc.get("MALTC"));
        lopTinChiDTO.setMakhoa((String) ltc.get("MAKHOA"));
        lopTinChiDTO.setNienkhoa((String) ltc.get("NIENKHOA"));
        lopTinChiDTO.setHocki((Integer) ltc.get("HOCKI"));
        lopTinChiDTO.setNhom((Integer) ltc.get("NHOM"));
        lopTinChiDTO.setSosvtt((Integer) ltc.get("SOSVTOITHIEU"));
        lopTinChiDTO.setSosvtd((Integer) ltc.get("SOSVTOIDA"));
        lopTinChiDTO.setMalop((String) ltc.get("MALOP"));
        lopTinChiDTO.setDonglop((Boolean) ltc.get("DONGLOP"));
        lopTinChiDTO.setMamh((String) ltc.get("MAMH"));
        lopTinChiDTO.setTenmh((String) ltc.get("TENMH"));
        lopTinChiDTO.setMagv((String) ltc.get("MAGV"));
        lopTinChiDTO.setTengiangvien((String) ltc.get("TENGIANGVIEN"));
        return lopTinChiDTO;
    }

    public List<LopTinChiDTO> locLTC(String makhoa, String nienkhoa, int hocki) {

        List<Map<String, Object>> data = lopTinChiRepository.locLTC(makhoa, nienkhoa, hocki);
        List<LopTinChiDTO> ltc = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            LopTinChiDTO temp = mapLTCDTO(data.get(i));
            ltc.add(temp);
        }
        return ltc;
    }

    public int themLTC(String mamh, String malop, String magv, String nienKhoa, int nhom, int sosvtt, int sosvtd,
            int hocKi, String maKhoa) {

        try {
            lopTinChiRepository.addLTC(mamh, malop, magv, nienKhoa, nhom, sosvtt, sosvtd, hocKi, maKhoa);
            return 1;
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
    }

    public int updateLTC(int maltc, String magv, int nhom, int sosvtt, int sosvtd, String malop) {

        try {
            lopTinChiRepository.updateLTC(maltc, magv, nhom, sosvtt, sosvtd, malop);
            return 1;
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
    }

    public int xoaLTC(int maltc) {
        try {
            lopTinChiRepository.xoaLTC(maltc);
            return 1;
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
    }

    public List<LopTinChiResponse> danhSachLtcTheoMaGv(String maGV) {
        List<Object[]> resultCallSp = lopTinChiRepository.danhSachLtcTheoMaGV(maGV);
        if (!resultCallSp.isEmpty()) {
            List<LopTinChiResponse> result = new ArrayList<>();
            for (Object[] re : resultCallSp) {
                LopTinChiResponse lopTinChiResponse = new LopTinChiResponse();
                lopTinChiResponse.setMaLTC((int) re[0]);
                lopTinChiResponse.setNienKhoa((String) re[1]);
                lopTinChiResponse.setHocKi((int) re[2]);
                lopTinChiResponse.setMaMH((String) re[3]);
                lopTinChiResponse.setTenMH((String) re[4]);
                result.add(lopTinChiResponse);
            }
            return result;
        }
        return null;
    }

    private Map<String, Object> layHocKiHienTai() {
        LocalDate time = LocalDate.now();
        int year = time.getYear();
        Month month = time.getMonth();

        int hocKy;
        String nienKhoa;

        if ((month.getValue() >= Month.JUNE.getValue()) && (month.getValue() <= Month.DECEMBER.getValue())) {
            // Học kỳ 1
            hocKy = 1;
            nienKhoa = year + "-" + (year + 1);
        } else {
            hocKy = 2;
            nienKhoa = (year - 1) + "-" + year;
        }

        Map<String, Object> thongTin = new HashMap<>();
        thongTin.put("nienKhoa", nienKhoa);
        thongTin.put("hocKy", hocKy);
        return thongTin;
    }

    private LTCDTO mapDBToLTCDTO(Map<String, Object> data) {
        LTCDTO result = new LTCDTO();
        result.setMaLTC((Integer) data.get("MALTC"));
        result.setMaMH((String) data.get("MAMH"));
        result.setTenMH((String) data.get("TENMH"));
        result.setNhom((Integer) data.get("NHOM"));
        result.setSoTC((Integer) data.get("SOTC"));
        result.setMaLop((String) data.get("MALOP"));
        result.setSoSVToiDa((Integer) data.get("SOSVTOIDA"));
        result.setMaGV((String) data.get("MAGV"));
        return result;
    }

    public List<Map<String, Object>> getDanhSachLTCDeDangKi(String namhoc, int hocki, String maLop, String masv) {
        return lopTinChiRepository.getDanhSachLTCDeDangKi(namhoc, hocki, maLop, masv);
    }

    public List<Map<String, Object>> getDanhSachLTCDaDangKi(String namhoc, int hocki, String masv) {
        return lopTinChiRepository.getDanhSachLTCSVDaDangKi(namhoc, hocki, masv);
    }

    public int getSoLuongDaDKTheoMaLTC(int maltc) {
        return lopTinChiRepository.getSoLuongDaDKTheoMaLTC(maltc);
    }

    public void insertSVDangKi(int maltc, String masv) {
        lopTinChiRepository.insertSVDangKi(maltc, masv);
    }

    public ApiResponse getDSLTCDeDK(String maLop, String masv) {
        Map<String, Object> thongTin = layHocKiHienTai();
        List<Map<String, Object>> data = getDanhSachLTCDeDangKi((String) thongTin.get("nienKhoa"),
                (Integer) thongTin.get("hocKy"), maLop, masv);
        List<Map<String, Object>> data1 = getDanhSachLTCDaDangKi((String) thongTin.get("nienKhoa"),
                (Integer) thongTin.get("hocKy"), masv);
        if (data == null) {
            return new ApiResponse<List<LTCDTO>>(201, "Lấy danh sách lớp tín chỉ thất bại!", null);
        }
        List<LTCDTO> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            LTCDTO ltc = mapDBToLTCDTO(data.get(i));
            int svdaDK = getSoLuongDaDKTheoMaLTC(ltc.getMaLTC());
            ltc.setConLai(ltc.getSoSVToiDa() - svdaDK);
            for (int j = 0; j < data1.size(); j++) {
                if ((Integer) data1.get(j).get("MALTC") == ltc.getMaLTC()) {
                    ltc.setActive(true);
                    break;
                }
            }
            result.add(ltc);
        }
        return new ApiResponse<List<LTCDTO>>(200, "Lấy danh sách lớp tín chỉ thành công!", result);
    }

    public ApiResponse getDSLTCDaDK(String masv) {
        Map<String, Object> thongTin = layHocKiHienTai();
        List<Map<String, Object>> data = getDanhSachLTCDaDangKi((String) thongTin.get("nienKhoa"),
                (Integer) thongTin.get("hocKy"), masv);
        if (data == null) {
            return new ApiResponse<List<LTCDTO>>(201, "Lấy danh sách lớp tín chỉ thất bại!", null);
        }
        List<LTCDTO> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            LTCDTO ltc = mapDBToLTCDTO(data.get(i));
            int svConLai = getSoLuongDaDKTheoMaLTC(ltc.getMaLTC());
            ltc.setConLai(ltc.getSoSVToiDa() - svConLai);
            result.add(ltc);
        }
        return new ApiResponse<List<LTCDTO>>(200, "Lấy danh sách lớp tín chỉ thành công!", result);
    }

    @CircuitBreaker(name = "insertHocPhi", fallbackMethod = "fallbackInsertHocPhi")
    public ApiResponse dangKyLTC(int maltc, String masv, int soSVtoiDa) {
        int svdaDK = getSoLuongDaDKTheoMaLTC(maltc);
        if (svdaDK == soSVtoiDa) {
            return new ApiResponse<>(202, "Lớp không còn vị trí trống để đăng kí!", null);
        }
        Map<String, Object> mh = lopTinChiRepository.getMaMHFromLTC(maltc);
        Map<String, Object> sv = lopTinChiRepository.getInfoSVDK(masv);
        ResponseMucPhi data1 = new ResponseMucPhi(masv, (String) mh.get("MAMH"));
        Mono<ApiResponse> response = webClient.build().post()
                .uri("http://thong-tin-service/api/thong-tin/muc-phi/lay-muc-phi")
                .body(Mono.just(data1), ResponseMucPhi.class)
                .retrieve()
                .bodyToMono(ApiResponse.class);
        ApiResponse result = response.block();
        int mucPhi = (Integer) result.getData();
        System.out.println("Check data muc phi: " + mucPhi);
        SinhVienHocPhi data2 = new SinhVienHocPhi();
        data2.setMaSV(masv);
        data2.setHoSV((String) sv.get("HO"));
        data2.setTenSV((String) sv.get("TEN"));
        Map<String, Object> thongTin = layHocKiHienTai();
        data2.setNienKhoa((String) thongTin.get("nienKhoa"));
        data2.setHocKi((Integer) thongTin.get("hocKy"));
        data2.setSoTien(mucPhi * ((Integer) mh.get("SOTC")));
        System.out.println((Integer) mh.get("SOTC"));
        System.out.println(data2.getSoTien());
        Mono<ApiResponse> response1 = webClient.build().post()
                .uri("http://thanh-toan-service/api/thanh-toan/hoc-phi/them-hoc-phi")
                .body(Mono.just(data2), SinhVienHocPhi.class)
                .retrieve()
                .bodyToMono(ApiResponse.class);
        ApiResponse result1 = response1.block();
        System.out.println("Check lưu hoc phí" + result1.getStatus());
        insertSVDangKi(maltc, masv);
        return new ApiResponse<>(200, "Đăng kí thành công!", null);
    }

    @Transactional
    public ApiResponse huyDangKyLTC(int maltc, String masv) {
        Map<String, Object> mh = lopTinChiRepository.getMaMHFromLTC(maltc);
        Map<String, Object> sv = lopTinChiRepository.getInfoSVDK(masv);
        ResponseMucPhi data1 = new ResponseMucPhi(masv, (String) mh.get("MAMH"));
        Mono<ApiResponse> response = webClient.build().post()
                .uri("http://thong-tin-service/api/thong-tin/muc-phi/lay-muc-phi")
                .body(Mono.just(data1), ResponseMucPhi.class)
                .retrieve()
                .bodyToMono(ApiResponse.class);
        ApiResponse result = response.block();
        int mucPhi = (Integer) result.getData();
        System.out.println("Check data muc phi: " + mucPhi);
        SinhVienHocPhi data2 = new SinhVienHocPhi();
        data2.setMaSV(masv);
        data2.setHoSV((String) sv.get("HO"));
        data2.setTenSV((String) sv.get("TEN"));
        Map<String, Object> thongTin = layHocKiHienTai();
        data2.setNienKhoa((String) thongTin.get("nienKhoa"));
        data2.setHocKi((Integer) thongTin.get("hocKy"));
        data2.setSoTien(-(mucPhi * ((Integer) mh.get("SOTC"))));
        System.out.println((Integer) mh.get("SOTC"));
        System.out.println(data2.getSoTien());
        Mono<ApiResponse> response1 = webClient.build().post()
                .uri("http://thanh-toan-service/api/thanh-toan/hoc-phi/them-hoc-phi")
                .body(Mono.just(data2), SinhVienHocPhi.class)
                .retrieve()
                .bodyToMono(ApiResponse.class);
        ApiResponse result1 = response1.block();
        System.out.println("Check lưu hoc phí" + result1.getStatus());
        lopTinChiRepository.deleteSVDangKi(maltc, masv);

        return new ApiResponse<>(200, "Huỷ đăng kí thành công!", null);
    }

    public ApiResponse layThongTinLop123() {
        List<Map<String, Object>> data = lopTinChiRepository.getThongTinLop123();
        return new ApiResponse<List<Map<String, Object>>>(200, "Lấy thông tin lớp thành công", data);
    }

    public ApiResponse fallbackInsertHocPhi(int maltc, String masv, int soSVtoiDa, Throwable t) {
        return new ApiResponse<String>(300, "Lưu môn học thất bại!", t.getMessage());
    }
}
