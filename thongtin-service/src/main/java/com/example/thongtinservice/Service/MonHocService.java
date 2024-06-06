package com.example.thongtinservice.Service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.DTO.MonHocDTO;
import com.example.thongtinservice.Model.MonHoc;
import com.example.thongtinservice.Repository.MonHocRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * MonHocService
 */
@Service
public class MonHocService {
    private static final Logger logger = LoggerFactory.getLogger(ApiResponse.class);

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private WebClient.Builder webClient;

    public ApiResponse getSizeListMonHoc() {
        Integer dsMonHoc = monHocRepository.getSizeListMonHoc();
        if (dsMonHoc == null) {
            return new ApiResponse<Integer>(201, "Lấy danh sách môn học thất bại!", null);
        }
        return new ApiResponse<Integer>(200, "Lấy danh sách môn học thành công!", dsMonHoc);
    }

    public ApiResponse getListMonHoc(int start, int size) {
        List<MonHoc> dsMonHoc = monHocRepository.getListMonHoc(start, size);
        List<MonHocDTO> dsMonHocDTO = new ArrayList<>();
        if (dsMonHoc == null) {
            return new ApiResponse<List<MonHocDTO>>(201, "Lấy danh sách môn học thất bại!", null);
        }

        for (int i = 0; i < dsMonHoc.size(); i++) {
            MonHocDTO dto = new MonHocDTO(start + i + 1, dsMonHoc.get(i));
            dsMonHocDTO.add(dto);
        }
        return new ApiResponse<List<MonHocDTO>>(200, "Lấy danh sách môn học thành công!", dsMonHocDTO);
    }

    public ApiResponse findListMonHoc(String search) {
        List<MonHoc> dsMH = monHocRepository.findListMonHoc(search);
        List<MonHocDTO> dsMonHocDTO = new ArrayList<>();
        if (dsMH == null) {
            return new ApiResponse<List<MonHocDTO>>(202, "Không tìm thấy môn học!", null);
        }

        for (int i = 0; i < dsMH.size(); i++) {
            MonHocDTO dto = new MonHocDTO(i + 1, dsMH.get(i));
            dsMonHocDTO.add(dto);
        }
        return new ApiResponse<List<MonHocDTO>>(200, "Lấy danh sách môn học thành công!", dsMonHocDTO);
    }

    @CircuitBreaker(name = "insertMonHoc", fallbackMethod = "fallbackInsertMonHoc")
    public ApiResponse insertMonHoc(MonHoc monHoc, String token) {
        Integer kiemTraTrung = monHocRepository.kiemTraMonHoc(monHoc.getMaMH());
        if (kiemTraTrung == 1) {
            return new ApiResponse<>(203, "Môn học đã tồn tại", null);
        }

        monHocRepository.insertMonHoc(monHoc.getMaMH(),
                monHoc.getTenMH(),
                monHoc.getSoTietLT(),
                monHoc.getSoTietTH(),
                monHoc.getSoTinChi());

        Mono<ApiResponse> response = webClient.build().post()
                .uri("http://lop-tin-chi-service/api/lop-tin-chi/them-mon-hoc")
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(Mono.just(monHoc), MonHoc.class)
                .retrieve()
                .bodyToMono(ApiResponse.class);
        ApiResponse result = response.block();
        System.out.println("check:" + result.toString());

        return new ApiResponse<>(200, "Thêm mới môn học thành công", null);
    }

    public ApiResponse updateMonHoc(MonHoc monHoc) {
        monHocRepository.updateMonHoc(monHoc.getMaMH(),
                monHoc.getTenMH(),
                monHoc.getSoTietLT(),
                monHoc.getSoTietTH(),
                monHoc.getSoTinChi());
        return new ApiResponse<Object>(200, "Cập nhật môn học thành công", null);
    }

    public ApiResponse deleteMonHoc(String maMH) {
        Integer kiemTraXoaMH = monHocRepository.kiemTraXoaMonHoc(maMH);
        if (kiemTraXoaMH == 1) {
            return new ApiResponse<Object>(203, "Môn học đang được giảng dạy không thể xoá!", null);
        }
        monHocRepository.deleteMonHoc(maMH);
        return new ApiResponse<Object>(200, "Xoá môn học thành công", null);
    }

    public ApiResponse fallbackInsertMonHoc(MonHoc monHoc, String token, Throwable t) {
        logger.error("Fallback triggered for monHoc={} due to: {}", monHoc.toString(), t.getMessage());
        return new ApiResponse<>(300, "Lưu môn học thất bại!", null);
    }

}