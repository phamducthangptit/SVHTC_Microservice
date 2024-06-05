package com.example.thanhtoanhocphiservice.service;

import com.example.thanhtoanhocphiservice.dto.HocPhiSVDTO;
import com.example.thanhtoanhocphiservice.dto.SinhVienDTO;
import com.example.thanhtoanhocphiservice.repository.SinhVienRepo;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SinhVienService {
    SinhVienRepo repository;
    public int themSV(SinhVienDTO sv) {
        try {
            repository.themSV(
                    sv.getMasv(),
                    sv.getHo(),
                    sv.getTen()
            );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public int updateSV(SinhVienDTO sv) {
        try {
            repository.updateSV(
                    sv.getMasv(),
                    sv.getHo(),
                    sv.getTen()
            );
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public int xoaSV(String masv) {

        try {
            repository.xoaSV(masv);
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }
    public HocPhiSVDTO mapHPDTO (Map<String , Object> hp)
    {
        HocPhiSVDTO hocPhiSVDTO = new HocPhiSVDTO();
        hocPhiSVDTO.setNienkhoa((String) hp.get("nienkhoa"));
        hocPhiSVDTO.setHocki((int) hp.get("hocki"));
        hocPhiSVDTO.setSotien((int) hp.get("sotien"));
        hocPhiSVDTO.setDathu((int) hp.get("dathu"));
        hocPhiSVDTO.setConno((int) hp.get("conno"));
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
}
