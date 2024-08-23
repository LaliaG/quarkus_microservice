package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.example.client.AuthorServiceClient;
import org.example.dto.AuthorDTO;
import org.example.entity.Book;
import org.example.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    @Inject
    AuthorServiceClient authorServiceClient;

    public List<Book> getAllBooks() {
        return bookRepository.listAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void addBook(Book book) {
        AuthorDTO authorDto = authorServiceClient.getAuthorById(book.getAuthor().getId());
        bookRepository.persist(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
