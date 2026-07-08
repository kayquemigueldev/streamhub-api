package com.kayque.streamhubapi.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UpdateSeriesRequest(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Integer releaseYear,

        @NotNull
        Integer seasonsCount,

        @NotNull
        Integer episodesCount,

        @NotBlank
        String ageRating,

        @NotBlank
        String posterUrl,

        @NotBlank
        String bannerUrl,

        @NotNull
        LocalDate releaseDate,

        @NotNull
        Long genreId

) {
}