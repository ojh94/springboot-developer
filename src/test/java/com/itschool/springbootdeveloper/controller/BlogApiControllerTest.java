package com.itschool.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itschool.springbootdeveloper.domain.Article;
import com.itschool.springbootdeveloper.dto.AddArticleRequest;
import com.itschool.springbootdeveloper.dto.UpdateArticleRequest;
import com.itschool.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.security.Principal;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest extends MockMvcTest<Article,Long> {


    @DisplayName("addArticle : 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        // 객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(MockMvcResultMatchers.status().isCreated());

        List<Article> articles = baseRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAllArticle : 블로그 글 목록 조회에 성공한다.")
    @Test
    public void findAllArticle() throws Exception {
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        Long beforeCount = baseRepository.count();

        baseRepository.save(Article.builder().title(title).content(content).build());


        Long afterCount = baseRepository.count();

        // When : 테스트를 위한 수행
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(title));

        assertThat(beforeCount+1).isEqualTo(afterCount);


    }

    @DisplayName("findArticle : 블로그 글 조회에 성공한다.")
    @Test
    public void findArticle() throws Exception{
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Long beforeCount = baseRepository.count();

        Article article = baseRepository.save(Article.builder().title(title).content(content).build());


        Long afterCount = baseRepository.count();

        // When : 테스트를 위한 수행
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url, article.getId()).accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title));

        assertThat(beforeCount+1).isEqualTo(afterCount);


    }

    @DisplayName("deleteArticle : 블로그 글 삭제에 성공한다.")
    @Test
    public void deleteArticle() throws Exception{
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article article = baseRepository.save(Article.builder().title(title).content(content).build());

        Long beforeCount = baseRepository.count();

        mockMvc.perform(MockMvcRequestBuilders.delete(url, article.getId())).andExpect(MockMvcResultMatchers.status().isOk());

        Long afterCount = baseRepository.count();

        List<Article> articles = baseRepository.findAll();

        assertThat(articles.isEmpty());
        assertThat(beforeCount-1).isEqualTo(afterCount);

    }

    @DisplayName("updateArticle : 블고그 글 수정에 성공한다.")
    @Test
    public void updateArticle() throws Exception{
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article saveArticle = baseRepository.save(Article.builder().title(title).content(content).build());

        Long beforeCount = baseRepository.count();

        final String newtitle = "new title";
        final String newcontent = "new content";

        UpdateArticleRequest request = new UpdateArticleRequest(newtitle,newcontent);


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(url,saveArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        Long afterCount = baseRepository.count();

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Article article = baseRepository.findById(saveArticle.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newtitle);
        assertThat(article.getContent()).isEqualTo(newcontent);
        assertThat(beforeCount).isEqualTo(afterCount);

    }
}