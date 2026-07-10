package com.kayque.streamhubapi.mapper;

import com.kayque.streamhubapi.dto.ReviewResponse;
import com.kayque.streamhubapi.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewResponse toResponse(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getUser().getId(),
                review.getUser().getFullName(),
                review.getMovie() == null ? null : review.getMovie().getId(),
                review.getMovie() == null ? null : review.getMovie().getTitle(),
                review.getSeries() == null ? null : review.getSeries().getId(),
                review.getSeries() == null ? null : review.getSeries().getTitle(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}