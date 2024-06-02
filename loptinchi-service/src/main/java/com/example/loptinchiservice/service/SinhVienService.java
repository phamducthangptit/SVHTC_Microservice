package com.example.loptinchiservice.Service;

import com.example.loptinchiservice.Repository.SinhVienRepository;
import com.example.loptinchiservice.responsedto.SinhVienLtcResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
}
