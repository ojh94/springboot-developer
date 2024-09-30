package com.itschool.springbootdeveloper.service;

import com.itschool.springbootdeveloper.domain.Article;

import com.itschool.springbootdeveloper.dto.AddArticleRequest;
import com.itschool.springbootdeveloper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article create(AddArticleRequest request) {
        // save() 메서드는 JpaReposi
        return blogRepository.save(request.toEntity());
    }

    // 블로그 글 전체 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }
}
