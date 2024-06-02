package com.example.loptinchiservice.Service;

import com.example.loptinchiservice.DTO.LopTinChiDTO;
import com.example.loptinchiservice.Model.GiangVien;
import com.example.loptinchiservice.Model.LopTinChi;
import com.example.loptinchiservice.Repository.LopTinChiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class LopTinChiService {
    @Autowired
    LopTinChiRepository lopTinChiRepository;

    public List<GiangVien> locGVKhoa(String makhoa){

        return  lopTinChiRepository.locGiangVien(makhoa);
    }
    public List<String> locMaKhoa()
    {
        return lopTinChiRepository.locMaKhoa();
    }
    public List<String> locNienKhoa()
    {
        return lopTinChiRepository.locNienKhoa();
    }
    public LopTinChiDTO mapLTCDTO (Map<String , Object> ltc)
    {
        System.out.println(ltc.get("HOCKI"));
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
    public List<LopTinChiDTO> locLTC(String makhoa,String nienkhoa, int hocki){

        List<Map<String,Object>> data = lopTinChiRepository.locLTC(makhoa,nienkhoa,hocki);
        List<LopTinChiDTO> ltc = new ArrayList<>();
        for (int i = 0 ; i< data.size();i++)
        {
            LopTinChiDTO temp = mapLTCDTO(data.get(i));
            ltc.add(temp);
        }
        return  ltc;
    }
}
