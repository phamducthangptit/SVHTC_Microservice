package com.example.thongtinservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thongtinservice.Model.He;
import com.example.thongtinservice.Repository.HeRepository;

@Service
public class HeService {
    @Autowired
    private HeRepository heRepository;

    public List<He> getListHe() {
        return heRepository.getListHe();
    }
}
