package com.codehows.sbd.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article {

    @Id     //id 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)       //nullable = false 는 not null 이라는 뜻
    private String title;

    @Column(name = "content", nullable = false, length = 3000)
    private String content;

    //생성 시간과 수정 시간
    @CreatedDate        //엔티티가 생성될 때 생성 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @LastModifiedDate       //엔티티가 수정될 때 수정 시간 과정
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "author", nullable = false)
    private String author;


    @Builder        //빌더 패턴으로 객체 생성
    public Article(String title, String content,String author){
        this.author = author;
        this.title = title;
        this.content = content;
    }

    //블로그 글 수정하기
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
