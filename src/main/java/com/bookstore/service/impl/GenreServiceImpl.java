package com.bookstore.service.impl;

import com.bookstore.exceptions.APIException;
import com.bookstore.dto.GenreDTO;
import com.bookstore.entity.Genre;
import com.bookstore.repository.GenreRepository;
import com.bookstore.service.GenreService;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.GenreVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final ModelMapper mapper;
    @Override
    public APIResponse<List<GenreVO>> getAllGenre() {
        List<GenreVO> genreVOList =  genreRepository.findAll()
                .stream()
                .map(genre -> mapper.map(genre, GenreVO.class)).toList();
        return APIResponse.<List<GenreVO>>builder()
                .data(genreVOList)
                .statusCode(200)
                .message("Success")
                .build();
    }

    @Override
    public APIResponse<GenreVO> getGenreById(Long id) {
        GenreVO genreVO = genreRepository.findById(id)
                .map(genre -> mapper.map(genre, GenreVO.class)).orElse(null);
        return APIResponse.<GenreVO>builder()
                .data(genreVO)
                .statusCode(200)
                .message("Success")
                .build();
    }

    @Override
    public APIResponse<GenreVO> saveGenre(GenreDTO request) {
        Optional<Genre> genreOption = genreRepository.findByName(request.getName());
        if (genreOption.isPresent()){
            throw APIException.builder()
                    .statusCode(409)
                    .message("Genre Already exist")
                    .build();
        }
        Genre genre = mapper.map(request, Genre.class);
        Genre savedGenre = genreRepository.save(genre);
        return APIResponse.<GenreVO>builder()
                .data(mapper.map(savedGenre, GenreVO.class))
                .statusCode(200)
                .message("Success")
                .build();
    }

    @Override
    public APIResponse<Void> deleteGenreId(Long id) {
        Optional<Genre> genreOption = genreRepository.findById(id);
        if (genreOption.isEmpty()){
            throw APIException.builder()
                    .statusCode(404)
                    .message("Genre Doesn't")
                    .build();
        }
        Genre genre = genreOption.get();
        genreRepository.delete(genre);
        return APIResponse.<Void>builder()
                .data(null)
                .statusCode(200)
                .message("Success")
                .build();
    }
}
