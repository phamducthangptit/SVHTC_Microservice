package com.example.loptinchiservice.Service;

import com.example.loptinchiservice.DTO.ApiResponse;
import com.example.loptinchiservice.DTO.LTCDTO;
import com.example.loptinchiservice.Model.Lop;
import com.example.loptinchiservice.Repository.LopTinChiRepository;
import com.example.loptinchiservice.Responsedto.LopTinChiResponse;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LopTinChiService {
    @Autowired
    private LopTinChiRepository lopTinChiRepository;

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

    @Transactional
    public ApiResponse dangKyLTC(int maltc, String masv, int soSVtoiDa) {
        int svdaDK = getSoLuongDaDKTheoMaLTC(maltc);
        if (svdaDK == soSVtoiDa) {
            return new ApiResponse<>(202, "Lớp không còn vị trí trống để đăng kí!", null);
        }
        insertSVDangKi(maltc, masv);
        return new ApiResponse<>(200, "Đăng kí thành công!", null);
    }

    @Transactional
    public ApiResponse huyDangKyLTC(int maltc, String masv) {
        lopTinChiRepository.deleteSVDangKi(maltc, masv);
        return new ApiResponse<>(200, "Huỷ đăng kí thành công!", null);
    }

    public ApiResponse layThongTinLop123() {
        List<Map<String, Object>> data = lopTinChiRepository.getThongTinLop123();
        return new ApiResponse<List<Map<String, Object>>>(200, "Lấy thông tin lớp thành công", data);
    }
}
