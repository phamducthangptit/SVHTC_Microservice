package com.example.thanhtoanhocphiservice.service;

import com.example.thanhtoanhocphiservice.dto.HocPhiList;
import com.example.thanhtoanhocphiservice.dto.HocPhiSVDTO;
import com.example.thanhtoanhocphiservice.dto.SinhVienDTO;
import com.example.thanhtoanhocphiservice.repository.SinhVienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class SinhVienService {
    @Autowired
    SinhVienRepo repository;

    public HocPhiSVDTO mapHPDTO (Map<String , Object> hp)
    {
        HocPhiSVDTO hocPhiSVDTO = new HocPhiSVDTO();
        hocPhiSVDTO.setNienkhoa((String) hp.get("nienkhoa"));
        hocPhiSVDTO.setHocki((int) hp.get("hocki"));
        hocPhiSVDTO.setSotien((int) hp.get("sotien"));
        hocPhiSVDTO.setDathu(Integer.parseInt(hp.get("dathu").toString()));
        hocPhiSVDTO.setConno(Integer.parseInt(hp.get("conno").toString()));
        return hocPhiSVDTO;
    }
    public List<HocPhiSVDTO> xemHP(String masv){

        List<Map<String,Object>> data = repository.hocphiSV(masv);
        List<HocPhiSVDTO> hplist = new ArrayList<>();
        for (int i = 0 ; i< data.size();i++)
        {
            HocPhiSVDTO temp = mapHPDTO(data.get(i));
            hplist.add(temp);
        }
        return  hplist;
    }
    public HocPhiList mapHPList (Map<String , Object> hp)
    {
        HocPhiList hocPhiSVDTO = new HocPhiList();
        hocPhiSVDTO.setNienkhoa((String) hp.get("nienkhoa"));
        hocPhiSVDTO.setHocki((int) hp.get("hocki"));
        hocPhiSVDTO.setSotien((int) hp.get("sotien"));
        hocPhiSVDTO.setMasv((String) hp.get("masv"));
        hocPhiSVDTO.setHoten((String) hp.get("hoten"));
        if (hp.get("dadong").toString().equals("Chua")) hocPhiSVDTO.setDadong((String) "Chưa đóng");
        else hocPhiSVDTO.setDadong((String) hp.get("dadong").toString());
        return hocPhiSVDTO;
    }
    public List<HocPhiList> DSSVHP(){

        List<Map<String,Object>> data = repository.DSSVhocphi();
        List<HocPhiList> hplist = new ArrayList<>();
        for (int i = 0 ; i< data.size();i++)
        {
            HocPhiList temp = mapHPList(data.get(i));
            hplist.add(temp);
        }
        return  hplist;
    }
}
