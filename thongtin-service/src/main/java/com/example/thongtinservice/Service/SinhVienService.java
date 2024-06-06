package com.example.thongtinservice.Service;
import com.example.thongtinservice.DTO.SinhVienDTO;
import com.example.thongtinservice.Model.SinhVien;
import com.example.thongtinservice.Repository.SinhVienRepository;
import com.example.thongtinservice.ResponseDTO.DiemTongKetResponse;
import com.example.thongtinservice.ResponseDTO.NienKhoaHocKi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class SinhVienService {
    @Autowired
    SinhVienRepository sinhVienRepository;



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
            return 1;
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }

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
    public SinhVienDTO timSinhVen(String masv)
    {
        return sinhVienRepository.timSinhVien(masv);
    }




    public Map<String, ?> thongTinCaNhanSV(String maSV){
        return sinhVienRepository.thongTinCaNhanSinhVien(maSV);
    }

    public List<NienKhoaHocKi> getNienKhoaHocKi(String maSV){
        List<NienKhoaHocKi> listNienKhoaHocKi = new ArrayList<>();
        List<Object[]> resultCallSp =  sinhVienRepository.getNienKhoaHocKi(maSV);
        for(Object[] result : resultCallSp){
            NienKhoaHocKi nienKhoaHocKi = new NienKhoaHocKi();
            nienKhoaHocKi.setNienKhoa((String) result[0]);
            nienKhoaHocKi.setHocKi((int) result[1]);
            listNienKhoaHocKi.add(nienKhoaHocKi);
        }
        return listNienKhoaHocKi;
    }

    public List<List<DiemTongKetResponse>> xemDiem(String maSV){
        List<NienKhoaHocKi> listNienKhoaHocKi = getNienKhoaHocKi(maSV);
        if(!listNienKhoaHocKi.isEmpty()){
            List<Object[]> resultCallSp = sinhVienRepository.xemDiem(maSV);
            List<DiemTongKetResponse> listDiem = new ArrayList<>(); // danh sach tat ca diem
            List<List<DiemTongKetResponse>> listDiemLoc = new ArrayList<>();
            for(Object[] result : resultCallSp){
                DiemTongKetResponse diemTongKetResponse = getDiemResponse(result);
                listDiem.add(diemTongKetResponse);
            }
            // loc theo tung nienkhoa hocki
            for(NienKhoaHocKi nienKhoaHocKi : listNienKhoaHocKi){
                List<DiemTongKetResponse> listDiemTmp = new ArrayList<>();
                for(DiemTongKetResponse diemTongKetResponse : listDiem){
                    if(diemTongKetResponse.getNienKhoa().equals(nienKhoaHocKi.getNienKhoa()) && (diemTongKetResponse.getHocKi() == nienKhoaHocKi.getHocKi())){
                        listDiemTmp.add(diemTongKetResponse);
                    }
                }
                listDiemLoc.add(listDiemTmp);
            }
            return listDiemLoc;
        }
        return null;
    }

    private static DiemTongKetResponse getDiemResponse(Object[] result) {
        DiemTongKetResponse diemTongKetResponse = new DiemTongKetResponse();
        diemTongKetResponse.setNienKhoa((String) result[0]);
        diemTongKetResponse.setHocKi((int) result[1]);
        diemTongKetResponse.setMaMH((String) result[2]);
        diemTongKetResponse.setTenMH((String) result[3]);
        diemTongKetResponse.setSoTC((int) result[4]);
        diemTongKetResponse.setDiemCC((int) result[5]);
        diemTongKetResponse.setDiemGK((double) result[6]);
        diemTongKetResponse.setDiemCK((double) result[7]);

        // tinh diem tk
        diemTongKetResponse.setDiemTK10(diemTongKetResponse.getDiemCC() * 0.1 + diemTongKetResponse.getDiemGK() * 0.3 + diemTongKetResponse.getDiemCK() * 0.6);
        diemTongKetResponse.setDiemTK4VaDiemTKC(diemTongKetResponse.getDiemTK10());
        return diemTongKetResponse;
    }
}

