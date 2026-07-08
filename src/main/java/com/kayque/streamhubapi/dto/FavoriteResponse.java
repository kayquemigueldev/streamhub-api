package com.kayque.streamhubapi.dto;

import java.time.LocalDateTime;

public record FavoriteResponse(
        Long id,
        MovieResponse movie,
        SeriesResponse series,
        LocalDateTime createdAt
) {
}