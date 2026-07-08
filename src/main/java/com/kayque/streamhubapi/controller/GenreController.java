package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.CreateGenreRequest;
import com.kayque.streamhubapi.dto.GenreResponse;
import com.kayque.streamhubapi.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreResponse create(@RequestBody @Valid CreateGenreRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<GenreResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GenreResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }
}