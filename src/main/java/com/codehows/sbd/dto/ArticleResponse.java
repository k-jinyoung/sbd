package com.codehows.sbd.dto;
//응답을 위한 DTO
import com.codehows.sbd.domain.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    //Article 엔티티를 인수로 받는 생성자 만들기
    public ArticleResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
