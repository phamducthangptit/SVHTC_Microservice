package com.example.thongtinservice.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.DTO.LopDTO;
import com.example.thongtinservice.DTO.LopDTOService;
import com.example.thongtinservice.Model.Lop;
import com.example.thongtinservice.Model.MonHoc;
import com.example.thongtinservice.Repository.LopRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Service
public class LopService {
    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private WebClient.Builder webClient;

    public LopDTO mapLopDTO(Map<String, Object> lop, int stt) {
        LopDTO result = new LopDTO();
        result.setStt(stt);
        result.setMaLop((String) lop.get("MALOP"));
        result.setTenLop((String) lop.get("TENLOP"));
        result.setKhoaHoc((String) lop.get("KHOAHOC"));
        result.setTenHe((String) lop.get("TEN_HE"));
        result.setTenKhoa((String) lop.get("TENKHOA"));
        return result;
    }

    public ApiResponse getSizeListLop() {
        Integer size = lopRepository.getSizeListLop();
        if (size == null) {
            return new ApiResponse<Integer>(201, "Lấy danh sách lớp học thất bại!", null);
        }
        return new ApiResponse<Integer>(200, "Lấy danh sách lớp học thành công!", size);
    }

    public ApiResponse getListLop(int start, int size) {
        List<Map<String, Object>> dsLop = lopRepository.getListLop(start, size);
        List<LopDTO> dsLopDTO = new ArrayList<>();
        if (dsLop == null) {
            return new ApiResponse<List<LopDTO>>(201, "Lấy danh sách lớp học thất bại!", null);
        }

        for (int i = 0; i < dsLop.size(); i++) {
            LopDTO dto = mapLopDTO(dsLop.get(i), start + i + 1);
            dsLopDTO.add(dto);
        }
        return new ApiResponse<List<LopDTO>>(200, "Lấy danh sách lớp học thành công!", dsLopDTO);
    }

    public ApiResponse timLop(String search) {
        List<Map<String, Object>> dsLop = lopRepository.findListLop(search);
        List<LopDTO> dsLopDTO = new ArrayList<>();
        if (dsLop == null) {
            return new ApiResponse<List<LopDTO>>(201, "Lấy danh sách lớp học thất bại!", null);
        }

        for (int i = 0; i < dsLop.size(); i++) {
            LopDTO dto = mapLopDTO(dsLop.get(i), i + 1);
            dsLopDTO.add(dto);
        }
        return new ApiResponse<List<LopDTO>>(200, "Lấy danh sách lớp học thành công!", dsLopDTO);
    }

    @CircuitBreaker(name = "insertLopHoc", fallbackMethod = "fallbackInsertLopHoc")
    public ApiResponse themLop(Map<String, Object> data) {
        Integer kiemTraTrung = lopRepository.kiemTraLop((String) data.get("maLop"));
        if (kiemTraTrung == 1) {
            return new ApiResponse<>(203, "Lớp học đã tồn tại", null);
        }
        LopDTOService lop = new LopDTOService((String) data.get("maLop"), (String) data.get("tenLop"),
                (String) data.get("maKhoa"));
        Mono<ApiResponse> response = webClient.build().post()
                .uri("http://lop-tin-chi-service/api/lop-tin-chi/them-lop-hoc")
                .body(Mono.just(lop), LopDTOService.class)
                .retrieve()
                .bodyToMono(ApiResponse.class);
        ApiResponse result = response.block();
        if (result.getCode() != 200) {
            return new ApiResponse<>(300, "Có lỗi khi lưu lớp học!", null);
        }
        String khoaHoc = (String) data.get("khoaHoc1") + "-"
                + (String) data.get("khoaHoc2");
        lopRepository.insertLop((String) data.get("maLop"), (String) data.get("tenLop"), khoaHoc,
                (String) data.get("maKhoa"), (Integer) data.get("idHe"), true);
        return new ApiResponse<>(200, "Thêm mới lớp học thành công", null);
    }

    public ApiResponse fallbackInsertLopHoc(Map<String, Object> data, Throwable t) {
        return new ApiResponse<>(300, "Lưu môn học thất bại! " + t.getMessage(), null);
    }

    @CircuitBreaker(name = "updateLopHoc", fallbackMethod = "fallbackUpdateLopHoc")
    public ApiResponse updateLopHoc(Map<String, Object> data) {
        LopDTOService lop = new LopDTOService((String) data.get("maLop"), (String) data.get("tenLop"),
                (String) data.get("maKhoa"));
        Mono<ApiResponse> response = webClient.build().post()
                .uri("http://lop-tin-chi-service/api/lop-tin-chi/thay-doi-lop")
                .body(Mono.just(lop), LopDTOService.class)
                .retrieve()
                .bodyToMono(ApiResponse.class);
        ApiResponse result = response.block();
        if (result.getCode() != 200) {
            return new ApiResponse<>(300, "Có lỗi khi cập nhật lớp học!", null);
        }
        String khoaHoc = (String) data.get("khoaHoc1") + "-"
                + (String) data.get("khoaHoc2");
        lopRepository.updateLop((String) data.get("maLop"), (String) data.get("tenLop"), khoaHoc,
                (String) data.get("maKhoa"), (Integer) data.get("idHe"), true);
        return new ApiResponse<Object>(200, "Cập nhật lớp học thành công", null);
    }

    public ApiResponse fallbackUpdateLopHoc(Map<String, Object> data, Throwable t) {
        return new ApiResponse<>(300, "Thay đổi lớp học thất bại! " + t.getMessage(), null);
    }

    @CircuitBreaker(name = "deleteLopHoc", fallbackMethod = "fallbackDeleteLopHoc")
    public ApiResponse deleteLopHoc(String maLop) {
        Integer kiemTraXoaLop = lopRepository.kiemTraXoaLop(maLop);
        if (kiemTraXoaLop == 1) {
            return new ApiResponse<Object>(203, "Lớp học đang trong quá trình hoạt động, không thể xoá!", null);
        }
        Mono<ApiResponse> response = webClient.build().post()
                .uri("http://lop-tin-chi-service/api/lop-tin-chi/xoa-lop-hoc")
                .body(Mono.just(maLop), String.class)
                .retrieve()
                .bodyToMono(ApiResponse.class);
        ApiResponse result = response.block();
        if (result.getCode() != 200) {
            return new ApiResponse<>(300, "Có lỗi khi xoá lớp học!", null);
        }
        lopRepository.deleteLopHoc(maLop);
        return new ApiResponse<>(200, "Xoá lớp học thành công", null);
    }

    public ApiResponse fallbackDeleteLopHoc(String maLop, Throwable t) {
        return new ApiResponse<>(300, "Xoá lớp học thất bại! " + t.getMessage(), null);
    }

    public List<Lop> getAllLop() {
        return lopRepository.findAll();
    }

    public Lop lopTheoMaLop(String maLop) {
        if (maLop == null) {
            return null;
        }
        return lopRepository.findBymalop(maLop);
    }

}
