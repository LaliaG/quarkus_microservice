package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.BookServiceClient;
import org.example.client.UserServiceClient;
import org.example.dto.BookDTO;
import org.example.dto.BorrowingDTO;
import org.example.dto.UserDTO;
import org.example.entity.Borrowing;
import org.example.repository.BorrowingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BorrowingService {

    @Inject
    BorrowingRepository borrowingRepository;

    @Inject
    @RestClient
    BookServiceClient bookServiceClient;

    @Inject
    @RestClient
    UserServiceClient userServiceClient;

    public Borrowing createBorrowing(Long bookId, Long userId) {
        // Vérifier que le livre est disponible
        BookDTO book = bookServiceClient.getBookById(bookId);
        UserDTO user = userServiceClient.getUserById(userId);

        if (isBookBorrowed(bookId)) {
            throw new WebApplicationException("Le livre est déjà emprunté", 400);
        }

        Borrowing borrowing = Borrowing.builder()
                .bookId(bookId)
                .userId(userId)
                .borrowDate(LocalDateTime.now())
                .build();

        borrowingRepository.persist(borrowing);
        return borrowing;
    }

    public void deleteBorrowing(Long id) {
        borrowingRepository.deleteById(id);
    }

    public List<BorrowingDTO> getCurrentBorrowings() {
        return borrowingRepository.find("returnDate IS NULL")
                .list()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<BorrowingDTO> getBorrowingsByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return borrowingRepository.find("borrowDate BETWEEN ?1 AND ?2", startDate, endDate)
                .list()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<BorrowingDTO> getBorrowingsByUser(Long userId) {
        return borrowingRepository.find("userId", userId)
                .list()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public boolean isBookBorrowed(Long bookId) {
        return borrowingRepository.find("bookId = ?1 AND returnDate IS NULL", bookId)
                .count() > 0;
    }

    private BorrowingDTO mapToDto(Borrowing borrowing) {
        return BorrowingDTO.builder()
                .id(borrowing.getId())
                .bookId(borrowing.getBookId())
                .userId(borrowing.getUserId())
                .borrowDate(borrowing.getBorrowDate())
                .returnDate(borrowing.getReturnDate())
                .build();
    }

    private Borrowing mapToEntity(BorrowingDTO borrowingDTO) {
        return Borrowing.builder()
                .bookId(borrowingDTO.getBookId())
                .userId(borrowingDTO.getUserId())
                .borrowDate(borrowingDTO.getBorrowDate())
                .returnDate(borrowingDTO.getReturnDate())
                .build();
    }
}
