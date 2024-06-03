package com.example.thongtinservice.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NienKhoaHocKi {
    @JsonProperty("NIENKHOA")
    private String nienKhoa;

    @JsonProperty("HOCKI")
    private int hocKi;
}
