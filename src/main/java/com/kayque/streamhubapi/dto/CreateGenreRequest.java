package com.kayque.streamhubapi.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateGenreRequest(

        @NotBlank(message = "Genre name is required")
        String name

) {
}