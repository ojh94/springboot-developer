package com.itschool.springbootdeveloper.service;

import com.itschool.springbootdeveloper.domain.Article;
import com.itschool.springbootdeveloper.network.Header;
import com.itschool.springbootdeveloper.network.request.ArticleRequest;
import com.itschool.springbootdeveloper.network.request.UpdateArticleRequest;
import com.itschool.springbootdeveloper.network.response.ArticleResponse;
import com.itschool.springbootdeveloper.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.logging.Handler;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService extends BaseService<ArticleRequest, ArticleResponse, Article> {

    @Override
    protected ArticleResponse response(Article entity){
        return new ArticleResponse(entity);
    }

    @Override
    public Header<ArticleResponse> create(Header<ArticleRequest> request) {
        // save() 메서드는 JpaRepository 의 부모인 CrudRepository 에 선언이 돼있음
        Article newEntity = baseRepository.save(request.getData().toEntity());

        return Header.OK(response(newEntity));
    }

    // 블로그 글 전체 조회
    public List<Article> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Header<ArticleResponse> read(Long id) {
        Article findArticle = baseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        return Header.OK(response(findArticle));
    }

    @Override
    public Header delete(Long id) {

        baseRepository.deleteById(id);

        return Header.OK();
    }

    @Transactional // 트랜잭션 메서드여야 더티 체킹 활성화, 기본값 (rollbackFor = RuntimeException.class)
    @Override
    public Header<ArticleResponse> update(Header<ArticleRequest> request) {
        ArticleRequest articleRequest = request.getData();
        Long id = articleRequest.getId();
        Article article = baseRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found" + id));

        article.update(articleRequest.getTitle(), articleRequest.getContent());

        return Header.OK(response(article));
    }

    @Override
    public Header<List<ArticleResponse>> search(Pageable pageable) {
        return null;
    }



}
