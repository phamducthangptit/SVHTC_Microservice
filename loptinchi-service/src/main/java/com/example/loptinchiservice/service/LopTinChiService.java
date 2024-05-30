package com.example.loptinchiservice.service;

import com.example.loptinchiservice.repository.LopTinChiRepository;
import com.example.loptinchiservice.responsedto.LopTinChiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LopTinChiService {
    @Autowired
    private LopTinChiRepository repository;
    public List<LopTinChiResponse> danhSachLtcTheoMaGv(String maGV){
        List<Object[]> resultCallSp = repository.danhSachLtcTheoMaGV(maGV);
        if(!resultCallSp.isEmpty()){
            List<LopTinChiResponse> result = new ArrayList<>();
            for(Object[] re : resultCallSp){
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
}
