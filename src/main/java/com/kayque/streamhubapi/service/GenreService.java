package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.CreateGenreRequest;
import com.kayque.streamhubapi.dto.GenreResponse;
import com.kayque.streamhubapi.entity.Genre;
import com.kayque.streamhubapi.mapper.GenreMapper;
import com.kayque.streamhubapi.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository repository;
    private final GenreMapper mapper;

    public GenreResponse create(CreateGenreRequest request) {

        if (repository.existsByName(request.name())) {
            throw new RuntimeException("Genre already exists");
        }

        Genre genre = mapper.toEntity(request);

        return mapper.toResponse(repository.save(genre));
    }

    public List<GenreResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public GenreResponse findById(Long id) {

        Genre genre = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        return mapper.toResponse(genre);
    }
}