package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.WatchlistResponse;
import com.kayque.streamhubapi.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService service;

    @PostMapping("/movies/{movieId}")
    @ResponseStatus(HttpStatus.CREATED)
    public WatchlistResponse addMovie(
            @PathVariable Long movieId,
            Authentication authentication
    ) {
        return service.addMovie(movieId, authentication.getName());
    }

    @PostMapping("/series/{seriesId}")
    @ResponseStatus(HttpStatus.CREATED)
    public WatchlistResponse addSeries(
            @PathVariable Long seriesId,
            Authentication authentication
    ) {
        return service.addSeries(seriesId, authentication.getName());
    }

    @GetMapping
    public List<WatchlistResponse> findAll(Authentication authentication) {
        return service.findAll(authentication.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            Authentication authentication
    ) {
        service.delete(id, authentication.getName());
    }
}