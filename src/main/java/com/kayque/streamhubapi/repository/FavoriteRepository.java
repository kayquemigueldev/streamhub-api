package com.kayque.streamhubapi.repository;

import com.kayque.streamhubapi.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}