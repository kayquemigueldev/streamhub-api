package com.kayque.streamhubapi.repository;

import com.kayque.streamhubapi.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}