package com.kayque.streamhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateMovieRequest(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Integer releaseYear,

        @NotNull
        Integer durationMinutes,

        @NotBlank
        String ageRating,

        String posterUrl,

        String bannerUrl,

        LocalDate releaseDate,

        @NotNull
        Long genreId

) {
}