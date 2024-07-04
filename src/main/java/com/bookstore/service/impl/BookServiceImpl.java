package com.bookstore.service.impl;

import com.bookstore.exceptions.APIException;
import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.Genre;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.GenreRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.BookService;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.AuthorVO;
import com.bookstore.vo.BookVO;
import com.bookstore.vo.GenreVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final ModelMapper mapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    @Override
    public APIResponse<List<BookVO>> getAllBooks() {
        List<BookVO> bookVOList =  bookRepository.findAll()
                .stream()
                .map(book -> {
                    BookVO bookVO =  mapper.map(book, BookVO.class);
                    GenreVO genreVO = mapper.map(book.getGenre(), GenreVO.class);
                    AuthorVO authorVO = mapper.map(book.getAuthor(), AuthorVO.class);
                    bookVO.setAuthor(authorVO);
                    bookVO.setGenre(genreVO);
                    return bookVO;
                }).toList();
        return APIResponse.<List<BookVO>>builder()
                .data(bookVOList)
                .statusCode(200)
                .message("Success")
                .build();
    }

    @Override
    public APIResponse<BookVO> getBookById(Long id) {
        BookVO bookVOs =  bookRepository.findById(id)
                .map(book -> {
                    BookVO bookVO =  mapper.map(book, BookVO.class);
                    GenreVO genreVO = mapper.map(book.getGenre(), GenreVO.class);
                    AuthorVO authorVO = mapper.map(book.getAuthor(), AuthorVO.class);
                    bookVO.setAuthor(authorVO);
                    bookVO.setGenre(genreVO);
                    return bookVO;
                }).orElse(null);
        return APIResponse.<BookVO>builder()
                .data(bookVOs)
                .statusCode(200)
                .message("Success")
                .build();
    }

    @Override
    public APIResponse<BookVO> saveBook(BookDTO request) {
        Optional<Genre> genreOptional = genreRepository.findByName(request.getGenre().getName());
        if (genreOptional.isPresent()){
            Genre genre = genreOptional.get();
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<Author> authorOptional = userRepository.findAuthorByEmail(email);
            if (authorOptional.isEmpty()){
                throw APIException.builder()
                        .statusCode(404)
                        .message("Author account doesn't exist")
                        .build();
            }
            Author author = authorOptional.get();
            Optional<Book> bookOptional = bookRepository.findBookByTitleAndAuthorAndGenre(request.getTitle(), author, genre);
            if(bookOptional.isPresent()){
                throw APIException.builder()
                        .statusCode(409)
                        .message("Book Already exist")
                        .build();
            }
            Book book = mapper.map(request, Book.class);
            book.setGenre(genre);
            book.setAuthor(author);

            Book savedBook = bookRepository.save(book);

            BookVO bookVO = mapper.map(savedBook, BookVO.class);
            GenreVO genreVO = mapper.map(book.getGenre(), GenreVO.class);
            AuthorVO authorVO = mapper.map(book.getAuthor(), AuthorVO.class);
            bookVO.setAuthor(authorVO);
            bookVO.setGenre(genreVO);
            return APIResponse.<BookVO>builder()
                    .data(bookVO)
                    .statusCode(200)
                    .message("Success")
                    .build();

        }
        throw APIException.builder()
                .statusCode(404)
                .message("Genre doesn't exist")
                .build();

    }

    @Override
    public APIResponse<Void> deleteBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()){
            throw APIException.builder()
                    .statusCode(404)
                    .message("Author Doesn't exist")
                    .build();
        }
        Book book = bookOptional.get();
        bookRepository.delete(book);
        return APIResponse.<Void>builder()
                .data(null)
                .statusCode(200)
                .message("Success")
                .build();
    }
}
