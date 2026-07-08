package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.CreateMovieRequest;
import com.kayque.streamhubapi.dto.MovieResponse;
import com.kayque.streamhubapi.entity.Movie;
import com.kayque.streamhubapi.mapper.MovieMapper;
import com.kayque.streamhubapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final MovieMapper mapper;

    public MovieResponse create(CreateMovieRequest request) {
        Movie movie = mapper.toEntity(request);

        Movie savedMovie = repository.save(movie);

        return mapper.toResponse(savedMovie);
    }

    public List<MovieResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MovieResponse findById(Long id) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        return mapper.toResponse(movie);
    }
}