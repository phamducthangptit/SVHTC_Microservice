package com.example.thongtinservice.Responsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiemResponse {
    @JsonProperty("NIENKHOA")
    private String nienKhoa;

    @JsonProperty("HOCKI")
    private int hocKi;

    @JsonProperty("MAMH")
    private String maMH;

    @JsonProperty("TENMH")
    private String tenMH;

    @JsonProperty("SOTINCHI")
    private int soTC;

    @JsonProperty("DIEMCC")
    private int diemCC;

    @JsonProperty("DIEMGK")
    private double diemGK;

    @JsonProperty("DIEMCK")
    private double diemCK;

    @JsonProperty("DIEMTK10")
    private double diemTK10;

    @JsonProperty("DIEMTK4")
    private double diemTK4;

    @JsonProperty("DIEMTKC")
    private String diemTKC;

    @JsonProperty("KETQUA")
    private boolean ketQua;

    public void setDiemTK4VaDiemTKC(double diemTK10) {
        if (diemTK10 >= 9 && diemTK10 <= 10) {
            this.diemTK4 = 4;
            this.diemTKC = "A+";
            this.ketQua = true;
        }

        if (diemTK10 >= 8.5 & diemTK10 < 9) {
            this.diemTK4 = (float) 3.7;
            this.diemTKC = "A";
            this.ketQua = true;
        }

        if (diemTK10 >= 8 && diemTK10 < 8.5) {
            this.diemTK4 = (float) 3.5;
            this.diemTKC = "B+";
            this.ketQua = true;
        }

        if (diemTK10 >= 7 && diemTK10 < 8) {
            this.diemTK4 = (float) 3.0;
            this.diemTKC = "B";
            this.ketQua = true;
        }

        if (diemTK10 >= 6.5 && diemTK10 < 7) {
            this.diemTK4 = (float) 2.5;
            this.diemTKC = "C+";
            this.ketQua = true;
        }

        if (diemTK10 >= 5.5 && diemTK10 < 6.5) {
            this.diemTK4 = (float) 2.0;
            this.diemTKC = "C";
            this.ketQua = true;
        }

        if (diemTK10 >= 5 && diemTK10 < 5.5) {
            this.diemTK4 = (float) 1.5;
            this.diemTKC = "D+";
            this.ketQua = true;
        }

        if (diemTK10 >= 4 && diemTK10 < 5) {
            this.diemTK4 = (float) 1.0;
            this.diemTKC = "D";
            this.ketQua = true;
        }
        if (diemTK10 < 4) {
            this.diemTK4 = (float) 0;
            this.diemTKC = "F";
            this.ketQua = false;
        }
    }
}
