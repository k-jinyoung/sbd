package com.codehows.sbd.dto;
//데이터를 전송할 때 사용되는 것이 dto 이다.

import com.codehows.sbd.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor          //기본 생성자
@AllArgsConstructor         //모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    //생성자를 통해 객체 생성
    public Article toEntity(String author){      //toEntity()라는 메소드 생성
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
