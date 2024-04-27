package com.codehows.sbd.service;

import com.codehows.sbd.domain.Article;
import com.codehows.sbd.dto.AddArticleRequest;
import com.codehows.sbd.dto.UpdateArticleRequest;
import com.codehows.sbd.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor        //final이 붙거나 @NotNull이 붙은 필드의(필수 인자를 이용한) 생성자 추가
@Service            //빈으로 등록
public class BlogService {

    //final을 사용하면 메모리상에서 공유가 된다.
    private final BlogRepository blogRepository;

    //블로그 글 추가 메소드 (save())
    public Article save(AddArticleRequest request, String userName){
        return blogRepository.save(request.toEntity(userName));
    }

    //블로그 글 조회 메소드(findAll())
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    //Id를 이용해 글 조회하기
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));
    }

    //블로그 글 삭제 메소드(delete())
    public void delete(long id){
        Article article = blogRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        blogRepository.deleteById(id);
    }

    //블로그 글 수정 메소드(update())
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    //게시글 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Article article){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

}
