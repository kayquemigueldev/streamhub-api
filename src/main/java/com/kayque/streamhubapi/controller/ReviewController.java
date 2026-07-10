package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.CreateReviewRequest;
import com.kayque.streamhubapi.dto.ReviewResponse;
import com.kayque.streamhubapi.dto.UpdateReviewRequest;
import com.kayque.streamhubapi.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping("/movies/{movieId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createMovieReview(
            @PathVariable Long movieId,
            @RequestBody @Valid CreateReviewRequest request,
            Authentication authentication
    ) {
        return service.createMovieReview(
                movieId,
                request,
                authentication.getName()
        );
    }

    @PostMapping("/series/{seriesId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createSeriesReview(
            @PathVariable Long seriesId,
            @RequestBody @Valid CreateReviewRequest request,
            Authentication authentication
    ) {
        return service.createSeriesReview(
                seriesId,
                request,
                authentication.getName()
        );
    }

    @GetMapping("/movies/{movieId}")
    public List<ReviewResponse> findByMovie(@PathVariable Long movieId) {
        return service.findByMovie(movieId);
    }

    @GetMapping("/series/{seriesId}")
    public List<ReviewResponse> findBySeries(@PathVariable Long seriesId) {
        return service.findBySeries(seriesId);
    }

    @PutMapping("/{id}")
    public ReviewResponse update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateReviewRequest request,
            Authentication authentication
    ) {
        return service.update(id, request, authentication.getName());
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