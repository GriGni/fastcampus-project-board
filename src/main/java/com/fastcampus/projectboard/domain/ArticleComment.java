package com.fastcampus.projectboard.domain;

import java.time.LocalDateTime;

public class ArticleComment {

    /**
     * 식별자
     */
    private Long id;

    /**
     * 게시판
     */
    private Article article;

    /**
     * 본문
     */
    private String content;

    /**
     * 메타 데이터
     */

    /**
     * 생성 일시
     */
    private LocalDateTime createdAt;
    /**
     * 생성자
     */
    private String createdBy;
    /**
     * 수정 일시
     */
    private LocalDateTime modifiedAt;
    /**
     * 수정자
     */
    private String modifiedBy;
}
