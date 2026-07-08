package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.CreateMovieRequest;
import com.kayque.streamhubapi.dto.MovieResponse;
import com.kayque.streamhubapi.entity.Genre;
import com.kayque.streamhubapi.entity.Movie;
import com.kayque.streamhubapi.mapper.MovieMapper;
import com.kayque.streamhubapi.repository.GenreRepository;
import com.kayque.streamhubapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kayque.streamhubapi.dto.UpdateMovieRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final GenreRepository genreRepository;
    private final MovieMapper mapper;

    public MovieResponse create(CreateMovieRequest request) {
        Genre genre = genreRepository.findById(request.genreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        Movie movie = mapper.toEntity(request, genre);

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

    public MovieResponse update(Long id, UpdateMovieRequest request) {

        Movie movie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Genre genre = genreRepository.findById(request.genreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        movie.setTitle(request.title());
        movie.setDescription(request.description());
        movie.setReleaseYear(request.releaseYear());
        movie.setDurationMinutes(request.durationMinutes());
        movie.setAgeRating(request.ageRating());
        movie.setPosterUrl(request.posterUrl());
        movie.setBannerUrl(request.bannerUrl());
        movie.setReleaseDate(request.releaseDate());
        movie.setGenre(genre);
        movie.setUpdatedAt(java.time.LocalDateTime.now());

        return mapper.toResponse(repository.save(movie));
    }

    public void delete(Long id) {

        Movie movie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        repository.delete(movie);
    }

}