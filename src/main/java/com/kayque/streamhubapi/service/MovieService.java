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
import com.kayque.streamhubapi.dto.PageMovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    public PageMovieResponse search(
            String title,
            Long genreId,
            Integer releaseYear,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        Sort.Direction sortDirection =
                direction.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortDirection, sortBy)
        );

        Page<Movie> movies;

        if (title != null && !title.isBlank()) {
            movies = repository.findByTitleContainingIgnoreCase(title, pageable);
        } else if (genreId != null) {
            movies = repository.findByGenreId(genreId, pageable);
        } else if (releaseYear != null) {
            movies = repository.findByReleaseYear(releaseYear, pageable);
        } else {
            movies = repository.findAll(pageable);
        }

        return new PageMovieResponse(
                movies.getContent()
                        .stream()
                        .map(mapper::toResponse)
                        .toList(),
                movies.getNumber(),
                movies.getSize(),
                movies.getTotalElements(),
                movies.getTotalPages(),
                movies.isFirst(),
                movies.isLast()
        );
    }

}