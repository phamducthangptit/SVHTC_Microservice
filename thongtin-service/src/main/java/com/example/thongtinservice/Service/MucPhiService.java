package com.example.thongtinservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.Repository.MucPhiRepository;
import com.example.thongtinservice.ResponseDTO.ResponseMucPhi;
import com.netflix.discovery.converters.Auto;

@Service
public class MucPhiService {
    @Autowired
    private MucPhiRepository mucPhiRepository;

    public ApiResponse getMucPhi(ResponseMucPhi data) {
        Integer mucphi = mucPhiRepository.getMucPhi(data.getMaSV(), data.getMaMH());
        return new ApiResponse<Integer>(200, "Lấy mức phí thành công", mucphi);
    }
}
