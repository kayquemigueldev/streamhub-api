package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.DashboardResponse;
import com.kayque.streamhubapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;
    private final GenreRepository genreRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;
    private final WatchlistRepository watchlistRepository;

    public DashboardResponse getDashboard() {
        return new DashboardResponse(
                userRepository.count(),
                movieRepository.count(),
                seriesRepository.count(),
                genreRepository.count(),
                reviewRepository.count(),
                favoriteRepository.count(),
                watchlistRepository.count()
        );
    }
}