package com.codehows.sbd.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class User implements UserDetails {  //UserDetails를 상속 받아 인증 객체로 이용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    //OAuth 관련 키를 저장하는 코드
    //사용자 이름
    @Column(name = "nickname", unique = true)
    private String nickname;

    @Builder
    public User(String email, String password, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    //사용자 이름 변경
    public User update(String nickname){
        this.nickname = nickname;

        return this;
    }

    @Override       //권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //사용자의 id를 반환 (고유한 값)
    @Override
    public String getUsername(){
        return email;
    }

    //사용자의 패스워드 반환
    @Override
    public String getPassword(){
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired(){
        return true;        //true는 만료되지 않았음
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    //패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled(){
        return true;
    }


}
