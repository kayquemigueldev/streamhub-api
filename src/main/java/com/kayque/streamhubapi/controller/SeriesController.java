package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.CreateSeriesRequest;
import com.kayque.streamhubapi.dto.SeriesResponse;
import com.kayque.streamhubapi.service.SeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.kayque.streamhubapi.dto.UpdateSeriesRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @PutMapping("/{id}")
    public SeriesResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSeriesRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
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