package com.kayque.streamhubapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SeriesResponse(
        Long id,
        String title,
        String description,
        Integer releaseYear,
        Integer seasonsCount,
        Integer episodesCount,
        String ageRating,
        String posterUrl,
        String bannerUrl,
        LocalDate releaseDate,
        GenreResponse genre,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}