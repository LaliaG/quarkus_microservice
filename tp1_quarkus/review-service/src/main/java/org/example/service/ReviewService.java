package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.dto.ReviewDTO;
import org.example.entity.Review;
import org.example.repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReviewService {

    @Inject
    ReviewRepository reviewRepository;

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.listAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByUserName(String userName) {
        return reviewRepository.find("user.name", userName).list()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByBookIsbn(String isbn) {
        return reviewRepository.find("book.isbn", isbn).list()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByRating(int rating) {
        return reviewRepository.find("rating", rating).list()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ReviewDTO mapToDto(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .userName(review.getUser().getName())
                .bookIsbn(review.getBook().getIsbn())
                .build();
    }

    private Review mapToEntity(ReviewDTO reviewDTO) {
        return Review.builder()
                .comment(reviewDTO.getComment())
                .rating(reviewDTO.getRating())
                .build();
    }
}
