package com.example.loptinchiservice.Service;

import com.example.loptinchiservice.DTO.SinhVienDTO;
import com.example.loptinchiservice.Model.SinhVien;
import com.example.loptinchiservice.Repository.SinhVienRepository;
import com.example.loptinchiservice.ResponseDTO.SinhVienLtcResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SinhVienService {
    @Autowired
    private SinhVienRepository repository;

    public List<SinhVienLtcResponse> danhSachSinhVienLtc(int maLTC) {
        List<Object[]> resultCallSp = repository.danhSachLtcTheoMaGV(maLTC);
        if (!resultCallSp.isEmpty()) {
            List<SinhVienLtcResponse> result = new ArrayList<>();
            for (Object[] re : resultCallSp) {
                SinhVienLtcResponse sinhVienLtcResponse = new SinhVienLtcResponse();
                sinhVienLtcResponse.setMaSV((String) re[0]);
                sinhVienLtcResponse.setHo((String) re[1]);
                sinhVienLtcResponse.setTen((String) re[2]);
                result.add(sinhVienLtcResponse);
            }
            return result;
        }
        return null;
    }

    public List<String> danhSachLtcSV(String masv) {
        return repository.danhSachLtcSV(masv);
    }
    public int themSV(SinhVienDTO sv) {
        try {
            repository.themSV(
                   sv.getMasv(),
                    sv.getHo(),
                    sv.getTen(),
                    sv.getMalop()
            );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public int updateSV(SinhVienDTO sv) {
        try {
            repository.updateSV(
                    sv.getMasv(),
                    sv.getHo(),
                    sv.getTen(),
                    sv.getMalop()
            );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public int xoaSV(String masv) {

        try {
            repository.xoaSV(masv);
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
}
