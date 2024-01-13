package com.fisrtproject.forum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fisrtproject.forum.service.PostService;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "posts"})
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @OneToMany(mappedBy = "boardEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PostEntity> posts = new ArrayList<>();

    @Column(name = "topic")
    private String topic;

    @Column(name="board_about")
    private String boardAbout;

    @Builder
    public BoardEntity(String topic, String boardAbout) {
        this.topic = topic;
        this.boardAbout = boardAbout;
    }

    // 연관관계 편의 메서드 작성
    public void updatePosts(PostEntity postEntity) {
        this.posts.add(postEntity);
        postEntity.updateBoard(this);
    }
}
