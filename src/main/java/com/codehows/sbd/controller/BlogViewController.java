package com.codehows.sbd.controller;

import com.codehows.sbd.domain.Article;
import com.codehows.sbd.dto.ArticleListViewResponse;
import com.codehows.sbd.dto.ArticleViewResponse;
import com.codehows.sbd.service.BlogService;
import lombok.RequiredArgsConstructor;
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
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);
        //블로그 글 리스트 저장

        return "articleList";
        //articleList.html 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    //수정이나 새로 글 작성
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if (id == null){
            // 새 글 작성
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            // 글 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
