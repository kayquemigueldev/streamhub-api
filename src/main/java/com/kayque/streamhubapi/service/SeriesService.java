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
}