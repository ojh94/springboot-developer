package com.itschool.springbootdeveloper.dto;

import com.itschool.springbootdeveloper.domain.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {

    private final Long id;
    private final String title;
    private final String countent;

    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.countent = article.getContent();
    }
}
