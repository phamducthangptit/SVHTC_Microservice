package com.example.thanhtoanhocphiservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String maSV;
    private String nienKhoa;
    private int hocKi;
    private long amount;
}
