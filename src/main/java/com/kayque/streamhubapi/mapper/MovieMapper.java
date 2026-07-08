package com.kayque.streamhubapi.mapper;

import com.kayque.streamhubapi.dto.CreateMovieRequest;
import com.kayque.streamhubapi.dto.MovieResponse;
import com.kayque.streamhubapi.entity.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MovieMapper {

    public Movie toEntity(CreateMovieRequest request) {
        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseYear(request.releaseYear())
                .durationMinutes(request.durationMinutes())
                .ageRating(request.ageRating())
                .posterUrl(request.posterUrl())
                .bannerUrl(request.bannerUrl())
                .releaseDate(request.releaseDate())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public MovieResponse toResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseYear(),
                movie.getDurationMinutes(),
                movie.getAgeRating(),
                movie.getPosterUrl(),
                movie.getBannerUrl(),
                movie.getReleaseDate(),
                movie.getCreatedAt(),
                movie.getUpdatedAt()
        );
    }
}