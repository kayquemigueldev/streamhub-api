package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.CreateMovieRequest;
import com.kayque.streamhubapi.dto.MovieResponse;
import com.kayque.streamhubapi.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.kayque.streamhubapi.dto.UpdateMovieRequest;
import com.kayque.streamhubapi.dto.PageMovieResponse;

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

    @PutMapping("/{id}")
    public MovieResponse update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateMovieRequest request) {

        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public PageMovieResponse search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.search(
                title,
                genreId,
                releaseYear,
                page,
                size,
                sortBy,
                direction
        );
    }
}