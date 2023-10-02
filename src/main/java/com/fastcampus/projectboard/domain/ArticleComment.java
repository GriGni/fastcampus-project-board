package com.fastcampus.projectboard.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ArticleComment {

    /**
     * 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 게시판
     */
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // optional = false : article이 null이면 안된다.
    private Article article;

    /**
     * 본문
     */
    @Setter
    @Column(nullable = false, length = 500)
    private String content;

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

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }
    // factory method
    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
