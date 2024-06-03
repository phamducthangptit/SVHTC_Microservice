package com.example.thongtinservice.Service;

import com.example.thongtinservice.Repository.SinhVienRepository;
import com.example.thongtinservice.ResponseDTO.DiemTongKetResponse;
import com.example.thongtinservice.ResponseDTO.NienKhoaHocKi;
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

    public List<List<DiemTongKetResponse>> xemDiem(String maSV){
        List<NienKhoaHocKi> listNienKhoaHocKi = getNienKhoaHocKi(maSV);
        if(!listNienKhoaHocKi.isEmpty()){
            List<Object[]> resultCallSp = repository.xemDiem(maSV);
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
