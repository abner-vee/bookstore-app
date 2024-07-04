package com.bookstore.controller;

import com.bookstore.service.AuthorService;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.AuthorVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;


    @PostMapping("/{id}")
    public ResponseEntity<APIResponse<AuthorVO>> getByID(@PathVariable Long id){
        APIResponse<AuthorVO> apiResponse = authorService.getAuthorById(id);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("")
    public ResponseEntity<APIResponse<List<AuthorVO>>> getAllAuthors(){
        APIResponse<List<AuthorVO>> apiResponse = authorService.getAllAuthors();
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteAuthor(@PathVariable Long id){
        APIResponse<Void> apiResponse = authorService.deleteAuthorById(id);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
