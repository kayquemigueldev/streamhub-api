package com.kayque.streamhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateSeriesRequest(

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Release year is required")
        Integer releaseYear,

        @NotNull(message = "Seasons count is required")
        @Positive(message = "Seasons count must be positive")
        Integer seasonsCount,

        @NotNull(message = "Episodes count is required")
        @Positive(message = "Episodes count must be positive")
        Integer episodesCount,

        @NotBlank(message = "Age rating is required")
        String ageRating,

        String posterUrl,

        String bannerUrl,

        LocalDate releaseDate,

        @NotNull(message = "Genre id is required")
        Long genreId

) {
}