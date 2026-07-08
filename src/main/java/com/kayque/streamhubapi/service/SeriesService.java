package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.CreateSeriesRequest;
import com.kayque.streamhubapi.dto.SeriesResponse;
import com.kayque.streamhubapi.entity.Genre;
import com.kayque.streamhubapi.entity.Series;
import com.kayque.streamhubapi.mapper.SeriesMapper;
import com.kayque.streamhubapi.repository.GenreRepository;
import com.kayque.streamhubapi.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kayque.streamhubapi.dto.UpdateSeriesRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository repository;
    private final GenreRepository genreRepository;
    private final SeriesMapper mapper;

    public SeriesResponse create(CreateSeriesRequest request) {
        Genre genre = genreRepository.findById(request.genreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        Series series = mapper.toEntity(request, genre);

        return mapper.toResponse(repository.save(series));
    }

    public List<SeriesResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public SeriesResponse findById(Long id) {
        Series series = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        return mapper.toResponse(series);
    }
    @Transactional
    public SeriesResponse update(Long id, UpdateSeriesRequest request) {

        Series series = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        Genre genre = genreRepository.findById(request.genreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        series.setTitle(request.title());
        series.setDescription(request.description());
        series.setReleaseYear(request.releaseYear());
        series.setSeasonsCount(request.seasonsCount());
        series.setEpisodesCount(request.episodesCount());
        series.setAgeRating(request.ageRating());
        series.setPosterUrl(request.posterUrl());
        series.setBannerUrl(request.bannerUrl());
        series.setReleaseDate(request.releaseDate());
        series.setGenre(genre);

        return mapper.toResponse(repository.save(series));
    }

    @Transactional
    public void delete(Long id) {

        Series series = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        repository.delete(series);
    }
}