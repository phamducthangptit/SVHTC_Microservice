package com.example.thongtinservice.Service;

import com.example.thongtinservice.DTO.GiangVienDTO;
import com.example.thongtinservice.Repository.QuanTriGiangVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QTGiangVienService {
    @Autowired
    QuanTriGiangVienRepository giangVienRepository;
    public int themGiangVienMoi(GiangVienDTO giangVien, String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String newPass = encoder.encode(password);
        try {
            giangVienRepository.themGiangVienMoi(
                    giangVien.getMagv(),
                    giangVien.getHo(),
                    giangVien.getTen(),
                    giangVien.getHocham(),
                    giangVien.getHocvi(),
                    giangVien.getChuyenmon(),
                    giangVien.getSdt(),
                    giangVien.getHinhanh(),
                    giangVien.getMagv().trim()+"@ptithcm.edu.vn",
                    giangVien.getMakhoa(),
                    newPass            );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public int xoaGiangVien(String magv){
        try {
            giangVienRepository.xoaGiangVien(magv);

        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }

    public int updateGiangVien(GiangVienDTO giangVien) {

        try {
            giangVienRepository.updateGiangVien(
                    giangVien.getMagv(),
                    giangVien.getHo(),
                    giangVien.getTen(),
                    giangVien.getHocham(),
                    giangVien.getHocvi(),
                    giangVien.getChuyenmon(),
                    giangVien.getSdt(),
                    giangVien.getHinhanh(),
                    giangVien.getEmail(),
                    giangVien.getMakhoa()
            );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public GiangVienDTO mapGVDTO (Map<String , Object> gv)
    {
        GiangVienDTO gvDTO = new GiangVienDTO();
        gvDTO.setMagv((String) gv.get("MAGV"));
        gvDTO.setHo((String) gv.get("HO"));
        gvDTO.setTen((String) gv.get("TEN"));
        gvDTO.setHocham((String) gv.get("HOCHAM"));
        gvDTO.setHocvi((String) gv.get("HOCVI"));
        gvDTO.setChuyenmon((String) gv.get("CHUYENMON"));
        gvDTO.setSdt((String) gv.get("SDT"));
        gvDTO.setHinhanh((String) gv.get("HINHANH"));
        gvDTO.setEmail((String) gv.get("EMAIL"));
        gvDTO.setMakhoa((String) gv.get("MAKHOA"));

        return gvDTO;
    }
    public List<GiangVienDTO> danhSachGVMaKhoa(String makhoa){

        List<Map<String,Object>> data = giangVienRepository.danhSachGiangVien(makhoa);
        List<GiangVienDTO> gvList = new ArrayList<>();
        for (int i = 0 ; i< data.size();i++)
        {
            GiangVienDTO temp = mapGVDTO(data.get(i));
            gvList.add(temp);
        }
        return  gvList;
    }
    public List<String> locMaKhoa()
    {
        return giangVienRepository.locMaKhoa();
    }

}
