package com.kayque.streamhubapi.dto;

public record CreateFavoriteRequest(
        Long movieId,
        Long seriesId
) {
}