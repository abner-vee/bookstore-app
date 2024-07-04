package com.bookstore.dto;

import com.bookstore.entity.Author;
import com.bookstore.entity.Genre;
import com.bookstore.vo.AuthorVO;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDTO {
    private String title;
    private String ISBN;
    private double price;
    private LocalDateTime publicationDate;
    private Long authorId;
    private Long genreId;
}
