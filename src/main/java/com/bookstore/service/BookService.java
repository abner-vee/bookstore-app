package com.bookstore.service;

import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.BookVO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    APIResponse<List<BookVO>> getAllBooks();
    APIResponse<BookVO> getBookById(Long id);

    APIResponse<BookVO> saveBook(BookDTO request);

    APIResponse<Void> deleteBook(Long id);
}
