package com.bookstore.vo;

import com.bookstore.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookVO extends BaseVO{
    private String title;
    private String ISBN;
    private double price;
    private LocalDateTime publicationDate;
    private AuthorVO author;
    private GenreVO genre;
}
