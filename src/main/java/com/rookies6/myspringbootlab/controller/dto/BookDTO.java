package com.rookies6.myspringbootlab.controller.dto;

import com.rookies6.myspringbootlab.entity.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * [제출2-3] 클라이언트와 서버 간 데이터 전송용 DTO 모음.
 * 내부 static 클래스로 3가지 DTO 를 정의한다.
 */
public class BookDTO {

    /** 도서 생성(POST) 요청 DTO - 모든 필드 필수 */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookCreateRequest {

        @NotBlank(message = "Book title is required")
        @Size(max = 200, message = "Book title cannot exceed 200 characters")
        private String title;

        @NotBlank(message = "Author name is required")
        @Size(max = 100, message = "Author name cannot exceed 100 characters")
        private String author;

        @NotBlank(message = "ISBN is required")
        @Size(max = 20, message = "ISBN cannot exceed 20 characters")
        private String isbn;

        @NotNull(message = "Price is required")
        @PositiveOrZero(message = "Price must be positive or zero")
        private Integer price;

        @NotNull(message = "Publish date is required")
        private LocalDate publishDate;

        public Book toEntity() {
            return Book.builder()
                    .title(title)
                    .author(author)
                    .isbn(isbn)
                    .price(price)
                    .publishDate(publishDate)
                    .build();
        }
    }

    /**
     * 도서 수정(PUT) 요청 DTO - 부분 수정.
     * 저자(author), 가격(price), 제목(title), 출판일자(publishDate) 만 수정 대상.
     * 모든 필드가 선택값이며 null 이면 기존 값을 유지한다.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookUpdateRequest {

        @Size(max = 200, message = "Book title cannot exceed 200 characters")
        private String title;

        @Size(max = 100, message = "Author name cannot exceed 100 characters")
        private String author;

        @PositiveOrZero(message = "Price must be positive or zero")
        private Integer price;

        private LocalDate publishDate;
    }

    /** 클라이언트로 반환되는 응답 DTO */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookResponse {

        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;

        public static BookResponse fromEntity(Book book) {
            return BookResponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .isbn(book.getIsbn())
                    .price(book.getPrice())
                    .publishDate(book.getPublishDate())
                    .build();
        }
    }
}
