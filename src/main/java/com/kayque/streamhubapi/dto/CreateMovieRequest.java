package com.kayque.streamhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateMovieRequest(

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Release year is required")
        Integer releaseYear,

        @NotNull(message = "Duration is required")
        @Positive(message = "Duration must be positive")
        Integer durationMinutes,

        @NotBlank(message = "Age rating is required")
        String ageRating,

        String posterUrl,

        String bannerUrl,

        LocalDate releaseDate
) {
}