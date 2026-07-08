package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.FavoriteResponse;
import com.kayque.streamhubapi.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService service;

    @PostMapping("/movies/{movieId}")
    @ResponseStatus(HttpStatus.CREATED)
    public FavoriteResponse addMovieToFavorites(
            @PathVariable Long movieId,
            Authentication authentication
    ) {
        return service.addMovieToFavorites(movieId, authentication.getName());
    }

    @PostMapping("/series/{seriesId}")
    @ResponseStatus(HttpStatus.CREATED)
    public FavoriteResponse addSeriesToFavorites(
            @PathVariable Long seriesId,
            Authentication authentication
    ) {
        return service.addSeriesToFavorites(seriesId, authentication.getName());
    }

    @GetMapping
    public List<FavoriteResponse> findAll(Authentication authentication) {
        return service.findAllByUser(authentication.getName());
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