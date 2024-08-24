package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.BookDTO;
import org.example.dto.UserDTO;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime borrowDate;

    @Column(nullable = true)
    private LocalDateTime returnDate;

    @Transient
    private UserDTO userDTO;

    @Transient
    private BookDTO bookDTO;

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @Transient
    private UserDTO userDTO;

    @Transient
    private BookDTO bookDTO;*/
}
