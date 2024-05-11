package com.example.thanhtoanhocphiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReturn {
    private String responseCode;
    private String amount;
    private String infoPayment;
    private String bank;
    private String transactionNo;
    private String maSV;
}
