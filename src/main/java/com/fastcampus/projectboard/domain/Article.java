package com.fastcampus.projectboard.domain;


import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    /**
     * 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql auto_increment identity 방식 사용 그래서 auto 설정 안함.
    private Long id;
    /**
     * 제목
     */
    @Setter
    @Column(nullable = false)
    private String title;
    /**
     * 본문
     */
    @Setter
    @Column(nullable = false, length = 10000)
    private String content;
    /**
     * 해시태그
     */
    @Setter
    private String hashtag;

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>(); // 리시트 및 set 으로 하는 경우가 있다. (중복 허용 안한다.)

    /**
     * 메타 데이터
     */

    /**
     * 생성 일시
     */
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
    /**
     * 생성자
     */
    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy;
    /**
     * 수정 일시
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;
    /**
     * 수정자
     */
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy;

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // factory method -> new keyword 사용 않도록
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    // 동일성, 동등성 구현 - 모든 필드를 다 비교한다. 그러므로 사용 안함

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Article article = (Article) o;
//        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(hashtag, article.hashtag) && Objects.equals(createdAt, article.createdAt) && Objects.equals(createdBy, article.createdBy) && Objects.equals(modifiedAt, article.modifiedAt) && Objects.equals(modifiedBy, article.modifiedBy);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
//    }

    // 동등성 검사

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
