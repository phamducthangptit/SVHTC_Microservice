package com.example.thongtinservice.Service;

import com.example.thongtinservice.Repository.GiangVienRepository;
import com.example.thongtinservice.RequestDTO.DiemRequest;
import com.example.thongtinservice.ResponseDTO.DiemResponse;
import com.example.thongtinservice.ResponseService.LopTinChiResponse;
import com.example.thongtinservice.ResponseService.SinhVienResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NhapDiemService {
    private static final Logger logger = LoggerFactory.getLogger(NhapDiemService.class);
    private final WebClient.Builder webClientBuilder;

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    public NhapDiemService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @CircuitBreaker(name = "dsLopTinChi", fallbackMethod = "fallbackDanhSachLtcTheoMaGV")
    public List<LopTinChiResponse> danhSachLtcTheoMaGV(String maGV) {
        String uri = UriComponentsBuilder.fromUriString("http://lop-tin-chi-service/api/lop-tin-chi/danh-sach-ltc")
                .queryParam("ma-gv", maGV)
                .build()
                .toUriString();
        List<LopTinChiResponse> result = new ArrayList<>();
        result = webClientBuilder.build().get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(LopTinChiResponse.class)
                .collectList()
                .block();
        return result;
    }

    @CircuitBreaker(name = "dsSinhVienLtc", fallbackMethod = "fallbackDanhSachSinhVienLtc")
    public List<SinhVienResponse> danhSachSinhVienLtc(int maLTC) {
        String uri = UriComponentsBuilder.fromUriString("http://lop-tin-chi-service/api/lop-tin-chi/danh-sach-sinh-vien")
                .queryParam("ma-ltc", maLTC)
                .build()
                .toUriString();
        List<SinhVienResponse> result = new ArrayList<>();
        result = webClientBuilder.build().get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(SinhVienResponse.class)
                .collectList()
                .block();
        return result;
    }

    public List<DiemResponse> danhSachDiemSinhVienLtc(int maLTC, String nienKhoa, int hocKi, String maMH) {
        List<SinhVienResponse> danhSachSvLtc = danhSachSinhVienLtc(maLTC); // danh sách tất cả sv đã đăng kí
        List<DiemResponse> danhSachSvDaCoDiem = danhSachSinhVienDaCoDiem(nienKhoa, hocKi, maMH); // danh sách sinh viên đã có điểm
        List<DiemResponse> danhSachDiem = new ArrayList<>(); // danh sách tất cả sinh viên đã có và chưa có điểm
        for (SinhVienResponse sv : danhSachSvLtc) {
            Optional<DiemResponse> matchingSv = Optional.empty();
            if (danhSachSvDaCoDiem != null) {
                matchingSv = danhSachSvDaCoDiem.stream().filter(s -> s.getMaSV().equals(sv.getMaSV())).findFirst();
            }
            if (matchingSv.isPresent()) {// sv nay da co diem
                danhSachDiem.add(matchingSv.get());
            } else {
                DiemResponse newDiem = new DiemResponse();
                newDiem.setMaSV(sv.getMaSV());
                newDiem.setHo(sv.getHo());
                newDiem.setTen(sv.getTen());
                newDiem.setDiemCC(0);
                newDiem.setDiemGK(0);
                newDiem.setDiemCK(0);
                newDiem.setDiemTK(0);
                newDiem.setKetQua(false);
                danhSachDiem.add(newDiem);
            }
        }
        return danhSachDiem;
    }

    public List<DiemResponse> danhSachSinhVienDaCoDiem(String nienKhoa, int hocKi, String maMH) {
        List<Object[]> resultCallSp = giangVienRepository.danhSachSinhVienDaCoDiem(nienKhoa, hocKi, maMH);
        if (!resultCallSp.isEmpty()) {
            List<DiemResponse> result = new ArrayList<>();
            for (Object[] re : resultCallSp) {
                DiemResponse diemResponse = new DiemResponse();
                diemResponse.setMaSV((String) re[0]);
                diemResponse.setHo((String) re[1]);
                diemResponse.setTen((String) re[2]);
                diemResponse.setDiemCC((int) re[3]);
                diemResponse.setDiemGK(((Double) re[4]).floatValue()); // Chuyển từ Double sang float
                diemResponse.setDiemCK(((Double) re[5]).floatValue());

                diemResponse.setDiemTK((float) (diemResponse.getDiemCC() * 0.1 + diemResponse.getDiemGK() * 0.3 + diemResponse.getDiemCK() * 0.6));
                diemResponse.setKetQua(diemResponse.getDiemTK() > 4);
                result.add(diemResponse);
            }
            return result;
        }
        return null;
    }

    public void updateDiem(DiemRequest diemRequest) {
        giangVienRepository.updateDiem(diemRequest.getMaSv(),
                diemRequest.getNienKhoa(),
                diemRequest.getHocKi(),
                diemRequest.getMaMH(),
                diemRequest.getDiemCC(),
                diemRequest.getDiemGK(),
                diemRequest.getDiemCK());
    }

    public List<LopTinChiResponse> fallbackDanhSachLtcTheoMaGV(String maGV, Throwable t) {
        logger.error("Fallback triggered for maGV={} due to: {}", maGV, t.getMessage());
        return Collections.emptyList();
    }

    public List<LopTinChiResponse> fallbackDanhSachSinhVienLtc(int maLTC, Throwable t) {
        logger.error("Fallback triggered for maLTC={} due to: {}", maLTC, t.getMessage());
        return Collections.emptyList();
    }
}