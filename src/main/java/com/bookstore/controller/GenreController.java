package com.bookstore.controller;

import com.bookstore.dto.BookDTO;
import com.bookstore.dto.GenreDTO;
import com.bookstore.service.GenreService;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.BookVO;
import com.bookstore.vo.GenreVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    @GetMapping("/")
    public ResponseEntity<APIResponse<List<GenreVO>>> getAllGenre(){
        APIResponse<List<GenreVO>> apiResponse = genreService.getAllGenre();
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<GenreVO>> getGenreById(@PathVariable Long id){
        APIResponse<GenreVO> apiResponse = genreService.getGenreById(id);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @PostMapping("/")
    public ResponseEntity<APIResponse<GenreVO>> saveGenre(@RequestBody GenreDTO request){
        APIResponse<GenreVO> apiResponse = genreService.saveGenre(request);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteGenreId(@PathVariable Long id){
        APIResponse<Void> apiResponse = genreService.deleteGenreId(id);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
