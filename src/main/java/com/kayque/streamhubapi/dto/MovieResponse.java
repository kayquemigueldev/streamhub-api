package com.kayque.streamhubapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MovieResponse(
        Long id,
        String title,
        String description,
        Integer releaseYear,
        Integer durationMinutes,
        String ageRating,
        String posterUrl,
        String bannerUrl,
        LocalDate releaseDate,
        GenreResponse genre,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}