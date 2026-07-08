package com.kayque.streamhubapi.mapper;

import com.kayque.streamhubapi.dto.GenreResponse;
import com.kayque.streamhubapi.dto.CreateSeriesRequest;
import com.kayque.streamhubapi.dto.SeriesResponse;
import com.kayque.streamhubapi.entity.Genre;
import com.kayque.streamhubapi.entity.Series;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SeriesMapper {

    public Series toEntity(CreateSeriesRequest request, Genre genre) {
        return Series.builder()
                .title(request.title())
                .description(request.description())
                .releaseYear(request.releaseYear())
                .seasonsCount(request.seasonsCount())
                .episodesCount(request.episodesCount())
                .ageRating(request.ageRating())
                .posterUrl(request.posterUrl())
                .bannerUrl(request.bannerUrl())
                .releaseDate(request.releaseDate())
                .genre(genre)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public SeriesResponse toResponse(Series series) {
        GenreResponse genreResponse = null;

        if (series.getGenre() != null) {
            genreResponse = new GenreResponse(
                    series.getGenre().getId(),
                    series.getGenre().getName()
            );
        }

        return new SeriesResponse(
                series.getId(),
                series.getTitle(),
                series.getDescription(),
                series.getReleaseYear(),
                series.getSeasonsCount(),
                series.getEpisodesCount(),
                series.getAgeRating(),
                series.getPosterUrl(),
                series.getBannerUrl(),
                series.getReleaseDate(),
                genreResponse,
                series.getCreatedAt(),
                series.getUpdatedAt()
        );
    }
}