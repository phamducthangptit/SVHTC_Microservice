package com.example.loptinchiservice.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LopTinChiResponse {
    @JsonProperty("MALTC")
    private int maLTC;

    @JsonProperty("NIENKHOA")
    private String nienKhoa;

    @JsonProperty("HOCKI")
    private int hocKi;

    @JsonProperty("MAMH")
    private String maMH;

    @JsonProperty("TENMH")
    private String tenMH;
}

