package com.fisrtproject.forum.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
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

//    @OneToMany
//    @JoinColumn(name="comment_id")
//    private List<CommentEntity> comments = new ArrayList<>();

    private String title;
    private String content;
    private Timestamp createdAt;

    // 연관관계 편의 메서드 작성
    public void updateBoard(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
        boardEntity.updatePosts(this);
    }
}
