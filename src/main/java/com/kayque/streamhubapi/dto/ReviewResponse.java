package com.kayque.streamhubapi.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        Integer rating,
        String comment,
        Long userId,
        String userName,
        Long movieId,
        String movieTitle,
        Long seriesId,
        String seriesTitle,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}