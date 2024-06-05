package com.example.loptinchiservice.Service;

import com.example.loptinchiservice.DTO.GiangVienDTO;
import com.example.loptinchiservice.DTO.LopTinChiDTO;
import com.example.loptinchiservice.DTO.MonHocDTO;
import com.example.loptinchiservice.Model.GiangVien;
import com.example.loptinchiservice.Model.LopTinChi;
import com.example.loptinchiservice.Repository.LopTinChiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class LopTinChiService {
    @Autowired
    LopTinChiRepository lopTinChiRepository;


    public GiangVienDTO mapGVDTO (Map<String , Object> giangvien)
    {
        GiangVienDTO giangVienDTO = new GiangVienDTO();
        giangVienDTO.setMagv((String) giangvien.get("MAGV"));
        giangVienDTO.setHo((String) giangvien.get("HO"));
        giangVienDTO.setTen((String) giangvien.get("TEN"));
        return giangVienDTO;
    }
    public List<GiangVienDTO> locGVKhoa(String makhoa){
        List<GiangVienDTO> gvlist = new ArrayList<>();
        List<Map<String,Object>> data = lopTinChiRepository.locGiangVien(makhoa);
        for(int i = 0; i < data.size();i++)
        {
            GiangVienDTO temp = mapGVDTO(data.get(i));
            gvlist.add(temp);
        }
        return  gvlist;
    }
    public List<String> danhSachLopKhoa(String makhoa)
    {
        return lopTinChiRepository.danhSachLopKhoa(makhoa);
    }
    public List<String> locMaKhoa()
    {
        return lopTinChiRepository.locMaKhoa();
    }
    public List<String> locNienKhoa()
    {
        return lopTinChiRepository.locNienKhoa();
    }
    public MonHocDTO mapMonHocDTO (Map<String , Object> monhoc)
    {
        MonHocDTO monHocDTO = new MonHocDTO();
        monHocDTO.setMamh((String) monhoc.get("MAMH"));
        monHocDTO.setTenmh((String) monhoc.get("TENMH"));
        return monHocDTO;
    }
    public List<MonHocDTO> locMonHoc()
    {
        List<Map<String,Object>> data = lopTinChiRepository.locMonHoc();
        List<MonHocDTO> monHocList = new ArrayList<>();
        for(int i = 0;i<data.size();i++)
        {
            MonHocDTO temp = mapMonHocDTO(data.get(i));
            monHocList.add(temp);
        }
        return monHocList;
    }
    public LopTinChiDTO mapLTCDTO (Map<String , Object> ltc)
    {
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
    public int themLTC(String mamh, String malop, String magv, String nienKhoa, int nhom, int sosvtt,int sosvtd, int hocKi, String maKhoa){

        try {
            lopTinChiRepository.addLTC(mamh, malop, magv, nienKhoa, nhom, sosvtt,sosvtd, hocKi, maKhoa);
            return 1;
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
    }
    public int updateLTC(int maltc, String magv, int nhom, int sosvtt,int sosvtd){

        try {
            lopTinChiRepository.updateLTC(maltc, magv, nhom, sosvtt,sosvtd);
            return 1;
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
    }
}
