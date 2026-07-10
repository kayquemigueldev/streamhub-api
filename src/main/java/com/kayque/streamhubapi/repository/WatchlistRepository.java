package com.kayque.streamhubapi.repository;

import com.kayque.streamhubapi.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository
        extends JpaRepository<Watchlist, Long> {
}