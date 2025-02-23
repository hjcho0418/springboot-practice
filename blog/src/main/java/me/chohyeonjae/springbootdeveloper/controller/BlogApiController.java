package me.chohyeonjae.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.chohyeonjae.springbootdeveloper.domain.Article;
import me.chohyeonjae.springbootdeveloper.dto.AddArticleRequest;
import me.chohyeonjae.springbootdeveloper.dto.ArticleResponse;
import me.chohyeonjae.springbootdeveloper.dto.UpdateArticleRequest;
import me.chohyeonjae.springbootdeveloper.dto.ArticleResponse;
import me.chohyeonjae.springbootdeveloper.dto.UpdateArticleRequest;
import me.chohyeonjae.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 json형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles") //requestbody로 본문값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        //성공적으로 생성되었으며 저장된 블로그 글 정보를 응답객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream() //스트림은 여러 데이터가 모여 있는 컬렉션을 간편하게 처리하기 위한 기능
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }

    //url에서 {id}에 해당하는 값이 id로 들어옴
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);
        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
