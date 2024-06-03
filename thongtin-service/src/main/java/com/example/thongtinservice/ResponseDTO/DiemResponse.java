package com.example.thongtinservice.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiemResponse {
    @JsonProperty("MASV")
    private String maSV;

    @JsonProperty("HO")
    private String ho;

    @JsonProperty("TEN")
    private String ten;

    @JsonProperty("DIEMCC")
    private int diemCC;

    @JsonProperty("DIEMGK")
    private float diemGK;

    @JsonProperty("DIEMCK")
    private float diemCK;

    @JsonProperty("DIEMTK")
    private float diemTK;

    @JsonProperty("KETQUA")
    private boolean ketQua;
}
