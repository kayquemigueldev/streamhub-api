package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.CreateReviewRequest;
import com.kayque.streamhubapi.dto.ReviewResponse;
import com.kayque.streamhubapi.dto.UpdateReviewRequest;
import com.kayque.streamhubapi.entity.Movie;
import com.kayque.streamhubapi.entity.Review;
import com.kayque.streamhubapi.entity.Series;
import com.kayque.streamhubapi.entity.User;
import com.kayque.streamhubapi.mapper.ReviewMapper;
import com.kayque.streamhubapi.repository.MovieRepository;
import com.kayque.streamhubapi.repository.ReviewRepository;
import com.kayque.streamhubapi.repository.SeriesRepository;
import com.kayque.streamhubapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;
    private final ReviewMapper reviewMapper;

    public ReviewResponse createMovieReview(
            Long movieId,
            CreateReviewRequest request,
            String email
    ) {
        User user = findUser(email);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        reviewRepository.findByUserAndMovie(user, movie)
                .ifPresent(review -> {
                    throw new RuntimeException("Movie already reviewed");
                });

        Review review = Review.builder()
                .rating(request.rating())
                .comment(request.comment())
                .user(user)
                .movie(movie)
                .build();

        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    public ReviewResponse createSeriesReview(
            Long seriesId,
            CreateReviewRequest request,
            String email
    ) {
        User user = findUser(email);

        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        reviewRepository.findByUserAndSeries(user, series)
                .ifPresent(review -> {
                    throw new RuntimeException("Series already reviewed");
                });

        Review review = Review.builder()
                .rating(request.rating())
                .comment(request.comment())
                .user(user)
                .series(series)
                .build();

        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    public List<ReviewResponse> findByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        return reviewRepository.findByMovie(movie)
                .stream()
                .map(reviewMapper::toResponse)
                .toList();
    }

    public List<ReviewResponse> findBySeries(Long seriesId) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        return reviewRepository.findBySeries(series)
                .stream()
                .map(reviewMapper::toResponse)
                .toList();
    }

    public ReviewResponse update(
            Long id,
            UpdateReviewRequest request,
            String email
    ) {
        User user = findUser(email);

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Review not found");
        }

        review.setRating(request.rating());
        review.setComment(request.comment());

        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    public void delete(Long id, String email) {
        User user = findUser(email);

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Review not found");
        }

        reviewRepository.delete(review);
    }

    private User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}