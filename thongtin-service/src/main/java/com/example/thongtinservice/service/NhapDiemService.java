package com.example.thongtinservice.service;

import com.example.thongtinservice.responseservice.LopTinChiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NhapDiemService {
    private static final Logger logger = LoggerFactory.getLogger(NhapDiemService.class);
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public NhapDiemService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @CircuitBreaker(name = "nhapDiemService", fallbackMethod = "fallbackDanhSachLtcTheoMaGV")
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

    public List<LopTinChiResponse> fallbackDanhSachLtcTheoMaGV(String maGV, Throwable t) {
        logger.error("Fallback triggered for maGV={} due to: {}", maGV, t.getMessage());
        return Collections.emptyList();
    }
}
