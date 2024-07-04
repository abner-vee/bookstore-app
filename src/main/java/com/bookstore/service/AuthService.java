package com.bookstore.service;

import com.bookstore.dto.AuthorDTO;
import com.bookstore.dto.LoginDTO;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.AuthorVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService {
    APIResponse<AuthorVO> addAuthor(AuthorDTO request);
    APIResponse<AuthorVO> login(LoginDTO request);
}
