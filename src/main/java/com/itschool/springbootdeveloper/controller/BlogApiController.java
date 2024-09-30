package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.domain.Article;
import com.itschool.springbootdeveloper.dto.AddArticleRequest;
import com.itschool.springbootdeveloper.dto.ArticleResponse;
import com.itschool.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class BlogApiController {

    private final BlogService blogService;

    @GetMapping("")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        /*List<Article> articlesList = blogService.findAll();
        List<ArticleResponse> articles = new ArrayList<>();

        for (Article article : articlesList) {
            articles.add(new ArticleResponse(article));
        }*/

        List<ArticleResponse> articles = blogService.findAll().stream().map(article -> new ArticleResponse(article)).toList();

        //List<ArticleResponse> articles = blogService.findAll().stream().map()

        return ResponseEntity.ok().body(articles);


    }

    @PostMapping("")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article saveArticle = blogService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(saveArticle);
    }

    @PutMapping("/{id}")
    public String method2(@PathVariable Long id) {
        return "수정 테스트";
    }

    @DeleteMapping("/{id}")
    public String method3(@PathVariable Long id) {
        return "삭제 테스트";
    }
}
