package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.Borrowing;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class BorrowingRepository implements PanacheRepository<Borrowing> {

    public List<Borrowing> findByUserId(Long userId) {
        return list("userId", userId);
    }

    public List<Borrowing> findByBookId(Long bookId) {
        return list("bookId", bookId);
    }

    public List<Borrowing> findBorrowingsInPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return list("borrowDate >= ?1 and borrowDate <= ?2", startDate, endDate);
    }

    public boolean isBookBorrowed(Long bookId) {
        return count("bookId = ?1 and returnDate is null", bookId) > 0;
    }
}
