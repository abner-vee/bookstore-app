package com.bookstore.repository;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByTitleAndAuthorAndGenre(String title, Author author, Genre genre);
    void deleteAllByAuthor(Author author);
}
