package com.example.thanhtoanhocphiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseUpdate {
    private String maSV;
    private String nienKhoa;
    private int hocKi;
}
