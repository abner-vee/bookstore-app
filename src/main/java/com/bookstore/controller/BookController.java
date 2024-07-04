package com.bookstore.controller;

import com.bookstore.dto.BookDTO;
import com.bookstore.service.BookService;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.BookVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public ResponseEntity<APIResponse<List<BookVO>>> getAllBooks(){
        APIResponse<List<BookVO>> apiResponse = bookService.getAllBooks();
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<BookVO>> getById(@PathVariable Long id){
        APIResponse<BookVO> apiResponse = bookService.getBookById(id);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @PostMapping("/")
    public ResponseEntity<APIResponse<BookVO>> createBook(@RequestBody BookDTO request){
        APIResponse<BookVO> apiResponse = bookService.saveBook(request);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteBook(@PathVariable Long id){
        APIResponse<Void> apiResponse = bookService.deleteBook(id);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
