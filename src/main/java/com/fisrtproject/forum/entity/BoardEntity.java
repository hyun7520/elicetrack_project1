package com.fisrtproject.forum.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "topic", unique = true)
    private String topic;

    @Column(name="board_description")
    private String boardDescription;

    @Builder
    public BoardEntity(String topic, String boardDescription) {
        this.topic = topic;
        this.boardDescription = boardDescription;
    }

    // 연관관계 편의 메서드 작성
    public void updatePosts(PostEntity postEntity) {
        this.posts.add(postEntity);
        postEntity.updateBoard(this);
    }
}
