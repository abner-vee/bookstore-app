package com.bookstore.service.impl;

import com.bookstore.config.JwtService;
import com.bookstore.dto.AuthorDTO;
import com.bookstore.dto.LoginDTO;
import com.bookstore.entity.Author;
import com.bookstore.enums.Role;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.AuthService;
import com.bookstore.vo.APIResponse;
import com.bookstore.vo.AuthorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public APIResponse<AuthorVO> addAuthor(AuthorDTO request) {
        var author = mapper.map(request, Author.class);
        author.setPassword(passwordEncoder.encode(request.getPassword()));
        author.setRole(Role.USER);
        AuthorVO savedAuthor = mapper.map(userRepository.save(author), AuthorVO.class);
        return APIResponse.<AuthorVO>builder()
                .data(savedAuthor)
                .statusCode(200)
                .message("Success")
                .build();

    }

    @Override
    public APIResponse<AuthorVO> login(LoginDTO request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Author user = (Author) auth.getPrincipal();
        AuthorVO authorVO =  mapper.map(user, AuthorVO.class);
        String token = jwtService.generateToken(user);
        authorVO.setToken(token);
        return APIResponse.<AuthorVO>builder()
                .data(authorVO)
                .statusCode(200)
                .message("Success")
                .build();
    }

}
