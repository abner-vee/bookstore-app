package com.bookstore.service.impl;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.exceptions.APIException;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.AuthorService;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.AuthorVO;
import com.bookstore.vo.BookVO;
import com.bookstore.vo.GenreVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ModelMapper mapper;
    @Override
    public APIResponse<List<AuthorVO>> getAllAuthors() {
        List<AuthorVO> authorVOList =  userRepository.findAll()
                .stream()
                .map(author -> mapper.map(author, AuthorVO.class)).toList();
        return APIResponse.<List<AuthorVO>>builder()
                .data(authorVOList)
                .statusCode(200)
                .message("Success")
                .build();
    }

    @Override
    public APIResponse<AuthorVO> getAuthorById(Long authorId) {
        AuthorVO authorVO =  userRepository.findById(authorId)
                .map(author -> mapper.map(author, AuthorVO.class)).orElse(null);
        return APIResponse.<AuthorVO>builder()
                .data(authorVO)
                .statusCode(200)
                .message("Success")
                .build();
    }

    @Override
    public APIResponse<Void> deleteAuthorById(Long id) {
        Optional<Author> authorOptional = userRepository.findById(id);
        if (authorOptional.isEmpty()){
            throw APIException.builder()
                    .statusCode(404)
                    .message("Author Doesn't exist")
                    .build();
        }
        Author author = authorOptional.get();
        bookRepository.deleteAllByAuthor(author);
        userRepository.delete(author);
        return APIResponse.<Void>builder()
                .data(null)
                .statusCode(200)
                .message("Success")
                .build();
    }
}
