package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.entity.Author;
import org.example.repository.AuthorRepository;

import java.util.List;

@ApplicationScoped
public class AuthorService {

    @Inject
    AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.listAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public void addAuthor(Author author) {
        authorRepository.persist(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    /*@Inject
    AuthorRepository authorRepository;

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.listAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public AuthorDto getAuthorById(Long id) {
        return authorRepository.findByIdOptional(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new WebApplicationException("Author not found", 404));
    }

    public void addAuthor(AuthorDto authorDto) {
        Author author = mapToEntity(authorDto);
        authorRepository.persist(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    private AuthorDto mapToDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .biography(author.getBiography())
                .birthDate(author.getBirthDate())
                .build();
    }

    private Author mapToEntity(AuthorDto authorDto) {
        return Author.builder()
                .name(authorDto.getName())
                .biography(authorDto.getBiography())
                .birthDate(authorDto.getBirthDate())
                .build();
    }*/
}
