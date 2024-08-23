package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.Review;

@ApplicationScoped
public class ReviewRepository implements PanacheRepository<Review> {
}
