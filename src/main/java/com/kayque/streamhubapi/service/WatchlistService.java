package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.WatchlistResponse;
import com.kayque.streamhubapi.entity.*;
import com.kayque.streamhubapi.mapper.MovieMapper;
import com.kayque.streamhubapi.mapper.SeriesMapper;
import com.kayque.streamhubapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;
    private final MovieMapper movieMapper;
    private final SeriesMapper seriesMapper;

    public WatchlistResponse addMovie(Long movieId, String email) {
        User user = findUser(email);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Watchlist item = Watchlist.builder()
                .user(user)
                .movie(movie)
                .build();

        return toResponse(watchlistRepository.save(item));
    }

    public WatchlistResponse addSeries(Long seriesId, String email) {
        User user = findUser(email);

        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        Watchlist item = Watchlist.builder()
                .user(user)
                .series(series)
                .build();

        return toResponse(watchlistRepository.save(item));
    }

    public List<WatchlistResponse> findAll(String email) {
        User user = findUser(email);

        return watchlistRepository.findAll()
                .stream()
                .filter(item -> item.getUser().getId().equals(user.getId()))
                .map(this::toResponse)
                .toList();
    }

    public void delete(Long id, String email) {
        User user = findUser(email);

        Watchlist item = watchlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Watchlist item not found"));

        if (!item.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Watchlist item not found");
        }

        watchlistRepository.delete(item);
    }

    private User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private WatchlistResponse toResponse(Watchlist item) {
        return new WatchlistResponse(
                item.getId(),
                item.getMovie() == null ? null : movieMapper.toResponse(item.getMovie()),
                item.getSeries() == null ? null : seriesMapper.toResponse(item.getSeries()),
                item.getCreatedAt()
        );
    }
}