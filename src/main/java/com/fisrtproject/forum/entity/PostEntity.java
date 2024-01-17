package com.fisrtproject.forum.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "comments"})
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    private UserEntity userEntity;

    @OneToMany(mappedBy = "postEntity")
    private List<CommentEntity> comments = new ArrayList<>();

    private String title;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createdAt;

    public PostEntity(BoardEntity boardEntity, String title, String content, String createdAt) {
        this.boardEntity = boardEntity;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    // 연관관계 편의 메서드 작성
    public void updateBoard(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
        boardEntity.updatePosts(this);
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
