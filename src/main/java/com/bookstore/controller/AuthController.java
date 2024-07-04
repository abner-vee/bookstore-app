package com.bookstore.controller;

import com.bookstore.dto.AuthorDTO;
import com.bookstore.dto.LoginDTO;
import com.bookstore.service.AuthService;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.AuthorVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse<AuthorVO>> signUp(@RequestBody AuthorDTO authorDTO){
        APIResponse<AuthorVO> apiResponse = authService.addAuthor(authorDTO);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<AuthorVO>> login(@RequestBody LoginDTO loginDTO){
        APIResponse<AuthorVO> apiResponse = authService.login(loginDTO);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
