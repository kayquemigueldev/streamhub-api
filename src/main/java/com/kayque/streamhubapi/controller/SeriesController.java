package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.CreateSeriesRequest;
import com.kayque.streamhubapi.dto.SeriesResponse;
import com.kayque.streamhubapi.service.SeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeriesResponse create(@RequestBody @Valid CreateSeriesRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<SeriesResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SeriesResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }
}