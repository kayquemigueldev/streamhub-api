package com.kayque.streamhubapi.dto;

public record DashboardResponse(
        long totalUsers,
        long totalMovies,
        long totalSeries,
        long totalGenres,
        long totalReviews,
        long totalFavorites,
        long totalWatchlistItems
) {
}