package com.kayque.streamhubapi.mapper;

import com.kayque.streamhubapi.dto.CreateMovieRequest;
import com.kayque.streamhubapi.dto.GenreResponse;
import com.kayque.streamhubapi.dto.MovieResponse;
import com.kayque.streamhubapi.entity.Genre;
import com.kayque.streamhubapi.entity.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MovieMapper {

    public Movie toEntity(CreateMovieRequest request, Genre genre) {
        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseYear(request.releaseYear())
                .durationMinutes(request.durationMinutes())
                .ageRating(request.ageRating())
                .posterUrl(request.posterUrl())
                .bannerUrl(request.bannerUrl())
                .releaseDate(request.releaseDate())
                .genre(genre)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public MovieResponse toResponse(Movie movie) {
        GenreResponse genreResponse = null;

        if (movie.getGenre() != null) {
            genreResponse = new GenreResponse(
                    movie.getGenre().getId(),
                    movie.getGenre().getName()
            );
        }

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
                genreResponse,
                movie.getCreatedAt(),
                movie.getUpdatedAt()
        );
    }
}