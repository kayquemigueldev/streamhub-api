package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.CreateMovieRequest;
import com.kayque.streamhubapi.dto.MovieResponse;
import com.kayque.streamhubapi.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse create(@RequestBody @Valid CreateMovieRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<MovieResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }
}