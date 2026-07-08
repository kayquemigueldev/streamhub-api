package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.FavoriteResponse;
import com.kayque.streamhubapi.entity.Favorite;
import com.kayque.streamhubapi.entity.Movie;
import com.kayque.streamhubapi.entity.Series;
import com.kayque.streamhubapi.entity.User;
import com.kayque.streamhubapi.mapper.MovieMapper;
import com.kayque.streamhubapi.mapper.SeriesMapper;
import com.kayque.streamhubapi.repository.FavoriteRepository;
import com.kayque.streamhubapi.repository.MovieRepository;
import com.kayque.streamhubapi.repository.SeriesRepository;
import com.kayque.streamhubapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;
    private final MovieMapper movieMapper;
    private final SeriesMapper seriesMapper;

    public FavoriteResponse addMovieToFavorites(Long movieId, String email) {
        User user = findUserByEmail(email);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Favorite favorite = Favorite.builder()
                .user(user)
                .movie(movie)
                .build();

        return toResponse(favoriteRepository.save(favorite));
    }

    public FavoriteResponse addSeriesToFavorites(Long seriesId, String email) {
        User user = findUserByEmail(email);

        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        Favorite favorite = Favorite.builder()
                .user(user)
                .series(series)
                .build();

        return toResponse(favoriteRepository.save(favorite));
    }

    public List<FavoriteResponse> findAllByUser(String email) {
        User user = findUserByEmail(email);

        return favoriteRepository.findAll()
                .stream()
                .filter(favorite -> favorite.getUser().getId().equals(user.getId()))
                .map(this::toResponse)
                .toList();
    }

    public void delete(Long id, String email) {
        User user = findUserByEmail(email);

        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));

        if (!favorite.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Favorite not found");
        }

        favoriteRepository.delete(favorite);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private FavoriteResponse toResponse(Favorite favorite) {
        return new FavoriteResponse(
                favorite.getId(),
                favorite.getMovie() == null ? null : movieMapper.toResponse(favorite.getMovie()),
                favorite.getSeries() == null ? null : seriesMapper.toResponse(favorite.getSeries()),
                favorite.getCreatedAt()
        );
    }
}