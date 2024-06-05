package com.example.thongtinservice.RequestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiemRequest {
    @JsonProperty("MASV")
    private String maSv;

    @JsonProperty("NIENKHOA")
    private String nienKhoa;

    @JsonProperty("HOCKI")
    private int hocKi;

    @JsonProperty("MAMH")
    private String maMH;

    @JsonProperty("MAGV")
    private String maGv;

    @JsonProperty("DIEMCC")
    private int diemCC;

    @JsonProperty("DIEMGK")
    private float diemGK;

    @JsonProperty("DIEMCK")
    private float diemCK;
}
