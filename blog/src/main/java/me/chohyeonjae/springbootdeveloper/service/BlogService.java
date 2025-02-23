package me.chohyeonjae.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.chohyeonjae.springbootdeveloper.domain.Article;
import me.chohyeonjae.springbootdeveloper.dto.AddArticleRequest;
import me.chohyeonjae.springbootdeveloper.dto.UpdateArticleRequest;
import me.chohyeonjae.springbootdeveloper.dto.UpdateArticleRequest;
import me.chohyeonjae.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service //빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    //조회하고 없으면 예외처리
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
