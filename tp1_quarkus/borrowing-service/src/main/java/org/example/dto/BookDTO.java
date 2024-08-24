package org.example.dto;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String isbn;
    private String authorName;
    //private AuthorDTO author;
}
