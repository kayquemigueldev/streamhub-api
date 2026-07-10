package com.kayque.streamhubapi.repository;

import com.kayque.streamhubapi.entity.Movie;
import com.kayque.streamhubapi.entity.Review;
import com.kayque.streamhubapi.entity.Series;
import com.kayque.streamhubapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovie(Movie movie);

    List<Review> findBySeries(Series series);

    Optional<Review> findByUserAndMovie(User user, Movie movie);

    Optional<Review> findByUserAndSeries(User user, Series series);
}