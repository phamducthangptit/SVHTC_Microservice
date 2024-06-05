package com.example.loptinchiservice.Service;

import com.example.loptinchiservice.DTO.GiangVienDTO;
import com.example.loptinchiservice.Repository.GiangVienRepo;
import com.example.loptinchiservice.Repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GiangVienService {
    @Autowired
    private GiangVienRepo giangVienRepository;
    public int themGV(GiangVienDTO giangVien) {
        try {
            giangVienRepository.themGiangVienMoi(
                    giangVien.getMagv(),
                    giangVien.getHo(),
                    giangVien.getTen(),
                    giangVien.getMakhoa() );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public int xoaGV(String magv){
        try {
            giangVienRepository.xoaGiangVien(magv);

        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }

    public int updateGV(GiangVienDTO giangVien) {

        try {
            giangVienRepository.updateGiangVien(
                    giangVien.getMagv(),
                    giangVien.getHo(),
                    giangVien.getTen(),
                    giangVien.getMakhoa()
            );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
}
