package com.codehows.sbd.controller;

import com.codehows.sbd.domain.Article;
import com.codehows.sbd.dto.AddArticleRequest;
import com.codehows.sbd.dto.ArticleResponse;
import com.codehows.sbd.dto.UpdateArticleRequest;
import com.codehows.sbd.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor            //final이 붙거나 @NotNull이 붙은 필드의(필수 인자를 이용한) 생성자 추가
@RestController                     //HTTP Response Body에 객체 데이터를 Json 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    //HTTP 메소드가 POST일 때 전달받은 URL과 동일하면 메소드로 매핑한다.
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal){                  //@RequestBody로 요청 본문 값을 매핑한다.
        Article savedArticle = blogService.save(request, principal.getName());

        //요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송한다.
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    //블로그 글 조회 Controller
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    //id를 이용해 글 조회 Controller
    @GetMapping("/api/articles/{id}")
    //URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    //블로그 글 삭제 Controller
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Void> deleteArtilce(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    //블로그 글 수정 Controller
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }

}