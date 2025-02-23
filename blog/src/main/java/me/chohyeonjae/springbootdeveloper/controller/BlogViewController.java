package me.chohyeonjae.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.chohyeonjae.springbootdeveloper.domain.Article;
import me.chohyeonjae.springbootdeveloper.dto.ArticleListViewResponse;
//import me.chohyeonjae.springbootdeveloper.dto.ArticleViewResponse;
import me.chohyeonjae.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles); //블로그 글 리스트 저장

        return "articleList"; //articleList라는 뷰 조회
    }
}
