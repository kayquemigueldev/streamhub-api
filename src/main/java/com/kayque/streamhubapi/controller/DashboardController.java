package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.DashboardResponse;
import com.kayque.streamhubapi.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @GetMapping
    public DashboardResponse getDashboard() {
        return service.getDashboard();
    }
}