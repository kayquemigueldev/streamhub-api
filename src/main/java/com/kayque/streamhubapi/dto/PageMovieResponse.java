package com.kayque.streamhubapi.dto;

import java.util.List;

public record PageMovieResponse(
        List<MovieResponse> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {
}