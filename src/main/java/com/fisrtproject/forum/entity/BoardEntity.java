package com.fisrtproject.forum.entity;

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
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();

    private String topic;

    @Column(name="board_about")
    private String boardAbout;

    @Builder
    public BoardEntity(String topic, String boardAbout, List<PostEntity> posts) {
        this.topic = topic;
        this.boardAbout =boardAbout;
        this.posts = posts;
    }

    public static BoardEntity createBoard(String topic, String boardAbout, List<PostEntity> posts) {
        return BoardEntity.builder()
                .topic(topic)
                .boardAbout(boardAbout)
                .posts(posts)
                .build();
    }

    public void updatePosts(final PostEntity postEntity) {
        posts.add(postEntity);
        postEntity.updateBoard(this);
    }

}
