package com.kayque.streamhubapi.repository;

import com.kayque.streamhubapi.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );

    Page<Movie> findByGenreId(
            Long genreId,
            Pageable pageable
    );

    Page<Movie> findByReleaseYear(
            Integer releaseYear,
            Pageable pageable
    );
}