package com.itschool.springbootdeveloper.dto;

import com.itschool.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// DTO : Data Transfer Object
// 계층끼리 데이터를 교환하기 위해 사용하는 객체임
// 단순하게 데이터를 전달하기 위해 사용하는 전달자 역할 (비즈니스 조직 작성 X)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity() {

        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

    @Override
    public String toString() {
        return "AddArticleRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}