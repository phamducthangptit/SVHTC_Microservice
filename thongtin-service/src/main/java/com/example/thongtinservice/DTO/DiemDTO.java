package com.example.thongtinservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiemDTO {

    @JsonProperty("MASV")
    private String MASV;

    @JsonProperty("NIENKHOA")
    private String nienKhoa;

    @JsonProperty("HOCKI")
    private String hocKi;

    @JsonProperty("MAMH")
    private String maMH;

    @JsonProperty("MAGV")
    private String maGV;

    @JsonProperty("DIEM_CC")
    private int DIEM_CC;

    @JsonProperty("DIEM_GK")
    private float DIEM_GK;

    @JsonProperty("DIEM_CK")
    private float DIEM_CK;
}
