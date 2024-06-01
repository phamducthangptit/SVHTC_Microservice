package com.example.thongtinservice.Service;

import com.example.thongtinservice.DTO.SinhVienDTO;
import com.example.thongtinservice.Model.SinhVien;
import com.example.thongtinservice.Repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SinhVienService {
    @Autowired
    SinhVienRepository sinhVienRepository;


    // Lay danh sach sinh vien
    public List<SinhVien> findAll() {
        return sinhVienRepository.findAll();
    }


    public Map<String, Object> thongtinSV(String masv){
        return sinhVienRepository.thongtinSV(masv);
    }

    // Lay thong tin sinh vien
    public Optional<SinhVien> findById(String id) {
        return sinhVienRepository.findById(id);
    }

    // Luu va sua thong tin sinh vien
    public SinhVien save(SinhVien sinhVien) {
        return sinhVienRepository.save(sinhVien);
    }

    // Xoa sinh vien
    public void deleteById(String id) {
        sinhVienRepository.deleteById(id);
    }

    // Kiem tra ton tai
    public boolean existsById(String id) {
        return sinhVienRepository.existsById(id);
    }
    public SinhVien sinhVienTheoMa(String maSV){
        return sinhVienRepository.findBymasv(maSV);
    }


    public int themSinhVienMoi(SinhVienDTO sinhVien, String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String newPass = encoder.encode(password);
        try {
            sinhVienRepository.themSinhVienMoi(
                    sinhVien.getMasv(),
                    sinhVien.getHo(),
                    sinhVien.getTen(),
                    sinhVien.getNgaysinh(),
                    sinhVien.getPhai(),
                    sinhVien.getSdt(),
                    sinhVien.getDiachi(),
                    sinhVien.getMalop(),
                    false,
                    sinhVien.getHinhanh(),
                    sinhVien.getMasv().trim()+"@student.ptithcm.edu.vn",
                    newPass
            );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }

    public int xoaSinhVien(String masv){
        try {
            sinhVienRepository.xoaSinhVien(masv);

        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }

    public int updateSinhVien(SinhVienDTO sinhVien) {
        System.out.println(sinhVien.toString());
        try {
            sinhVienRepository.updateSinhVien(
                    sinhVien.getMasv(),
                    sinhVien.getHo(),
                    sinhVien.getTen(),
                    sinhVien.getNgaysinh(),
                    sinhVien.getPhai(),
                    sinhVien.getSdt(),
                    sinhVien.getDiachi(),
                    sinhVien.getMalop(),
                    sinhVien.getDanghihoc(),
                    sinhVien.getHinhanh(),
                    sinhVien.getEmail()
            );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public List<SinhVien> danhSachSVMaLop(String malop){

        return  sinhVienRepository.danhSachSinhVien(malop);
    }
    public List<String> locMaLop()
    {
        return sinhVienRepository.locMaLop();
    }
    public int updateDaNghiHoc(String masv) {

        try {
            sinhVienRepository.updateDaNghiHoc(masv);
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }

    public int doiMatKhau(String username,String password) {

        try {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String newPass = encoder.encode(password);
            sinhVienRepository.doiMatKhau(username,newPass);
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public SinhVienDTO timSinhVen(String masv)
    {
        return sinhVienRepository.timSinhVien(masv);
    }
    public Map<String, Object> taiKhoanByEmail(String Email) {
        return sinhVienRepository.taiKhoanByEMail(Email);
    }
}