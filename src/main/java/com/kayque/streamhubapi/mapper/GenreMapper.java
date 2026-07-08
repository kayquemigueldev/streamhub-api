package com.kayque.streamhubapi.mapper;

import com.kayque.streamhubapi.dto.CreateGenreRequest;
import com.kayque.streamhubapi.dto.GenreResponse;
import com.kayque.streamhubapi.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public Genre toEntity(CreateGenreRequest request) {
        return Genre.builder()
                .name(request.name())
                .build();
    }

    public GenreResponse toResponse(Genre genre) {
        return new GenreResponse(
                genre.getId(),
                genre.getName()
        );
    }
}