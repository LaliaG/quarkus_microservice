package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingDTO {

    private Long id;
    private Long bookId;
    private Long userId;
    /*
    private UserDTO user;
    private BookDTO book;*/
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
}
