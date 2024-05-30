package com.example.loptinchiservice.responsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SinhVienLtcResponse {
    @JsonProperty("MASV")
    private String maSV;
    @JsonProperty("HO")
    private String ho;
    @JsonProperty("TEN")
    private String ten;
}
