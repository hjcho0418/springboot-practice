package me.chohyeonjae.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) //생성시간과 수정시간 관리
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id //id를 기본 필드키로
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 1증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) //title과 매칭
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate //엔티티 생성될때 시간
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate //엔티티 수정될때 시간
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder //빌더패턴으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
