package com.bookstore.service;

import com.bookstore.dto.GenreDTO;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.AuthorVO;
import com.bookstore.vo.GenreVO;

import java.util.List;

public interface AuthorService {
    APIResponse<List<AuthorVO>> getAllAuthors();

    APIResponse<AuthorVO> getAuthorById(Long id);

    APIResponse<Void> deleteAuthorById(Long id);
}
