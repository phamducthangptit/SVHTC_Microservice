package com.example.thongtinservice.service;

import com.example.thongtinservice.repository.SinhVienRepository;
import com.example.thongtinservice.responsedto.DiemResponse;
import com.example.thongtinservice.responsedto.NienKhoaHocKi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SinhVienService {
    @Autowired
    private SinhVienRepository repository;

    public Map<String, ?> thongTinCaNhanSV(String maSV){
        return repository.thongTinCaNhanSinhVien(maSV);
    }

    public List<NienKhoaHocKi> getNienKhoaHocKi(String maSV){
        List<NienKhoaHocKi> listNienKhoaHocKi = new ArrayList<>();
        List<Object[]> resultCallSp =  repository.getNienKhoaHocKi(maSV);
        for(Object[] result : resultCallSp){
            NienKhoaHocKi nienKhoaHocKi = new NienKhoaHocKi();
            nienKhoaHocKi.setNienKhoa((String) result[0]);
            nienKhoaHocKi.setHocKi((int) result[1]);
            listNienKhoaHocKi.add(nienKhoaHocKi);
        }
        return listNienKhoaHocKi;
    }

    public List<List<DiemResponse>> xemDiem(String maSV){
        List<NienKhoaHocKi> listNienKhoaHocKi = getNienKhoaHocKi(maSV);
        if(!listNienKhoaHocKi.isEmpty()){
            List<Object[]> resultCallSp = repository.xemDiem(maSV);
            List<DiemResponse> listDiem = new ArrayList<>(); // danh sach tat ca diem
            List<List<DiemResponse>> listDiemLoc = new ArrayList<>();
            for(Object[] result : resultCallSp){
                DiemResponse diemResponse = getDiemResponse(result);
                listDiem.add(diemResponse);
            }
            // loc theo tung nienkhoa hocki
            for(NienKhoaHocKi nienKhoaHocKi : listNienKhoaHocKi){
                List<DiemResponse> listDiemTmp = new ArrayList<>();
                for(DiemResponse diemResponse : listDiem){
                    if(diemResponse.getNienKhoa().equals(nienKhoaHocKi.getNienKhoa()) && (diemResponse.getHocKi() == nienKhoaHocKi.getHocKi())){
                        listDiemTmp.add(diemResponse);
                    }
                }
                listDiemLoc.add(listDiemTmp);
            }
            return listDiemLoc;
        }
        return null;
    }

    private static DiemResponse getDiemResponse(Object[] result) {
        DiemResponse diemResponse = new DiemResponse();
        diemResponse.setNienKhoa((String) result[0]);
        diemResponse.setHocKi((int) result[1]);
        diemResponse.setMaMH((String) result[2]);
        diemResponse.setTenMH((String) result[3]);
        diemResponse.setSoTC((int) result[4]);
        diemResponse.setDiemCC((int) result[5]);
        diemResponse.setDiemGK((double) result[6]);
        diemResponse.setDiemCK((double) result[7]);

        // tinh diem tk
        diemResponse.setDiemTK10(diemResponse.getDiemCC() * 0.1 + diemResponse.getDiemGK() * 0.3 + diemResponse.getDiemCK() * 0.6);
        diemResponse.setDiemTK4VaDiemTKC(diemResponse.getDiemTK10());
        return diemResponse;
    }
}
