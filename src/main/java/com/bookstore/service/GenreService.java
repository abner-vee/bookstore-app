package com.bookstore.service;

import com.bookstore.dto.BookDTO;
import com.bookstore.dto.GenreDTO;
import com.bookstore.entity.Genre;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.BookVO;
import com.bookstore.vo.GenreVO;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    APIResponse<List<GenreVO>> getAllGenre();

    APIResponse<GenreVO> getGenreById(Long id);

    APIResponse<GenreVO> saveGenre(GenreDTO request);

    APIResponse<Void> deleteGenreId(Long id);
}
