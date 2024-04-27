package com.codehows.sbd.dto;
//글 작성 리스트를 뷰로 보내는 객체

import com.codehows.sbd.domain.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;


    //생성자 만들기
    public ArticleListViewResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
