package com.kayque.streamhubapi.repository;

import com.kayque.streamhubapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}